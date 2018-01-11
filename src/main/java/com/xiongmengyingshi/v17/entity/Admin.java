package com.xiongmengyingshi.v17.entity;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by nicholas.chi on 2018/1/11.
 */
public class Admin {

    private BigInteger adminId;
    private String adminName;
    private String adminPassword;
    private String passwordSalt;
    private Date loginTime;
    private String loginIP;

    public BigInteger getAdminId() {
        return adminId;
    }

    public void setAdminId(BigInteger adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getPasswordSalt() {
        return passwordSalt;
    }

    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", passwordSalt='" + passwordSalt + '\'' +
                ", loginTime=" + loginTime +
                ", loginIP='" + loginIP + '\'' +
                '}';
    }
}
