package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {

    @Test(timeOut = 3000)
    public void timeTest() throws InterruptedException{
        Thread.sleep(2000);
        System.out.println("success");
    }
    @Test(timeOut = 1000)
    public void testSuccess() throws InterruptedException{
        Thread.sleep(3000);
        System.out.println("fail");
    }


}



