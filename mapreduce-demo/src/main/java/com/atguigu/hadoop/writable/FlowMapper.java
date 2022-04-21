package com.atguigu.hadoop.writable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 继承mapper类
 *
 */
public class FlowMapper extends Mapper<LongWritable, Text,Text,FlowBean> {
    private Text outK=new Text();
    private FlowBean outV=new FlowBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //1.转换字符串
        String line = value.toString();
        //2.切割字符串
        String[] phones = line.split("\t");
        //拿到手机号
        String phone = phones[1];
        //拿到上行
        String upFlow = phones[phones.length - 3];
        //拿到下行
        String downFlow = phones[phones.length - 2];
        //对key复制
        outK.set(phone);
        //对value复制
        outV.setUpFlow(Integer.parseInt(upFlow));
        outV.setDownFlow(Integer.parseInt(downFlow));
        outV.setSumFlow();
        //写出
        context.write(outK,outV);
    }
}
