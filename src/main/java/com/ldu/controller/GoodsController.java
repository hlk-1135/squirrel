package com.ldu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.io.File;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ldu.pojo.Image;
import com.ldu.pojo.User;
import com.ldu.service.CatelogService;
import com.ldu.service.ImageService;
import com.ldu.service.UserService;
import com.sun.tracing.dtrace.Attributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ldu.pojo.Catelog;
import com.ldu.pojo.Goods;
import com.ldu.service.GoodsService;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CatelogService catelogService;
    @Autowired
    private UserService userService;

    /**
     * 首页显示商品，每一类商品查询6件，根据最新上架排序 key的命名为catelogGoods1、catelogGoods2....
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/homeGoods")
    public ModelAndView homeGoods() throws Exception {
        List<Goods> goods = null;

        ModelAndView modelAndView = new ModelAndView();
        //商品种类数量
        int catelogSize = 7;
        //每个种类显示商品数量
        int goodsSize = 6;
        for (int i = 1; i <= catelogSize; i++) {
            goods = goodsService.getGoodsByCatelogOrderByDate(i, goodsSize);
            String key = "catelog" + "Goods" + i;
            modelAndView.addObject(key, goods);
        }
        modelAndView.setViewName("goods/homeGoods");
        return modelAndView;
    }

    /**
     * 查询该类商品
     *
     * @param catelog
     * 要求该参数不为空
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/catelogGoods")
    public ModelAndView catelogGoods(
            @RequestParam(value = "catelog", required = true) Catelog catelog)
            throws Exception {
        List<Goods> goods = goodsService.getGoodsByCatelog(catelog);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("goods", goods);
        modelAndView.setViewName("goods/homeGoods");
        return modelAndView;
    }

    /**
     * 修改商品信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editGoods")
    public ModelAndView editGoods(Integer goodsId) throws Exception {

        Goods goods = goodsService.getGoodsByPrimaryKey(goodsId);
        ModelAndView modelAndView = new ModelAndView();

        // 将商品信息添加到model
        modelAndView.addObject("goods", goods);

        modelAndView.setViewName("goods/editGoods");

        return null;
    }

    /**
     * 提交商品更改信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editGoodsSubmit")
    public String editGoodsSubmit(Integer goodsId, Goods goods) throws Exception {
        goodsService.updateGoodsByPrimaryKeyWithBLOBs(goodsId, goods);
        return "/goods/homeGoods";
    }

    /**
     * 商品下架
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/offGoods")
    public ModelAndView offGoods() throws Exception {

        return null;
    }

    /**
     * 管理员删除商品
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteGoods")
    public ModelAndView deleteGoods() throws Exception {

        return null;
    }
    /**
     * 发布商品
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publishGoods")
    public String publishGoods(HttpServletRequest request) {
        //可以校验用户是否登录
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        if(cur_user == null) {
            return "/goods/homeGoods";
        } else {
            return "/goods/pubGoods";
        }

    }
    /**
     * 提交发布的商品信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/publishGoodsSubmit")
    public void publishGoodsSubmit(HttpServletRequest request,HttpServletResponse response,Image ima,Goods goods,MultipartFile image)
            throws Exception {
        //查询出当前用户cur_user对象，便于使用id
        User cur_user = (User)request.getSession().getAttribute("cur_user");
        goods.setUserId(cur_user.getId());
        int i = goodsService.addGood(goods,10);//在goods表中插入物品
        //返回插入的该物品的id
        int goodsId = goods.getId();
        ima.setGoodsId(goodsId);
        imageService.insert(ima);//在image表中插入商品图片
        //发布商品后，catlog的number+1，user表的goods_num+1，更新session的值
        Integer calelog_id = goods.getCatelogId();
        catelogService.updateCatelogNum(calelog_id);
        userService.updateGoodsNum(cur_user.getId());
        cur_user.setGoodsNum(cur_user.getGoodsNum()+1);
        request.getSession().setAttribute("cur_user",cur_user);//修改session值
        checkUpIsOk(i,response);
    }

    @RequestMapping(value = "/goodsInfo")
    public ModelAndView goodsInfo(Integer goodsId){
        ModelAndView modelAndView = new ModelAndView();
        Goods goods = goodsService.getGoodsByPrimaryKey(2);
        modelAndView.addObject("goods",goods);
        modelAndView.setViewName("/goods/detailGoods");
        return modelAndView;
    }
    //用于插入是否成功
    public void checkUpIsOk(int i,HttpServletResponse response) throws IOException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();//获取PrintWriter输出流
        if(i==0){
            out.write("Sorry，发布失败");
            out.write("<script>setTimeout(function(){"+
                    "history.go(-1);"+
                    "},500) </script>");
            out.close();
        }else{
            out.write("恭喜你，发布成功");
            out.write("<script>setTimeout(function(){"+
                    "location.href='/user/goods'"+
                    "},500) </script>");
            out.close();
        }
    }
    //uploadFile
    @ResponseBody
    @RequestMapping(value = "/uploadFile")
    public  Map<String,Object> uploadFile(HttpSession session,MultipartFile myfile) throws IllegalStateException, IOException{
        //原始名称
        String oldFileName = myfile.getOriginalFilename(); //获取上传文件的原名
        //存储图片的物理路径
        String file_path = session.getServletContext().getRealPath("upload");
        //上传图片
        if(myfile!=null && oldFileName!=null && oldFileName.length()>0){
            //新的图片名称
            String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
            //新图片
            File newFile = new File(file_path+"/"+newFileName);
            //将内存中的数据写入磁盘
            myfile.transferTo(newFile);
            //将新图片名称返回到前端
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("success", "成功啦");
            map.put("imgUrl",newFileName);
            return  map;
        }else{
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("error","图片不合法");
            return map;
        }
    }
}