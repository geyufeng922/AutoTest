package com.course.testng;

import org.testng.annotations.Test;

public class IgnorTest {

    @Test(enabled=true)
    public void Ignor1(){
        System.out.println("执行1");
    }
    @Test(enabled=false)
    public void Ignor2(){
        System.out.println("执行2");
    }
}
