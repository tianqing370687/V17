package com.xiongmengyingshi.v17.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;

/**
 * Created by nicholas.chi on 2018/1/12.
 */
public class SMSUtils {


    public static final String[] resArr = {"","成功","参数不正确","密钥验证失败",
            "用户名或密码错误","服务器内部失败","余额不足","内容不符合格式",
            "频率超限","接口超时","后缀名长度超过限制","其他错误",
            "签名或内容格式不通过","一个手机号码一天内超过5次","暴力破解被封1小时"};


    /**
     * 向指定 URL 发送POST方法的请求
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {

        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String senaSMS(String mobile,String smsContent) throws ParseException {

        String username = ResourceUtils.getBundleValue4String("sms.username");
        String password = ResourceUtils.getBundleValue4String("sms.password");
        String key = ResourceUtils.getBundleValue4String("sms.key");

        String body = java.net.URLEncoder.encode(smsContent);
        String suffix = java.net.URLEncoder.encode("V17");
        String content = body + java.net.URLEncoder.encode("【") + suffix + java.net.URLEncoder.encode("】");

        long time = CommonUtils.getGMTTime();//获取格林尼治时间
        String authkey = CommonUtils.getMD5Str(username+time+ CommonUtils.getMD5Str(password)+key);

        String param = "username="+username+"&time="+time+"&content="+content+"&mobile="+mobile+"&authkey="+authkey;
        System.out.println("send param is :" + param);

        String result = sendPost("http://sms.edmcn.cn/api/cm/trigger_mobile.php", param);
        System.out.println("send sms result : " + result);

        return result;
    }

}
