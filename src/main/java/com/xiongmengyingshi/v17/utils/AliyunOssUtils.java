package com.xiongmengyingshi.v17.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by nicholas.chi on 2018/1/17.
 */
public class AliyunOssUtils {

    private OSS ossClient;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

    private static Logger logger = LogManager.getLogger(AliyunOssUtils.class);

    public AliyunOssUtils(){
        this.endpoint=ResourceUtils.getBundleValue4String("ali.endpoint");
        this.accessKeyId=ResourceUtils.getBundleValue4String("ali.accesskey.id");
        this.accessKeySecret=ResourceUtils.getBundleValue4String("ali.accesskey.secret");
        this.bucketName=ResourceUtils.getBundleValue4String("ali.bucketname");
        ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
    }

    public boolean uploadFile(String key, File file){
        logger.info("Getting Started with OSS SDK for Java");
        if(!ossClient.doesBucketExist(bucketName)){
            logger.info("Creating bucket : {}",bucketName);
            ossClient.createBucket(bucketName);
            CreateBucketRequest createBucketRequest= new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            ossClient.createBucket(createBucketRequest);
        }

        logger.info("Uploading a new object to OSS from a file");
        ossClient.putObject(new PutObjectRequest(bucketName, key, file));

        ossClient.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);

        boolean exists = ossClient.doesObjectExist(bucketName, key);
        return exists;
    }

    public static void main(String[] args){
        AliyunOssUtils utils = new AliyunOssUtils();
        boolean exits = utils.uploadFile("xxxx.png",new File("C:\\Users\\nicholas.chi\\Desktop\\TIM截图20171031115902.png"));
        if(exits){
            System.out.println("success==============================");
//            logger.info("success");
        }else{
            System.out.println("fail==============================");
//            logger.info("fail");
        }
    }





    public OSS getOssClient() {
        return ossClient;
    }

    public void setOssClient(OSS ossClient) {
        this.ossClient = ossClient;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

}
