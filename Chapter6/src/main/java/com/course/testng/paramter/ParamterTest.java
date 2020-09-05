package com.course.testng.paramter;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParamterTest {
    @Test
    @Parameters({"name","age"})
    public void paramterTest1(String name,int age){
        System.out.println("name="+name+";   age="+age);
    }

}

//paramter  参数
//parameter 规范、范围、决定性因素