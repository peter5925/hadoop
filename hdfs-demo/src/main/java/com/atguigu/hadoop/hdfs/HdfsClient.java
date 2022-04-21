package com.atguigu.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @author clh
 * @create 2021-04-14-10:15
 */
public class HdfsClient {

    private FileSystem fs;
    private String user;
    private Configuration conf;
    private URI uri;
    @Before //todo 在test之前执行一次
    public void init() throws IOException, InterruptedException {
        //1.namenode地址
        URI uri = URI.create("hdfs://hadoop102:8020");
        //2.配置对象
        conf = new Configuration();
        conf.set("dfs.replication","2");
        //3.指定用户
        user = "atguigu";
        fs = FileSystem.get(uri, conf, user);
    }
    @After //todo 在test之后执行一次
    public void close() throws IOException {
        fs.close();
    }
    @Test
    public void mkdir() throws IOException {
        fs.mkdirs(new Path("/idea/java1"));
    }
    //上传方法
    @Test
    public void put() throws IOException {
        /**
         * 参数解读
         * 1.boolean delSrc 是否删除源文件 是否删除windows文件
         * 2.boolean overwrite 是否覆盖目标文件 是否覆盖hdfs文件
         * 3.Path src 源文件路径  windows路径
         * 4.Path dst 目标路径  hdfs路径
         */
        fs.copyFromLocalFile(false,true,
                new Path("D:\\input\\haha.txt"),new Path("/input"));
        //参数的优先级问题 先从default里面去获取参数值<xxxx.site.xml<代码里直接设置属性值
    }
    //下载方法
    @Test
    public void get() throws IOException {
        /**
         * 参数解读
         * 1.boolean delSrc 是否删除源文件hdfs 路径
         * 2. Path src 目标路径 hdfs 路径
         * 3. Path dst 下载到哪
         * 4.boolean useRawLocalFileSystem
         *   校验有两个人校验 开了本地校验  hdfs不校验 没有crc
         *                    没开本地校验  hdfs校验   有crc
         */
        fs.copyToLocalFile(false,new Path("/a.txt"),new Path("D:\\input"),false);
    }
    //删除方法
    @Test
    public void rm() throws IOException {
        fs.delete(new Path("/idea"),true);
    }
    //文件的更名和移动
    @Test
    public void mv() throws IOException {
        //修改文件名
        //fs.rename(new Path("/input/a.txt"),new Path("/input/b.txt"));
        //移动文件
        //fs.rename(new Path("/input/b.txt"),new Path("/"));
        //移动文件并改名
        //fs.rename(new Path("/b.txt"),new Path("/input/a.txt"));
        //修改目录名
        //fs.rename(new Path("/java"),new Path("/idea"));
        //移动目录
        //fs.rename(new Path("/idea"),new Path("/input"));
        //移动目录并改名
        fs.rename(new Path("/input"),new Path("/Java/linux"));
    }
    //获取文件的详情信息
    @Test
    public void ls() throws IOException {
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        while (listFiles.hasNext()) {
            LocatedFileStatus fileStatus = listFiles.next();
            System.out.println("========"+fileStatus.getPath()+"========");
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getOwner());
            System.out.println(fileStatus.getGroup());
            System.out.println(fileStatus.getLen());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println(simpleDateFormat.format(fileStatus.getModificationTime()));
            System.out.println(fileStatus.getReplication());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPath().getName());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            System.out.println(Arrays.toString( blockLocations));
        }
    }
    @Test
    public void fileOrDir() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            boolean isFile = fileStatus.isFile();
            if (isFile){
                System.out.println("文件:"+fileStatus.getPath());
            }else {
                System.out.println("目录:"+fileStatus.getPath());
            }
        }
    }
    public void fileOrDir(Path path) throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(path);
        for (FileStatus fileStatus : fileStatuses) {
            boolean isFile = fileStatus.isFile();
            if (isFile){
                System.out.println("文件:"+fileStatus.getPath());
            }else {
                System.out.println("目录:"+fileStatus.getPath());
                fileOrDir(fileStatus.getPath());
            }
        }
    }
    @Test
    public void testFileOrDir() throws IOException {
        fileOrDir(new Path("/"));
    }

    //手写io上传
    @Test
    public void putByIO() throws IOException {
        //1.创建本地文件输入流
        FileInputStream fis = new FileInputStream("D:\\input\\test.txt");
        //2.创建hdfs文件输出流
        FSDataOutputStream fdos = fs.create(new Path("/Java/test1.txt"));
        //3.流的对拷
        IOUtils.copyBytes(fis,fdos,conf);
        //4.流的关闭
        //IOUtils.closeStream(fdos);
        //IOUtils.closeStream(fis);
        IOUtils.closeStreams(fdos,fis);
    }
    //手写io下载
    @Test
    public void getByIO() throws IOException {
        //1.创建hdfs文件输入流
        FSDataInputStream fdis = fs.open(new Path("/hadoop-3.1.3.tar.gz"));
        //2.创建本地文件输出流
        FileOutputStream fos = new FileOutputStream("D:\\input\\hadoop-3.1.3.tar.gz");
        //3.流的对拷
        IOUtils.copyBytes(fdis,fos,conf);
        //4.流的关闭
        IOUtils.closeStreams(fos,fdis);
    }
}
