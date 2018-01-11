package com.xiongmengyingshi.v17.dao;

import com.xiongmengyingshi.v17.entity.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Created by nicholas.chi on 2018/1/11.
 */
@Mapper
@Component(value = "adminMapper")
public interface AdminMapper {

//    @Insert("insert into v17_admin(admin_name,admin_password,password_salt,login_time,login_ip) " +
//            "values (#{adminName},#{adminPassword},#{passwordSalt},#{loginTime},#{loginIP})")
//    void saveAdmin(Admin admin);

    int deleteByPrimaryKey(Long adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

}
