package com.xiongmengyingshi.v17.controller.vo;

import java.math.BigInteger;

/**
 * Created by nicholas.chi on 2018/1/16.
 */
public class LoginVO extends BaseVO {
    private String token;
    private BigInteger adminId;
    private String adminName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
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


    @Override
    public String toString() {
        return "LoginVO{" +
                "token='" + token + '\'' +
                ", adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                '}';
    }

}
