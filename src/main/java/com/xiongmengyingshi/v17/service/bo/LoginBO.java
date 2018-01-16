package com.xiongmengyingshi.v17.service.bo;

import com.xiongmengyingshi.v17.entity.Admin;

/**
 * Created by nicholas.chi on 2018/1/16.
 */
public class LoginBO {

    private String retCode;
    private Admin admin;

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
