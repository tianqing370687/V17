package com.xiongmengyingshi.v17.service;

import com.xiongmengyingshi.v17.entity.PersonalInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by nicholas.chi on 2018/1/13.
 */
public interface PersonalInfoService {

    String savePersonalInfo(PersonalInfo personalInfo);

    Integer getPersonalInfoId(String name,String phoneNum);

    void saveFileAndUpdateInfo(Integer userId, MultipartFile video1,
                               MultipartFile video2,MultipartFile mugShotImg,MultipartFile halfLengthImg,MultipartFile fullBodyImg);

    List<PersonalInfo> listPersonalInfoByPage(int currPage, int pageSize);

    PersonalInfo getPersonlInfoById(int infoId);

    List<PersonalInfo> listPersonalInfoByAll();

    String getSerialNum(Integer infoId);

    int deletePersonalInfobyId(int infoId);

}
