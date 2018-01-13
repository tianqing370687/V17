package com.xiongmengyingshi.v17.controller;

import com.xiongmengyingshi.v17.controller.form.EnrollInfoForm;
import com.xiongmengyingshi.v17.entity.PersonalInfo;
import com.xiongmengyingshi.v17.service.PersonalInfoService;
import com.xiongmengyingshi.v17.service.VerificationCodeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    Logger logger = LogManager.getLogger(EnrollController.class);

    @RequestMapping(value = "/getVerificationCode",method = RequestMethod.POST)
    public @ResponseBody String getVerificationCode(String phoneNum){
        String retCode = verificationCodeService.getVerificationCode(phoneNum.trim());
        return retCode;
    }

    @RequestMapping(value = "/resendCode",method = RequestMethod.POST)
    public @ResponseBody String resendCode(String phoneNum){
        String retCode = verificationCodeService.getVerificationCode(phoneNum);
        return null;
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

        //存储video，pic

        //update表信息

        //返回结果

    }
}
