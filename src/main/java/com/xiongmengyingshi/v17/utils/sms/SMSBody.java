package com.xiongmengyingshi.v17.utils.sms;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by ubuntu on 18-1-11.
 */
public class SMSBody {

    private String username;
    private long time;
    private String content;
    private String mobile;
    private String authkey;
    private int type;
    private String password;
    private String key;

    public SMSBody(String username,String content,
                   String mobile, int type,String password,
                   String key) throws UnsupportedEncodingException {
        this.username = username;
        this.time = new Date().getTime();
        this.content = URLEncoder.encode(
                new String(content.getBytes(),"UTF-8"));
        this.mobile = mobile;
        this.authkey = DigestUtils.md5Hex(
                username+this.time+
                        DigestUtils.md5Hex(password)+key);
        this.type = type;
    }

}
