package com.xiongmengyingshi.v17.service;

import com.xiongmengyingshi.v17.entity.Admin;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ubuntu on 18-1-14.
 */
public interface CrmAdminService {

    String register(String adminName,String adminPassword, String passwordSalt,String loginIP);

    String login(String adminName,String adminPassword,String loginIP);

    String changePassword(long adminId,String oldPassword,String newPassword );

    void saveSession(HttpServletRequest request, String adminName);

    Admin getAdminByName(String adminName);

}
