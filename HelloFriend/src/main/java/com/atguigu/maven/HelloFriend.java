package com.atguigu.maven;

/**
 * @author clh
 * @create 2021-04-02-14:21
 */
public class HelloFriend {
    public String sayHelloToFriend(String name){
        Hello hello = new Hello();
        String str = hello.sayHello(name)+" I am "+this.getMyName();
        return str;
    }

    public String getMyName(){
        return "Idea";
    }
}
