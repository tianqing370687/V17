package com.xiongmengyingshi.v17.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ubuntu on 18-1-14.
 */
public class FileUtils {

    private static Logger logger = LogManager.getLogger(FileUtils.class);

    public static String uploadFile(String type, long infoId, MultipartFile file) throws IOException {
        if(file == null ||file.isEmpty()){
            logger.info("the uploadfile is empty!");
            return null;
        }
        String basePath = "src/main/webapp/UploadFile/"+type;
        String fileName = type+getFileName(file.getOriginalFilename(),infoId);
        String path = basePath+File.separator+fileName;
        logger.info("the path of the upload file is {}" ,path);
        File localFile = new File(path);

        if(!localFile.exists()) {
            new File(basePath).mkdirs();
        }

        file.transferTo(localFile);

        return path;
    }

    private static String getFileName(String originalFilename, long infoId){
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String fileName = (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))+infoId+"."+suffix;
        logger.info("the name of the upload file is {}",fileName);
        return fileName;
    }

    public static void main(String[] args){
    }



}
