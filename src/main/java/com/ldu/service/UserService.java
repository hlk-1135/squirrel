package com.ldu.service;

import com.ldu.pojo.User;

import java.util.List;

public interface UserService {
    public void addUser(User user);
    public User getUserByPhone(String phone);
    public void updateUserName(User user);
    int updateGoodsNum(Integer id,Integer goodsNum);
    User selectByPrimaryKey(Integer id);
    public List<User> getPageUser(Integer page,Integer pageSize,String username);
    public int getUserNum(String username);
}