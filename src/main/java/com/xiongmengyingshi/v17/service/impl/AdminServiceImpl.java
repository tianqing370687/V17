package com.xiongmengyingshi.v17.service.impl;

import com.xiongmengyingshi.v17.dao.AdminMapper;
import com.xiongmengyingshi.v17.entity.Admin;
import com.xiongmengyingshi.v17.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nicholas.chi on 2018/1/11.
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    public void saveAdmin(Admin admin){
        adminMapper.insert(admin);
    }

    public Admin selectByPrimaryKey(Long adminId){
        return adminMapper.selectByPrimaryKey(adminId);
    }

}
