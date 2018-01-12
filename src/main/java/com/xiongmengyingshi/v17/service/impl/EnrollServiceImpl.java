package com.xiongmengyingshi.v17.service.impl;

import com.xiongmengyingshi.v17.service.EnrollService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by ubuntu on 18-1-12.
 */
public class EnrollServiceImpl implements EnrollService {

    Logger logger = LogManager.getLogger(EnrollServiceImpl.class);

    public String getVerificationCode(String phoneNum){
        double verificationCode = (Math.random()*9+1)*100000;
        return null;
    }

}
