package com.course.testng.groups;

import org.testng.annotations.Test;

@Test(groups="teacher")
public class GroupsOnClass3 {
    public void Teacher1(){
        System.out.println("GroupsOnClass3中的teacher11111运行");
    }
    public void Teacher2(){
        System.out.println("GroupsOnClass3中的teacher22222运行");
    }
}
