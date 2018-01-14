package com.xiongmengyingshi.v17.interceptor;

import com.xiongmengyingshi.v17.controller.EnrollController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by ubuntu on 18-1-14.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LogManager.getLogger(EnrollController.class);

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("=================LoginInterceptor:preHandle:{]===============",request.getRequestURI());
        HttpSession session = request.getSession();


        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("=================LoginInterceptor:postHandle===============");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("=================LoginInterceptor:afterCompletion===============");
    }


}
