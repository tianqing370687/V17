package com.xiongmengyingshi.v17.controller.vo;

import com.xiongmengyingshi.v17.entity.PersonalInfo;

import java.util.List;

/**
 * Created by nicholas.chi on 2018/1/15.
 */
public class ListPersonalInfoByPageVO extends BaseVO {

    private int currPage;
    private int pageSize;
    private List<PersonalInfo> list;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<PersonalInfo> getList() {
        return list;
    }

    public void setList(List<PersonalInfo> list) {
        this.list = list;
    }

}
