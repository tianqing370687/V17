package com.xiongmengyingshi.v17.service.impl;

import com.xiongmengyingshi.v17.dao.PersonalInfoMapper;
import com.xiongmengyingshi.v17.entity.PersonalInfo;
import com.xiongmengyingshi.v17.service.PersonalInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by nicholas.chi on 2018/1/13.
 */
@Service
@Transactional
public class PersonalInfoServiceImpl implements PersonalInfoService {

    @Autowired
    PersonalInfoMapper personalInfoMapper;

    public void savePersonalInfo(PersonalInfo personalInfo){
        personalInfoMapper.insert(personalInfo);
    }



}
