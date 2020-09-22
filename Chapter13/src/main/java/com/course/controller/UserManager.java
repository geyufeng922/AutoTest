package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Log4j
@RestController
@Api(value="v1",description="用户管理系统")
@RequestMapping("v1")
public class UserManager {
    //访问数据库得对象，自己就把数据库new出来了；
    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口", httpMethod = "POST")
    public Boolean login(HttpServletResponse response,
                         @RequestBody User user) {
        //上@：入参
        //查询数据库当中看是否有
        int i = template.selectOne("login", user);
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        log.info("查询到的结果是" + i);
        if (i == 1) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户接口", httpMethod = "POST")
    public boolean addUser(HttpServletRequest request,
                           @RequestBody User user) {
        Boolean x = verIfyCookies(request);
         int result=0;
         if(x!=null){
             template.insert("addUser",user);
         }
         if(result>0){
             log.info("添加用户得数量是："+result);
             return true;
         }
         return false;
    }
    @RequestMapping(value="/getUserInfo",method = RequestMethod.POST)
    @ApiOperation(value="获取用户(列表)信息接口",httpMethod = "POST")
    //把
    public List<User> getUserInfo(HttpServletRequest request,@RequestBody User user){
        Boolean x=verIfyCookies(request);
        if(x==true){
            //去数据库查
            List<User> users=template.selectList("getUserInfo",user);
            log.info("getUserInfo获取到的用户数量是"+users.size());
            return users;
        }
        else {
            return null;
        }
    }

    @RequestMapping(value="/updateUserInfo",method = RequestMethod.POST)
    @ApiOperation(value="更新/删除用户接口",httpMethod = "POST")
    private int updateUser(HttpServletRequest request,@RequestBody User user){
        Boolean x=verIfyCookies(request);
        int i=0;
        if(x==true){
            //触发这条SQL语句，把user扔进去，自己更新就OK啦
            i=template.update("updateUserInfo",user);
        }
        log.info("更新用户得条数为："+i);
        return i;
    }

    private Boolean verIfyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            log.info("cookies为空");
            return false;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login")) {
                if (cookie.getValue().equals("true")) {
                    log.info("cookies验证通过");
                    return true;
                }
            }
        }
        return false;
    }
}

