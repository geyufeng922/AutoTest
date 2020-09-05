package com.course.testng;

import org.testng.annotations.Test;

public class ExpectedException {
     //@Test(expectedExceptions = RuntimeException.class)
      public void Failed(){
        System.out.println("这是一个运行失败得案例");
        //throw new RuntimeException();
     }
     @Test(expectedExceptions = RuntimeException.class)
     public void Success(){
         System.out.println("这是一个成功得异常测试案例");
          throw new RuntimeException();
     }
}


