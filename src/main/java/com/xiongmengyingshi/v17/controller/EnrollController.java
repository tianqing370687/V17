package com.xiongmengyingshi.v17.controller;

import com.xiongmengyingshi.v17.constant.ErrCodeConstant;
import com.xiongmengyingshi.v17.controller.form.EnrollInfoForm;
import com.xiongmengyingshi.v17.controller.vo.SaveEnrollInfoVO;
import com.xiongmengyingshi.v17.entity.PersonalInfo;
import com.xiongmengyingshi.v17.service.PersonalInfoService;
import com.xiongmengyingshi.v17.service.VerificationCodeService;
import com.xiongmengyingshi.v17.utils.CommonUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
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

    @ApiOperation(value = "获取验证码", notes = "通过填写用户手机号，获取短信验证码")
    @RequestMapping(value = "/getVerificationCode",method = RequestMethod.POST)
    public @ResponseBody String getVerificationCode(String phoneNum){
        String retCode = verificationCodeService.getVerificationCode(phoneNum.trim());
        return retCode;
    }

    @ApiOperation(value = "重新获取验证码", notes = "通过填写用户手机号，获取短信验证码")
    @ApiImplicitParam(name = "phoneNum", value = "用户", required = true, dataType = "Long")
    @RequestMapping(value = "/resendCode",method = RequestMethod.POST)
    public @ResponseBody String resendCode(String phoneNum){
        String retCode = verificationCodeService.getVerificationCode(phoneNum);
        return retCode;
    }

    @ApiOperation(value = "验证手机验证码", notes = "")
    @RequestMapping(value = "/testCode",method = RequestMethod.POST)
    public @ResponseBody String testCode(String phoneNum,String verificationCode){
        String retCode = verificationCodeService.testCode(phoneNum,verificationCode);
        return retCode;
    }

    @ApiOperation(value = "保存报名信息", notes = "")
    @RequestMapping(value = "/saveEnrollInfo",method = RequestMethod.POST,consumes = "multipart/form-data")
    public @ResponseBody
    SaveEnrollInfoVO saveEnrollInfo(EnrollInfoForm form){

        logger.info("form : {}",form);

        SaveEnrollInfoVO vo = new SaveEnrollInfoVO();
        //保存基本信息
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setName(form.getName());

        Date birthday = new Date();
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(form.getBirthday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        personalInfo.setBirthday(birthday);
        int age = 0;
        try {
            age = CommonUtils.getAge(birthday);
        } catch (Exception e) {
            e.printStackTrace();
        }
        personalInfo.setAge(age);
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
        String retCode = personalInfoService.savePersonalInfo(personalInfo);

        if(!ErrCodeConstant.ENROLL_INFO_SUCCESS.equals(retCode)){
            vo.setSerialNum(null);
            vo.setRetCode(retCode);
            return vo;
        }
        //查询id
        int userId = personalInfoService.getPersonalInfoId(form.getName(),form.getPhoneNum());

        personalInfoService.saveFileAndUpdateInfo(userId,form.getVideo1(),form.getVideo2(),
                form.getMugShotImg(),form.getHalfLengthImg(),form.getFullBodyImg());

        //返回结果
        String serialNum = personalInfoService.getSerialNum(userId);
        logger.info("serialNum is {}",serialNum);

        vo.setRetCode(retCode);
        vo.setSerialNum(serialNum);
        return vo;
    }
}
