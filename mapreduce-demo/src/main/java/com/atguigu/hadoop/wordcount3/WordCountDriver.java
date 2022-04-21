package com.atguigu.hadoop.wordcount3;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 驱动类
 *       main方法所在类
 *       Job 是你整个mr的抽象对象   你可以使用这个job对你的当前的mr做任意操作
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1.创建配置对象
        Configuration conf = new Configuration();
        //设置HDFS NameNode的地址
        conf.set("fs.defaultFS", "hdfs://hadoop102:8020");
        //指定MapReduce运行在Yarn上
        conf.set("mapreduce.framework.name","yarn");
        //指定mapreduce可以在远程集群运行
        conf.set("mapreduce.app-submission.cross-platform","true");
        //指定Yarn resourcemanager的位置
        conf.set("yarn.resourcemanager.hostname","hadoop103");
        //2.获取job实例
        Job job = Job.getInstance(conf);
        //3.绑定当前jar或主程序所在类
        //job.setJarByClass(WordCountDriver.class);
        job.setJar("C:\\ideaProjects\\bigdata0225\\mapreduce-demo\\target\\mapreduce-demo-1.0-SNAPSHOT.jar");
        //4.绑定当前mr的mapper和reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);
        //5.指定当前mapper的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //6.指定当前最终的输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //7.指定输入和输出路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
        //8.提交当前job运行
        boolean b = job.waitForCompletion(true);
        System.out.println(job.getClass().getName());
        System.exit(b?0:1);
    }
}
