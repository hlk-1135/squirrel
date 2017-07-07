package com.ldu.controller;

import com.ldu.pojo.User;
import com.ldu.util.UserGrid;
import com.ldu.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;

/**
 * Created by lenovo on 2017/5/9.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/userList",method = RequestMethod.GET)
    public String userList() {
        return "/admin/userList";
    }

    @RequestMapping(value="/getUserInfo",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public User getUserById(@RequestParam("userId") int userId){
        User user = userService.selectByPrimaryKey(userId);
        return user;
    }

    @RequestMapping(value = "/users",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public UserGrid getUserList(@RequestParam("current") int current,@RequestParam("rowCount") int rowCount) {
        int total = userService.getUserNum();
        List<User>  list = userService.getPageUser(current,rowCount);
        UserGrid userGrid = new UserGrid();
        userGrid.setCurrent(current);
        userGrid.setRowCount(rowCount);
        userGrid.setRows(list);
        userGrid.setTotal(total);
        return userGrid;
    }
    //将用户信息导出到Excel
    @RequestMapping("/exportUser")
    public void export(HttpServletResponse response) throws Exception{
        InputStream is=userService.getInputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("contentDisposition", "attachment;filename=AllUsers.xls");
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(is,output);
    }
}