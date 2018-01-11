package com.xiongmengyingshi.v17.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ubuntu on 18-1-11.
 */
public class HttpUtils {

    public static String sendGetRequest(String urlString){

        String result = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent","Mozilla/5.0");

            int responseCode = con.getResponseCode();
            System.out.println("Sending get request : "+ url);
            System.out.println("Response code : "+ responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null){
                response.append(output);
            }

            in.close();
            result = response.toString();
            System.out.println("Response result : "+ result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String sendPostRequest(String urlString,String param){
        String result = null;

        try {
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent","Mozilla/5.0");
//            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Content-Type","application/json");
            con.setDoOutput(true);

            DataOutputStream dataOutputStream = new DataOutputStream(con.getOutputStream());
            dataOutputStream.write(param.getBytes());
            dataOutputStream.flush();
            dataOutputStream.close();

            int responseCode = con.getResponseCode();

            System.out.println("nSending 'POST' request to URL : " + url);
            System.out.println("Post Data : " + param);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();

            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();

            result = response.toString();
            System.out.println("Response result : "+ result);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

    public static void main(String[] args){
        String result = sendGetRequest("http://www.baidu.com");

    }



}
