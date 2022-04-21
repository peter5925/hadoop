package com.atguigu.maven;

import org.junit.Test;
import static junit.framework.Assert.*;
/**
 * @author clh
 * @create 2021-04-02-14:23
 */
public class TestHelloFriend {
    @Test
    public void testHelloFriend(){
        HelloFriend helloFriend = new HelloFriend();
        String results = helloFriend.sayHelloToFriend("Maven");
        assertEquals("hello Maven! I am Idea",results);
    }
}
