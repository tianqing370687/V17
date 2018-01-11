package com.xiongmengyingshi.v17.controller;

import com.xiongmengyingshi.v17.entity.Admin;
import com.xiongmengyingshi.v17.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by nicholas.chi on 2018/1/11.
 */
@Controller
public class TestController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "test",method = RequestMethod.GET)
    public String test(){
        System.out.println("=============Hello World!==============");

        Admin admin = new Admin();
        admin.setAdminName("admin");
        admin.setAdminPassword("123123123");
        admin.setPasswordSalt("123");
        admin.setLoginTime(new Date());
        admin.setLoginIP("222");

        System.out.println(admin.toString());

        adminService.saveAdmin(admin);

        return "HelloWorld";
    }

}
