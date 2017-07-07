package com.ldu.util;

import com.ldu.pojo.User;

import java.util.List;

/**
 * 用户分页查询工具类
 * Created by lenovo on 2017/5/14.
 */
public class UserGrid {

    private int current;//当前页面号
    private int rowCount;//每页行数
    private int total;//总行数
    private List<User> rows;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<User> getRows() {
        return rows;
    }

    public void setRows(List<User> rows) {
        this.rows = rows;
    }
}
