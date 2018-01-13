package com.xiongmengyingshi.v17.service;

/**
 * Created by ubuntu on 18-1-12.
 */
public interface VerificationCodeService {

    String getVerificationCode(String phoneNum);

//    String resend(String phoneNum);

    String testCode(String phoneNum,String verificationCode);


}
