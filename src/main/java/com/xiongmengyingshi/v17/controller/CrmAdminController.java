package com.xiongmengyingshi.v17.controller;

import com.xiongmengyingshi.v17.session.UserSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ubuntu on 18-1-14.
 */
@RequestMapping(value = "/crm")
@Controller
public class CrmAdminController {

    Logger logger = LogManager.getLogger(EnrollController.class);

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody String login(HttpServletRequest request,
                                      String adminName, String adminPassword){
        UserSession session = new UserSession();
        request.getSession().setAttribute("userSession",session);

        return "HelloWorld";
    }

    @RequestMapping(value = "/changePassword",method = RequestMethod.POST)
    public @ResponseBody String changePassword(HttpServletRequest request,
                                               String oldPassword,String newPassword){
        UserSession session = (UserSession) request.getSession().getAttribute("userSession");
        return null;
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public @ResponseBody String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return null;
    }



}
