package com.xiongmengyingshi.v17.controller.vo;

import java.math.BigInteger;

/**
 * Created by nicholas.chi on 2018/1/16.
 */
public class LoginVO extends BaseVO {

    private BigInteger adminId;
    private String adminName;

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

}
