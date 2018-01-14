package com.xiongmengyingshi.v17.controller;

import com.xiongmengyingshi.v17.constant.ServerCodeConstant;
import com.xiongmengyingshi.v17.controller.form.EnrollInfoForm;
import com.xiongmengyingshi.v17.entity.PersonalInfo;
import com.xiongmengyingshi.v17.service.PersonalInfoService;
import com.xiongmengyingshi.v17.service.VerificationCodeService;
import com.xiongmengyingshi.v17.utils.CommonUtils;
import com.xiongmengyingshi.v17.utils.FileUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ubuntu on 18-1-12.
 */
@RequestMapping(value = "/enroll")
@Controller
public class EnrollController {

    @Autowired
    PersonalInfoService personalInfoService;
    @Autowired
    VerificationCodeService verificationCodeService;

    private static Logger logger = LogManager.getLogger(EnrollController.class);

    @RequestMapping(value = "/getVerificationCode",method = RequestMethod.POST)
    public @ResponseBody String getVerificationCode(String phoneNum){
        String retCode = verificationCodeService.getVerificationCode(phoneNum.trim());
        return retCode;
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "phoneNum", value = "用户", required = true, dataType = "Long")
    @RequestMapping(value = "/resendCode",method = RequestMethod.POST)
    public @ResponseBody String resendCode(String phoneNum){
        String retCode = verificationCodeService.getVerificationCode(phoneNum);
        return retCode;
    }

    @RequestMapping(value = "/testCode",method = RequestMethod.POST)
    public @ResponseBody String testCode(String phoneNum,String verificationCode){
        String retCode = verificationCodeService.testCode(phoneNum,verificationCode);
        return retCode;
    }

    @RequestMapping(value = "/saveEnrollInfo",method = RequestMethod.POST)
    public void saveEnrollInfo(EnrollInfoForm form){

        //保存基本信息
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName(form.getName());
        personalInfo.setBirthday(form.getBirthday());
        personalInfo.setAge(form.getAge());
        personalInfo.setBirthplace(form.getBirthplace());
        personalInfo.setResidence(form.getResidence());
        personalInfo.setPerformingExperience(form.getPerformingExperience());
        personalInfo.setFamilyComposition(form.getFamilyComposition());
        personalInfo.setOccupation(form.getOccupation());
        personalInfo.setSchoolsMajors(form.getSchoolsMajors());
        personalInfo.setGrade(form.getGrade());
        personalInfo.setPlaceOfStudy(form.getPlaceOfStudy());
        personalInfo.setPhoneNum(form.getPhoneNum());
        personalInfo.setQq(form.getQq());
        personalInfo.setEmail(form.getEmail());
        personalInfo.setWeibo(form.getWeibo());
        personalInfo.setHomePhone(form.getHomePhone());
        personalInfo.setSpecialty(form.getSpecialty());
        personalInfo.setInterest(form.getInterest());
        personalInfo.setAwards(form.getAwards());
        personalInfo.setDream(form.getDream());
        personalInfo.setIdol(form.getIdol());
        personalInfo.setComic(form.getComic());
        personalInfo.setFilmWorks(form.getFilmWorks());
        personalInfo.setWebsite(form.getWebsite());
        personalInfo.setWantToSay(form.getWantToSay());
        personalInfo.setCreateTime(new Date());

        personalInfoService.savePersonalInfo(personalInfo);

        //查询id
        BigInteger userId = new BigInteger("1");

        //存储video，pic
        try {
            String video1Url = FileUtils.uploadFile(
                    ServerCodeConstant.upload_file_type_video1,userId,form.getVideo1());
            String video2Url = FileUtils.uploadFile(
                    ServerCodeConstant.upload_file_type_video2,userId,form.getVideo2());
            String mugShotImgUrl = FileUtils.uploadFile(
                    ServerCodeConstant.upload_file_type_mugShot,userId,form.getMugShotImg());
            String halfLengthImgUrl = FileUtils.uploadFile(
                    ServerCodeConstant.upload_file_type_halfLength,userId,form.getHalfLengthImg());
            String fullBodyImgUrl = FileUtils.uploadFile(
                    ServerCodeConstant.upload_file_type_fullBody,userId,form.getFullBodyImg());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //update表信息

        //返回结果
        String serialNum = new SimpleDateFormat("yyMMddHHmmss").format(new Date())
                +CommonUtils.strFormat(userId+"",6);

    }
}
