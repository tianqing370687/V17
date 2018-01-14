package com.xiongmengyingshi.v17.session;

import java.math.BigInteger;

/**
 * Created by ubuntu on 18-1-14.
 */
public class UserSession {

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
