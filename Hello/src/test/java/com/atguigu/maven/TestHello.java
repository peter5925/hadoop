package com.atguigu.maven;


import org.junit.Test;
import static junit.framework.Assert.*;
/**
 * @author clh
 * @create 2021-04-02-10:53
 */
public class TestHello {
    @Test
    public void testHello(){
        Hello hello = new Hello();
        String result = hello.sayHello("atguigu");
        //断言  给一个期望值 如果你的结果集合期望值相同 则测试成功  如果不同则失败
        assertEquals("hello atguigu!",result);
    }
}
