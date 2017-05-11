package com.ldu.service;

import com.ldu.pojo.User;

public interface UserService {
    public void addUser(User user);
    public User getUserByPhone(String phone);
    public void updateUserName(User user);
}