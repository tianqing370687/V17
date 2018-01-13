package com.xiongmengyingshi.v17.entity;

/**
 * Created by nicholas.chi on 2018/1/13.
 */
public class VerificationCode {

    private String phoneNum;
    private String verificationCode;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "VerificationCode{" +
                "phoneNum='" + phoneNum + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }

}
