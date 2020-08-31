package com.course.testng.suite;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

//进行所有测试套件配置的，写测试套件之前需要运行的方法，共有的
public class SuiteConfig {
    @BeforeSuite
    public void BeforeSuite(){
        System.out.println("before suite 运行啦");
    }
    @AfterSuite
    public void AfterSuite(){
        System.out.println("After suite 运行啦");
    }
    @BeforeTest
    public void BeforeTest(){
        System.out.println("beforetest测试前");
    }
    @AfterTest
    public void AfterTest(){
        System.out.println("aftertest测试后");
    }
}
