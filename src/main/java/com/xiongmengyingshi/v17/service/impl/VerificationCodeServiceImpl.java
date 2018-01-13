package com.xiongmengyingshi.v17.service.impl;

import com.xiongmengyingshi.v17.constant.ErrCodeConstant;
import com.xiongmengyingshi.v17.dao.VerificationCodeMapper;
import com.xiongmengyingshi.v17.entity.VerificationCode;
import com.xiongmengyingshi.v17.service.VerificationCodeService;
import com.xiongmengyingshi.v17.utils.CommonUtils;
import com.xiongmengyingshi.v17.utils.SMSUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

/**
 * Created by ubuntu on 18-1-12.
 */
@Service
@Transactional
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Autowired
    VerificationCodeMapper verificationCodeMapper;

    private static final String CODE_SMS_CONTENT = "您的手机验证码为： [CODE]，请勿向任何单位或个人泄露！";

    Logger logger = LogManager.getLogger(VerificationCodeServiceImpl.class);

    public String getVerificationCode(String phoneNum){

        String verificationCode = CommonUtils.getRandomCode(6);
        VerificationCode code = verificationCodeMapper.selectByPrimaryKey(phoneNum);
        if(code == null){
            code.setPhoneNum(phoneNum);
            code.setVerificationCode(verificationCode);
            logger.info("VerificationCode : {}",code.toString());
            verificationCodeMapper.insert(code);
        }else{
            code.setVerificationCode(verificationCode);
            logger.info("VerificationCode : {}",code.toString());
            verificationCodeMapper.updateByPrimaryKey(code);
        }
        return sendSMS(phoneNum,verificationCode);
    }

    public String testCode(String phoneNum,String verificationCode){
        VerificationCode code = verificationCodeMapper.selectByPrimaryKey(phoneNum);

        if(code == null){
            logger.info("Did not find the verification code");
            return ErrCodeConstant.TEST_CODE_FAIL;
        }

        if(StringUtils.isBlank(verificationCode) || !verificationCode.equals(code.getVerificationCode())){
            logger.info("Incorrect verification code");
            return ErrCodeConstant.TEST_CODE_FAIL;
        }
        return ErrCodeConstant.TEST_CODE_SUCCESS;
    }

    private String sendSMS(String phoneNum,String verificationCode){
        String result = null;
        //发送短信
        String smsContet = CODE_SMS_CONTENT.replace("[CODE]",verificationCode);
        logger.info("sms content : {}" ,smsContet);

        try {
            result = SMSUtils.senaSMS(phoneNum,smsContet);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error("SMS send fail! : {}" ,e.getStackTrace().toString() );
            return ErrCodeConstant.SMS_SEND_FAIL;
        }
        if(StringUtils.isNoneBlank(result) &&  "1".equals(result)){
            return ErrCodeConstant.SMS_SEND_SUCESS;
        }
        return ErrCodeConstant.SMS_SEND_FAIL;
    }

}
