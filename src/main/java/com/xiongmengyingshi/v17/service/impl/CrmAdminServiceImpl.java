package com.xiongmengyingshi.v17.service.impl;

import com.xiongmengyingshi.v17.constant.ErrCodeConstant;
import com.xiongmengyingshi.v17.controller.EnrollController;
import com.xiongmengyingshi.v17.dao.AdminMapper;
import com.xiongmengyingshi.v17.entity.Admin;
import com.xiongmengyingshi.v17.service.CrmAdminService;
import com.xiongmengyingshi.v17.session.UserSession;
import com.xiongmengyingshi.v17.utils.CommonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by ubuntu on 18-1-14.
 */
@Service
@Transactional
public class CrmAdminServiceImpl implements CrmAdminService{

    @Autowired
    private AdminMapper adminMapper;

    private static Logger logger = LogManager.getLogger(CrmAdminService.class);

    public String register(String adminName,String adminPassword, String passwordSalt,String loginIP){
        Admin admin = adminMapper.selectByAdminName(adminName);
        if(admin != null){
            return ErrCodeConstant.REGISTERED_FAIL_NAME_EXITED;
        }
        String password = CommonUtils.getMD5Str(adminPassword+CommonUtils.getMD5Str(passwordSalt));
        logger.info("sakt length is {}",passwordSalt.length());
        logger.info("password is {}",password);
        admin = new Admin();
        admin.setAdminName(adminName);
        admin.setAdminPassword(password);
        admin.setPasswordSalt(passwordSalt);
        admin.setLoginTime(new Date());
        admin.setLoginIP(loginIP);

        adminMapper.insert(admin);

        return ErrCodeConstant.REGISTERED_SUCCESS;
    }


    public String login(String adminName,String adminPassword,String loginIP){

        Admin admin = adminMapper.selectByAdminName(adminName);
        if(admin == null){
            return ErrCodeConstant.LOGIN_FAIL_WRONG_USERNAME;
        }

        boolean isSuccess =(CommonUtils.getMD5Str(adminPassword+CommonUtils.getMD5Str(admin.getPasswordSalt()))).
                equals(admin.getAdminPassword());

        if(isSuccess){
            admin.setLoginTime(new Date());
            admin.setLoginIP(loginIP);
            adminMapper.updateByPrimaryKey(admin);
            return ErrCodeConstant.LOGIN_SUCCESS;
        }

        return ErrCodeConstant.LOGIN_FAIL_WRONG_PASSWORD;

    }

    public String changePassword(long adminId,String oldPassword,String newPassword ){
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        if(admin == null){
            return ErrCodeConstant.CHANGE_PASSWORD_FAIL;
        }
        boolean isCorrect = (CommonUtils.getMD5Str(oldPassword+CommonUtils.getMD5Str(admin.getPasswordSalt()))).
                equals(admin.getAdminPassword());

        if(isCorrect){
            String password = CommonUtils.getMD5Str(newPassword+CommonUtils.getMD5Str(admin.getPasswordSalt()));
            admin.setAdminPassword(password);
            adminMapper.updateByPrimaryKey(admin);
            return ErrCodeConstant.CHANGE_PASSWORD_SUCCESS;
        }

        return ErrCodeConstant.CHANGE_PASSWORD_FAIL;
    }

    public Admin getAdminByName(String adminName){
        return adminMapper.selectByAdminName(adminName);
    }

    public void saveSession(HttpServletRequest request, String adminName){
        Admin admin = adminMapper.selectByAdminName(adminName);
        UserSession session = new UserSession();
        session.setAdminId(new Long(admin.getAdminId()+""));
        session.setAdminName(adminName);
        request.getSession().setAttribute("userSession",session);
    }


}
