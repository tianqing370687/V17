package com.xiongmengyingshi.v17.dao;

import com.xiongmengyingshi.v17.entity.PersonalInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "personalInfoMapper")
public interface PersonalInfoMapper {
    int deleteByPrimaryKey(Integer infoId);

    int insert(PersonalInfo record);

    int insertSelective(PersonalInfo record);

    PersonalInfo selectByPrimaryKey(Integer infoId);

    PersonalInfo selectByPhoneNum(String phoneNum);

    int updateByPrimaryKeySelective(PersonalInfo record);

    int updateByPrimaryKey(PersonalInfo record);
}