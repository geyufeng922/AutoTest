package com.course.testng;
import org.testng.annotations.*;

public class BasicAnnotation {
    @Test
    public void testCase1() {
        System.out.println("这是测试用例1");
    }

    @Test
    public void testCase2() {
        System.out.printf("Thread Id : %s%n",Thread.currentThread().getId());
        System.out.println("这是测试用例2");
    }

    @BeforeMethod
    public void BeforMethod() {
        System.out.println("在方法前运行的用例");
    }

    @AfterMethod
    public void AfterMethod() {
        System.out.println("在方法后运行的用例");
    }

    @BeforeClass
    public void BeforClass() {
        System.out.println("在类前运行的用例");
    }

    @AfterClass
    public void AfterClass() {
        System.out.println("在类后运行的用例");
    }

    @BeforeSuite
    public void BeforSuite(){
        System.out.println("在类运行前运行的用例");
    }
    @AfterSuite
    public void AfterSuite(){
        System.out.println("在类运行后运行的用例");
    }

}