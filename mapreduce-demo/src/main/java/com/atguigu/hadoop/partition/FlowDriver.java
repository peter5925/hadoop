package com.atguigu.hadoop.partition;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * main 方法所在类
 */
public class FlowDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1.创建配置对象
        Configuration conf = new Configuration();
        //2.通过conf 获取实例
        Job job = Job.getInstance(conf);
        //3.绑定当前jar或者class
        job.setJarByClass(FlowDriver.class);
        //4.绑定当前的mapper和reudcer
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);
        //5.指定mapper的输出
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);
        //6.指定最终输出
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        //设置reducertask的数量
        job.setNumReduceTasks(5);
        //设置使用分区器
        job.setPartitionerClass(ProvincePartitioner.class);
        //7.指定输入和输出
        FileInputFormat.setInputPaths(job,new Path("D:\\input\\inputflow"));
        FileOutputFormat.setOutputPath(job,new Path("D:\\hadoop\\fb3"));
        //8.提交运行
        job.waitForCompletion(true);
    }
}
