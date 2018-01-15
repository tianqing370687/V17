package com.xiongmengyingshi.v17.interceptor;

import com.xiongmengyingshi.v17.controller.EnrollController;
import com.xiongmengyingshi.v17.session.UserSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ubuntu on 18-1-14.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LogManager.getLogger(EnrollController.class);

    private static List<String> ignorelist = Arrays.asList("/v17/crm/register","/v17/crm/login");

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("=================LoginInterceptor:preHandle:{}===============",request.getRequestURI());

        if (request.getHeader(HttpHeaders.ORIGIN) != null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "3600");
        }

        String requestUrl = request.getRequestURI();
        if(ignorelist.contains(requestUrl)){
            return true;
        }

        UserSession session = (UserSession) request.getSession().getAttribute("userSession");

        if(session == null){
            return false;
        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        logger.info("=================LoginInterceptor:postHandle===============");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.info("=================LoginInterceptor:afterCompletion===============");
    }


}
