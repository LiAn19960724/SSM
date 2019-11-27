package com.insistla.ssm.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.insistla.ssm.pojo.Page;
import com.insistla.ssm.pojo.User;
import com.insistla.ssm.redis.RedisCache;
import com.insistla.ssm.service.PageService;
import com.insistla.ssm.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private PageService pageService;

    @Resource
    private RedisCache redisCache;


    //log4j日志
    private static Logger logger = LogManager.getLogger();

    //SSM测试
    @RequestMapping(value = "/login")
    public String addUser(Model model, User user){
        System.out.println("进来了");
        System.out.println(user);
        return "404.jsp";
    }


    //缓存测试
    @RequestMapping(value = "/Redis")
    public String getRedis(Model model){
        //测试根据key获取缓存里面的值
        String key = "user";
        String data = redisCache.getDataFromRedis(key);
        logger.info(data);
        //先去缓存里面拿数据,如果缓存里面没有，再去数据库里拿数据，拿完后，就把数据转成json字符串存入缓存
        //如果缓存里面有，就把拿出来的json字符串转成对象或集合就OK了
        // 对象|集合  转json:         JSON.toJSONString();
        // json字符串转成对象|集合：   JSON.parseArray(data , User.class);
        return "404.jsp";
    }



    //分页测试
    @ResponseBody  //Ajax返回json数据
    @RequestMapping(value = "/Page",produces = "application/json;charset=utf-8") //@Responsebody需要加produces = "application/json;charset=utf-8"解决乱码问题
    public String page(Integer pageNum, Integer pageSize){
        //System.out.println(pageNum+"-----"+pageSize);
        //1.引用分页插件,传入当前页和每页记录数(大坑：必需写在要查询的数据前面)
        PageHelper.startPage(pageNum,pageSize);
        //2.调用查询数据方法
        List<Page> la = pageService.selectPage();
        //System.out.println(la);    //此时输出的不是集合数据
        //System.out.println(JSON.toJSONString(la));//转成json之后输出的是分页后的集合数据
        //3.把集合传入PageInfo分页工具类
        PageInfo<Page> pageInfo = new PageInfo<>(la);
        //System.out.println(pageInfo.toString());//输出工具类的详细信息
        //System.out.println(JSON.toJSONString(pageInfo));//转成json可以看到分页后的详细数据，包括总页数、总记录数等等.....
        //{"endRow":9,"firstPage":1,"hasNextPage":true,"hasPreviousPage":true,"isFirstPage":false,"isLastPage":false,"lastPage":8,"list":[{"pageAge":19,"pageId":7,"pageName":"张三7","pageSex":"男"},{"pageAge":19,"pageId":8,"pageName":"张三8","pageSex":"男"},{"pageAge":19,"pageId":9,"pageName":"张三9","pageSex":"男"}],"navigateFirstPage":1,"navigateLastPage":8,"navigatePages":8,"navigatepageNums":[1,2,3,4,5,6,7,8],"nextPage":4,"pageNum":3,"pageSize":3,"pages":8,"prePage":2,"size":3,"startRow":7,"total":22}
        //firstPage:首页；    prePage：上一页；nextPage：下一页；pages:末页
        String pages = JSON.toJSONString(pageInfo);
        return pages;
    }


    //shiro验证测试：
    @RequestMapping(value = "/doLogin")
    public String logins(String userName,String userPassword){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, userPassword);
        try {
            //执行认证操作.
            subject.login(token);
            System.out.println("认证成功");
        }catch (AuthenticationException E) {
            System.out.println("登陆失败: " + E.getMessage());
            return "404.jsp";
        }
        return "index.jsp";
    }



}
