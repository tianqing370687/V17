package com.xiongmengyingshi.v17.service.impl;

import com.xiongmengyingshi.v17.constant.ErrCodeConstant;
import com.xiongmengyingshi.v17.constant.ServerCodeConstant;
import com.xiongmengyingshi.v17.dao.PersonalInfoMapper;
import com.xiongmengyingshi.v17.entity.PersonalInfo;
import com.xiongmengyingshi.v17.service.PersonalInfoService;
import com.xiongmengyingshi.v17.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigInteger;
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

    public void saveFileAndUpdateInfo(Integer userId, MultipartFile video1,
                                      MultipartFile video2,MultipartFile mugShotImg,MultipartFile halfLengthImg,MultipartFile fullBodyImg){
        //存储video，pic
        try {
            String video1Url = FileUtils.uploadFile(
                    ServerCodeConstant.upload_file_type_video1,userId,video1);
            String video2Url = FileUtils.uploadFile(
                    ServerCodeConstant.upload_file_type_video2,userId,video2);
            String mugShotImgUrl = FileUtils.uploadFile(
                    ServerCodeConstant.upload_file_type_mugShot,userId,mugShotImg);
            String halfLengthImgUrl = FileUtils.uploadFile(
                    ServerCodeConstant.upload_file_type_halfLength,userId,halfLengthImg);
            String fullBodyImgUrl = FileUtils.uploadFile(
                    ServerCodeConstant.upload_file_type_fullBody,userId,fullBodyImg);


            PersonalInfo personalInfo1 = personalInfoMapper.selectByPrimaryKey(userId);
            personalInfo1.setVideo1Url(video1Url);
            personalInfo1.setVideo2Url(video2Url);
            personalInfo1.setMugShotImgUrl(mugShotImgUrl);
            personalInfo1.setHalfLengthImgUrl(halfLengthImgUrl);
            personalInfo1.setFullBodyImgUrl(fullBodyImgUrl);

            personalInfoMapper.updateByPrimaryKey(personalInfo1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<PersonalInfo> listPersonalInfoByPage(int currPage, int pageSize){
        Map<String, Object> data = new HashMap<String, Object>();
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
}
