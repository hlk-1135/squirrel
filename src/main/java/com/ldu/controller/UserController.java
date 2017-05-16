package com.ldu.controller;

import com.ldu.pojo.Goods;
import com.ldu.pojo.GoodsExtend;
import com.ldu.pojo.User;
import com.ldu.service.GoodsService;
import com.ldu.service.ImageService;
import com.ldu.util.DateUtil;
import com.ldu.util.MD5;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ldu.pojo.Image;
import com.ldu.service.UserService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private GoodsService goodsService;
    @Resource
    private ImageService imageService;

    /**
     * 用户注册
     * @param user1
     * @return
     */
    @RequestMapping(value = "/addUser")
    public String addUser(HttpServletRequest request,@ModelAttribute("user") User user1) {
        String url=request.getHeader("Referer");
        User user=userService.getUserByPhone(user1.getPhone());
        if(user==null) {//检测该用户是否已经注册
            String t = DateUtil.getNowDate();
            //对密码进行MD5加密
            String str = MD5.md5(user1.getPassword());
            user1.setCreateAt(t);//创建开始时间
            user1.setPassword(str);
            user1.setGoodsNum(0);
            userService.addUser(user1);
        }
        return "redirect:"+url;
    }

    /**
     * 验证登录
     * @param request
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/login")
    public ModelAndView loginValidate(HttpServletRequest request, HttpServletResponse response,User user, ModelMap modelMap) {
        User cur_user = userService.getUserByPhone(user.getPhone());
        String url=request.getHeader("Referer");
        if(cur_user != null) {
            String pwd = MD5.md5(user.getPassword());
            if(pwd.equals(cur_user.getPassword())) {
                request.getSession().setAttribute("cur_user",cur_user);
                return new ModelAndView("redirect:"+url);
            }
        }
        return new ModelAndView("redirect:"+url);
    }

    /**
     * 更改用户名
     * @param request
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/changeName")
    public ModelAndView changeName(HttpServletRequest request,User user,ModelMap modelMap) {
        String url=request.getHeader("Referer");
        //从session中获取出当前用户
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        cur_user.setUsername(user.getUsername());//更改当前用户的用户名
        userService.updateUserName(cur_user);//执行修改操作
        request.getSession().setAttribute("cur_user",cur_user);//修改session值
        return new ModelAndView("redirect:"+url);
    }

    /**
     * 完善或修改信息
     * @param request
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/updateInfo")
    public ModelAndView updateInfo(HttpServletRequest request,User user,ModelMap modelMap) {
        //从session中获取出当前用户
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        cur_user.setUsername(user.getUsername());
        cur_user.setQq(user.getQq());
        userService.updateUserName(cur_user);//执行修改操作
        request.getSession().setAttribute("cur_user",cur_user);//修改session值
        return new ModelAndView("redirect:/user/basic");
    }
    /**
     * 用户退出
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute("cur_user",null);
        return "redirect:/goods/homeGoods";
    }


    /**
     * 个人中心
     * @return
     */
    @RequestMapping(value = "/home")
    public String home() {
        return "/user/home";
    }

    /**
     * 个人信息设置
     * @return
     */
    @RequestMapping(value = "/basic")
    public String basic() {
        return "/user/basic";
    }

    /**
     * 我的闲置
     * 查询出所有的用户商品以及商品对应的图片
     * @return  返回的model为 goodsAndImage对象,该对象中包含goods 和 images，参考相应的类
     */
    @RequestMapping(value = "/allGoods")
    public ModelAndView goods(HttpServletRequest request) {
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        Integer userId = cur_user.getId();
        List<Goods> goodsList = goodsService.getGoodsByUserId(userId);
        List<GoodsExtend> goodsAndImage = new ArrayList<GoodsExtend>();
        for (int i = 0; i < goodsList.size() ; i++) {
            //将用户信息和image信息封装到GoodsExtend类中，传给前台
            GoodsExtend goodsExtend = new GoodsExtend();
            Goods goods = goodsList.get(i);
            List<Image> images = imageService.getImagesByGoodsPrimaryKey(goods.getId());
            goodsExtend.setGoods(goods);
            goodsExtend.setImages(images);
            goodsAndImage.add(i, goodsExtend);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("goodsAndImage",goodsAndImage);
        mv.setViewName("/user/goods");
        return mv;
    }
}