package com.ldu.util;

import com.ldu.pojo.User;

import java.util.List;

/**
 * 用户分页查询工具类
 * Created by lenovo on 2017/5/14.
 */
public class UserGrid {

    private List<User> data;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }
}
