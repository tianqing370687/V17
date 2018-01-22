package com.xiongmengyingshi.v17.session;

/**
 * Created by ubuntu on 18-1-14.
 */
public class UserSession {

    private long adminId;

    private String adminName;

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
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
        return "UserSession{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                '}';
    }


}
