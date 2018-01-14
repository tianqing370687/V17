package com.xiongmengyingshi.v17.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by nicholas.chi on 2018/1/13.
 */
public class CommonUtils {

    /**
     * MD5 加密
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();
    }

    public static long getGMTTime() throws ParseException {
        final int msInMin = 60000;
        final int minInHr = 60;
        Date date = new Date();
        int Hours, Minutes;
        DateFormat dateFormat =new SimpleDateFormat("yyyyMMddHHmmss");// DateFormat.getDateTimeInstance(DateFormat.LONG,  DateFormat.LONG);
        TimeZone zone = dateFormat.getTimeZone();
        System.out.println("IST Time: " + dateFormat.format(date));
        Minutes = zone.getOffset(date.getTime()) / msInMin;
        Hours = Minutes / minInHr;
        System.out.println("GMT Time" + (Hours >= 0 ? "+" : "") + Hours + ":" + Minutes);
        zone = zone.getTimeZone("GMT Time" + (Hours >= 0 ? "+" : "") + Hours + ":" + Minutes);
        dateFormat.setTimeZone(zone);

        String  time = dateFormat.format(date);
        System.out.println("GMT: " + time);

        long Seconds = 0;
        try {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmss");
            Seconds = simpledateformat.parse(time).getTime()/1000;
            System.out.println(Seconds);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//毫秒

        return Seconds;
    }

    public static String getRandomCode(int length){
        double random = (Math.random()*9+1)*Math.pow(10,length);
        return Double.toString(random).substring(0,length-1);
    }

    public static String strFormat(String param,int length){
        if(param.length() >= length){
            return null;
        }
        String str1 = reverse(param);
        for (int i = 1;i<=length - param.length();i++){
            str1 = str1 + "0";
        }

        return reverse(str1);
    }

    public static String reverse(String input){
        StringBuffer sb = new StringBuffer();
        char[] arr = input.toCharArray();

        for (int i = arr.length-1; i>=0; i--){
            sb.append(arr[i]);
        }

        return sb.toString();
    }

    public static void main(String[] args){

//        System.out.println(getRandomCode(6));

        System.out.println(reverse("12345"));
    }

}
