package com.xiongmengyingshi.v17.dao;

import com.xiongmengyingshi.v17.entity.PersonalInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component(value = "personalInfoMapper")
public interface PersonalInfoMapper {


    int insert(PersonalInfo record);

    Integer selectInfoIdByParam(@Param("phoneNum")String phoneNum);

    PersonalInfo selectByPrimaryKey(Integer infoId);

    int updateByPrimaryKey(PersonalInfo record);

    List<PersonalInfo> listPersonalInfoByPage(Map<String,Object> data);

    List<PersonalInfo> listPersonalInfoByAll();

    int deleteByPrimaryKey(Integer infoId);

    int insertSelective(PersonalInfo record);

    int updateByPrimaryKeySelective(PersonalInfo record);


}