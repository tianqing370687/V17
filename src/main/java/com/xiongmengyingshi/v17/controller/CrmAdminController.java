package com.xiongmengyingshi.v17.controller;

import com.xiongmengyingshi.v17.constant.ErrCodeConstant;
import com.xiongmengyingshi.v17.controller.vo.LoginVO;
import com.xiongmengyingshi.v17.service.CrmAdminService;
import com.xiongmengyingshi.v17.service.bo.LoginBO;
import com.xiongmengyingshi.v17.session.UserSession;
import com.xiongmengyingshi.v17.utils.CommonUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by ubuntu on 18-1-14.
 */
@RequestMapping(value = "/crm")
@Controller
public class CrmAdminController {

    @Autowired
    CrmAdminService crmAdminService;

    Logger logger = LogManager.getLogger(EnrollController.class);

    @ResponseBody
    @ApiOperation(value = "注册", notes = "")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(HttpServletRequest request,String adminName, String adminPassword){
        String passwordSalt = CommonUtils.getRandomCode(6);
        String loginIP = CommonUtils.getIP(request);
        String retCode = crmAdminService.register(adminName,adminPassword,passwordSalt,loginIP);
        return retCode;
    }

    @ResponseBody
    @ApiOperation(value = "登录", notes = "")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public LoginVO login(HttpServletRequest request, String adminName, String adminPassword){
        LoginVO vo = new LoginVO();
        String loginIP = CommonUtils.getIP(request);
        LoginBO bo = crmAdminService.login(adminName,adminPassword,loginIP);
        vo.setRetCode(bo.getRetCode());
        if(ErrCodeConstant.LOGIN_SUCCESS.equals(bo.getRetCode())){
            UserSession session = new UserSession();
            session.setAdminId(new Long(bo.getAdmin().getAdminId()+""));
            session.setAdminName(adminName);

            HttpSession  httpSession= request.getSession(true);
            String sessionId = httpSession.getId();
            httpSession.setAttribute("userSession",session);

            vo.setToken(sessionId);
            vo.setAdminId(bo.getAdmin().getAdminId());
            vo.setAdminName(bo.getAdmin().getAdminName());
        }
        return vo;
    }

    @ResponseBody
    @ApiOperation(value = "修改密码", notes = "")
    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public String changePassword(HttpServletRequest request,
                                               String oldPassword,String newPassword){
        UserSession session = (UserSession) request.getSession().getAttribute("userSession");
        String retCode = crmAdminService.changePassword(session.getAdminId(),oldPassword,newPassword);
        return retCode;
    }

    @ResponseBody
    @ApiOperation(value = "退出登录", notes = "")
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return ErrCodeConstant.EXIT_SUCCESS;
    }
}
