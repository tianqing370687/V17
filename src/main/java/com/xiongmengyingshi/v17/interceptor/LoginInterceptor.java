package com.xiongmengyingshi.v17.interceptor;

import com.xiongmengyingshi.v17.session.UserSession;
import com.xiongmengyingshi.v17.utils.CommonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ubuntu on 18-1-14.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    Logger logger = LogManager.getLogger(LoginInterceptor.class);

    private static List<String> ignorelist = Arrays.asList("/v17/crm/register","/v17/crm/login");

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("=================LoginInterceptor:preHandle:method name :{}===============",request.getRequestURI());
        logger.info("=================LoginInterceptor:preHandle:request ip :{}===============", CommonUtils.getIP(request));

        if (request.getHeader(HttpHeaders.ORIGIN) != null) {
            response.addHeader("Access-Control-Allow-Origin", "*");
//            response.addHeader("Access-Control-Allow-Origin", "http://172.18.188.183:8020");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "3600");
        }

        String requestUrl = request.getRequestURI();
        if(ignorelist.contains(requestUrl)){
            return true;
        }

        Cookie[] cookie = request.getCookies();
        if(cookie != null){
            for(int i = 0;i<cookie.length;i++){
                logger.info("Cookie {} : {}={}",i,cookie[i].getName(),cookie[i].getValue());
            }
        }else{
            logger.info("no cookie");
        }


        UserSession session = (UserSession) request.getSession().getAttribute("userSession");
        logger.info("Login Session : {}",session);

//        if(session == null){
//            PrintWriter out = response.getWriter();
//            out.write(ErrCodeConstant.LOGIN_FAIL_NO_PERMISSION);
//            return false;
//        }

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        logger.info("=================LoginInterceptor:postHandle===============");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.info("=================LoginInterceptor:afterCompletion===============");
    }


}
