package com.xiongmengyingshi.v17.dao;

import com.xiongmengyingshi.v17.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "adminMapper")
public interface AdminMapper {

    Admin selectByAdminName(String admin);

    int deleteByPrimaryKey(Long adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}