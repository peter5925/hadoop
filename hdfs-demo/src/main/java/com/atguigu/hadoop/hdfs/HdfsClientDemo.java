package com.atguigu.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * 所有的客户端操作代码都一样
 * 都是三步
 * 1.创建客户端对象
 * 2.使用对象进行一些操作
 * 3.关闭客户端对象
 * jdbc   hadoop  zookeeper  kafka
 */
public class HdfsClientDemo {
    @Test
    public void initClient() throws URISyntaxException, IOException, InterruptedException {
        /**
         * 参数解读
         * 1.uri       namenode的地址 hdfs://hadoop102:8020
         * 2.配置对象  new Configuration
         * 3.用户      atguigu
         */
        //URI uri = new URI("hdfs://hadoop102:8020");
        //1.namenode地址
        URI uri = URI.create("hdfs://hadoop102:8020");
        //2.配置对象
        Configuration conf = new Configuration();
        //3.指定用户
        String user="atguigu";
        FileSystem fs = FileSystem.get(uri,conf,user);
        fs.mkdirs(new Path("/java"));
        fs.close();
    }

}
