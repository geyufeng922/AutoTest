package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Log4j
@RestController
@Api(value="v1",description="这是我得第一个版本的demo")
@RequestMapping("/v1")
public class Demo {
    //首先获取一个执行sql语句的对象
    @Resource
    private SqlSessionTemplate template;
    @RequestMapping(value="/getUserCount",method = RequestMethod.POST)
    @ApiOperation(value="可以获取的用户数",httpMethod = "GET")
    public String getUserCount(){
    return  "查询到的用户总数："+template.selectOne("getUserCount");

    }
    @RequestMapping(value="/addUser",method = RequestMethod.POST)
    public int addUser(@RequestBody User user){
        int result=template.insert("addUser",user);
        return result;
     }
     @RequestMapping(value="/updateUser",method = RequestMethod.POST)
     public int updateUser(@RequestBody User user){

        return template.update("updateUser",user);

     }
     @RequestMapping(value="/deleteUser",method = RequestMethod.GET)
     public int deleteUser(@RequestParam int id){
        return template.delete("deleteUser",id);
     }

}
