package com.ldu.service.impl;

import com.github.pagehelper.PageHelper;
import com.ldu.dao.UserMapper;
import com.ldu.pojo.User;
import com.ldu.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public void addUser(User user) {
        userMapper.insert(user);
    }

    public User getUserByPhone(String phone) {
        User user  = userMapper.getUserByPhone(phone);
        return  user;
    }

    public void updateUserName(User  user) {
        userMapper.updateByPrimaryKey(user);
    }

    public int updateGoodsNum(Integer id,Integer goodsNum) {
        return userMapper.updateGoodsNum(id,goodsNum);
    }

    public User selectByPrimaryKey(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    public List<User> getPageUser(Integer page, Integer pageSize,String username) {
        PageHelper.startPage(page,pageSize);
        List<User> data= userMapper.getUserList(username);
        System.out.println("UserServiceImpl:"+data.size());
        return data;
    }

    public int getUserNum(String username) {
        List<User> users = userMapper.getUserList(username);
        return users.size();
    }
}