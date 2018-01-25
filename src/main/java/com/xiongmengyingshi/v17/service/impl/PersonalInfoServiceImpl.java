package com.xiongmengyingshi.v17.service.impl;

import com.xiongmengyingshi.v17.constant.ErrCodeConstant;
import com.xiongmengyingshi.v17.constant.ServerCodeConstant;
import com.xiongmengyingshi.v17.dao.PersonalInfoMapper;
import com.xiongmengyingshi.v17.entity.PersonalInfo;
import com.xiongmengyingshi.v17.service.PersonalInfoService;
import com.xiongmengyingshi.v17.utils.AliyunOssUtils;
import com.xiongmengyingshi.v17.utils.CommonUtils;
import com.xiongmengyingshi.v17.utils.FileUtils;
import com.xiongmengyingshi.v17.utils.ResourceUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nicholas.chi on 2018/1/13.
 */
@Service
@Transactional
public class PersonalInfoServiceImpl implements PersonalInfoService {

    @Autowired
    private PersonalInfoMapper personalInfoMapper;

    private static Logger logger = LogManager.getLogger(PersonalInfoServiceImpl.class);

    public String savePersonalInfo(PersonalInfo personalInfo){
        Integer infoId = personalInfoMapper.selectInfoIdByParam(personalInfo.getName(),personalInfo.getPhoneNum());
        if(infoId != null){
            return ErrCodeConstant.ENROLL_INFO_FAIL_USER_EXIT;
        }
        personalInfoMapper.insert(personalInfo);
        return ErrCodeConstant.ENROLL_INFO_SUCCESS;
    }

    public Integer getPersonalInfoId(String name,String phoneNum){
        return personalInfoMapper.selectInfoIdByParam(name,phoneNum);
    }

//    @Async
    public void saveFileAndUpdateInfo(Integer userId, MultipartFile video1,
                                      MultipartFile video2,MultipartFile mugShotImg,MultipartFile halfLengthImg,MultipartFile fullBodyImg){

        String video1Url = uploadFile2OSS(video1,ServerCodeConstant.UPLOAD_FILE_TYPE_VIDEO1,userId);
        String video2Url = uploadFile2OSS(video2,ServerCodeConstant.UPLOAD_FILE_TYPE_VIDEO2,userId);
        String mugShotImgUrl = uploadFile2OSS(mugShotImg,ServerCodeConstant.UPLOAD_FILE_TYPE_MUGSHOT,userId);
        String halfLengthImgUrl = uploadFile2OSS(halfLengthImg,ServerCodeConstant.UPLOAD_FILE_TYPE_HALFLENGTH,userId);
        String fullBodyImgUrl = uploadFile2OSS(fullBodyImg,ServerCodeConstant.UPLOAD_FILE_TYPE_FULLBODY,userId);


        PersonalInfo personalInfo1 = personalInfoMapper.selectByPrimaryKey(userId);
        personalInfo1.setVideo1Url(video1Url);
        personalInfo1.setVideo2Url(video2Url);
        personalInfo1.setMugShotImgUrl(mugShotImgUrl);
        personalInfo1.setHalfLengthImgUrl(halfLengthImgUrl);
        personalInfo1.setFullBodyImgUrl(fullBodyImgUrl);

        personalInfoMapper.updateByPrimaryKey(personalInfo1);

        //存储video，pic
//        try {
//            String video1Url = FileUtils.uploadFile(
//                    ServerCodeConstant.upload_file_type_video1,userId,video1);
//            String video2Url = FileUtils.uploadFile(
//                    ServerCodeConstant.upload_file_type_video2,userId,video2);
//            String mugShotImgUrl = FileUtils.uploadFile(
//                    ServerCodeConstant.upload_file_type_mugShot,userId,mugShotImg);
//            String halfLengthImgUrl = FileUtils.uploadFile(
//                    ServerCodeConstant.upload_file_type_halfLength,userId,halfLengthImg);
//            String fullBodyImgUrl = FileUtils.uploadFile(
//                    ServerCodeConstant.upload_file_type_fullBody,userId,fullBodyImg);
//
//
//            PersonalInfo personalInfo1 = personalInfoMapper.selectByPrimaryKey(userId);
//            personalInfo1.setVideo1Url(video1Url);
//            personalInfo1.setVideo2Url(video2Url);
//            personalInfo1.setMugShotImgUrl(mugShotImgUrl);
//            personalInfo1.setHalfLengthImgUrl(halfLengthImgUrl);
//            personalInfo1.setFullBodyImgUrl(fullBodyImgUrl);
//
//            personalInfoMapper.updateByPrimaryKey(personalInfo1);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public List<PersonalInfo> listPersonalInfoByPage(int currPage, int pageSize){
        Map<String, Object> data = new HashMap<String, Object>(2);
        data.put("rows", (currPage-1)*pageSize);
        data.put("pageSize", pageSize);
        return personalInfoMapper.listPersonalInfoByPage(data);
    }
    public PersonalInfo getPersonlInfoById(int infoId){
        PersonalInfo personalInfo = personalInfoMapper.selectByPrimaryKey(infoId);
        return personalInfo;
    }

    public List<PersonalInfo> listPersonalInfoByAll(){
        return personalInfoMapper.listPersonalInfoByAll();
    }

    public String getSerialNum(Integer infoId){
//        String serialNum = new SimpleDateFormat("yyMMddHHmmss").format(new Date())
//                + CommonUtils.strFormat(infoId+"",6);
//        PersonalInfo personalInfo = personalInfoMapper.selectByPrimaryKey(infoId);
//        personalInfo.setSerialNum(serialNum);
//        personalInfoMapper.updateByPrimaryKey(personalInfo);
        String serialNum = "v172018"+CommonUtils.strFormat(infoId+"",4);
        PersonalInfo personalInfo = personalInfoMapper.selectByPrimaryKey(infoId);
        personalInfo.setSerialNum(serialNum);
        personalInfoMapper.updateByPrimaryKey(personalInfo);
        return serialNum;
    }


    private String uploadFile2OSS(MultipartFile file,String type, long infoId){

        File targetFile;

        String objUrl = "";

        if(file == null ||file.isEmpty()){
            logger.info("the uploadfile is empty!");
            return objUrl;
        }

        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileName = (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))+"."+suffix;

        String key ="v17/uploadFile/"+ infoId+"/"+type+fileName;
        logger.info("the key is {}",key);

        AliyunOssUtils utils = new AliyunOssUtils();

        boolean isSuccess;

        try {
            targetFile = CommonUtils.convert(file);
            isSuccess = utils.uploadFile(key,targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("MultipartFile file convert fail : {}",e.getMessage());
            return objUrl;
        }

        if(isSuccess){
            objUrl = ResourceUtils.getBundleValue4String("ali.file.url")+key;
            logger.info("the path of file is : {}",objUrl);
            targetFile.delete();
            return objUrl;
        }

        return objUrl;
    }

    public int deletePersonalInfobyId(int infoId){
        int result = personalInfoMapper.deleteByPrimaryKey(infoId);
        return result;
    }
}
