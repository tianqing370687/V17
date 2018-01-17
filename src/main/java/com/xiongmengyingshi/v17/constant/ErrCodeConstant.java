package com.xiongmengyingshi.v17.constant;

/**
 * Created by nicholas.chi on 2018/1/13.
 */
public class ErrCodeConstant {

    /*
    * 短信发送失败
    * */
    public static final String SMS_SEND_FAIL = "1000";
    /*
    * 短信发送成功
    * */
    public static final String SMS_SEND_SUCESS = "1001";
    /*
    * 验证码验证失败
    * */
    public static final String TEST_CODE_FAIL = "1002";
    /*
    * 验证码验证成功
    * */
    public static final String TEST_CODE_SUCCESS = "1003";
    /*
    * 保存报名信息成功
    * */
    public static final String ENROLL_INFO_SUCCESS = "1004";
    /*
    * 保存报名信息失败
    * */
    public static final String ENROLL_INFO_FAIL_USER_EXIT = "1005";
    /*
    * 登录成功
    * */
    public static final String LOGIN_SUCCESS = "2000";
    /*
    * 登陆失败（无此用户）
    * */
    public static final String LOGIN_FAIL_WRONG_USERNAME = "2001";
    /*
    * 登录失败（密码错误）
    * */
    public static final String LOGIN_FAIL_WRONG_PASSWORD = "2002";
    /*
    * 修改密码成功
    * */
    public static final String CHANGE_PASSWORD_SUCCESS = "2003";
    /*
    *修改密码失败
    * */
    public static final String CHANGE_PASSWORD_FAIL = "2004";
    /*
    *注册成功
    * */
    public static final String REGISTERED_SUCCESS = "2005";
    /*
    *注册失败（用户名已存在）
    * */
    public static final String REGISTERED_FAIL_NAME_EXITED = "2006";
    /*
     *退出登录成功
     * */
    public static final String EXIT_SUCCESS = "2007";
    /*
     *无权限访问，请登录
     * */
    public static final String LOGIN_FAIL_NO_PERMISSION= "2008";








}
