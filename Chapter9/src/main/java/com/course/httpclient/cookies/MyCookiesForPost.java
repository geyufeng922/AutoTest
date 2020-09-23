package com.course.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore store;

    @BeforeTest
    public void beforeTest(){
        bundle=ResourceBundle.getBundle("application", Locale.CANADA);
        url=bundle.getString("test.url");
    }
    @Test
    public void testGetCookies()throws IOException {
        String result;
        //重配置文件中拼接url
        String uri=bundle.getString("getCookies.uri");
        String testUrl=this.url+uri;
        //测试逻辑代码书写
        HttpGet get = new HttpGet(testUrl);
        DefaultHttpClient client =new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        result= EntityUtils.toString(response.getEntity(),"uft-8");
        System.out.println(result);
        //获取cookies信息
        this.store=client.getCookieStore();
        List<Cookie> cookielist=store.getCookies();
        for(Cookie cookie:cookielist){
            String name=cookie.getName();
            String value=cookie.getValue();
            System.out.println("cookie name="+name+"; cookie value="+value);
        }

    }
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {
        //拼接URL
        String uri = bundle.getString("test.get.with.cookies");
        String testUrl = this.url + uri;
        //声明一个client对象；
        DefaultHttpClient client = new DefaultHttpClient();
        //声明一个post方法；
        HttpPost post = new HttpPost(testUrl);
        //添加参数；
        JSONObject param = new JSONObject();
        param.put("name", "huhansan");
        param.put("age", "18");
        //设置请求头信息；设置header信息
        post.setHeader("content-type", "application/json");
        //将参数信息添加到方法中
        StringEntity entity = new StringEntity(param.toString(), "utf-8");
        post.setEntity(entity);
        //声明一个对象来进行相应结果得存储；
        String result;
        //设置cookies信息
        client.setCookieStore(this.store);
        //执行post方法
        HttpResponse reponse = client.execute(post);
        //获取相应结果
        result = EntityUtils.toString(reponse.getEntity(), "utf-8");
        System.out.println(result);
        //处理结果，就是判断返回结果是否符合预期
        //将返回的响应结果字符转化成json对象；
        JSONObject resultJson = new JSONObject(result);
        //获取结果值；
        String success = (String) resultJson.get("huhansan");
        String status = (String) resultJson.get("status");
        //具体判断返回结果的值；
        Assert.assertEquals("success", success);
        Assert.assertEquals("1", status);



    }
}
