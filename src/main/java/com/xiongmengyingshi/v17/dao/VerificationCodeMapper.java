package com.xiongmengyingshi.v17.dao;

import com.xiongmengyingshi.v17.entity.VerificationCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component("verificationCodeMapper")
public interface VerificationCodeMapper {
    int deleteByPrimaryKey(String phoneNum);

    int insert(VerificationCode record);

    int insertSelective(VerificationCode record);

    VerificationCode selectByPrimaryKey(String phoneNum);

    int updateByPrimaryKeySelective(VerificationCode record);

    int updateByPrimaryKey(VerificationCode record);
}