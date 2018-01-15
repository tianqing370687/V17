package com.xiongmengyingshi.v17.controller.vo;

import com.xiongmengyingshi.v17.entity.PersonalInfo;

/**
 * Created by nicholas.chi on 2018/1/15.
 */
public class GetPersonalInfoByIdVO extends BaseVO {

    private PersonalInfo personalInfo;

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

}
