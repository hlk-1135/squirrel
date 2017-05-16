package com.ldu.controller;

import com.ldu.pojo.User;
import com.ldu.util.UserGrid;
import com.ldu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by lenovo on 2017/5/9.
 */
@Controller
public class MainController {

    @Resource
    private UserService userService;
    @RequestMapping(value = "/api/v1/users")
    @ResponseBody
    public UserGrid getUserList(@RequestParam(value = "page",required = false) Integer page,
                                @RequestParam(value = "pageSize",required = false) Integer pageSize,
                                @RequestParam(value = "username",required = false) String username) {
        System.out.println("username:"+username);
        int total = userService.getUserNum(username);
        String pageStr = page + "";
        String pageSizeStr = pageSize + "";
        if("".equals(pageStr))
            page = 1;
        if("".equals(pageSizeStr))
            pageSize = 10;
        List<User> data = userService.getPageUser(page,pageSize,username);
        System.out.println("data:"+data.size());
        UserGrid userGrid = new UserGrid();
        userGrid.setData(data);
        userGrid.setTotal(total);
        return userGrid;
    }
}
