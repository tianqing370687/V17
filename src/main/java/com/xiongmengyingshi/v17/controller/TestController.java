package com.xiongmengyingshi.v17.controller;

import com.xiongmengyingshi.v17.utils.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

/**
 * Created by nicholas.chi on 2018/1/11.
 */
@Controller
public class TestController {

    Logger logger = LogManager.getLogger(EnrollController.class);

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        return "HelloWorld";
    }

}
