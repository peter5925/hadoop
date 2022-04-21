package com.atguigu.hadoop.writablecomparable;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @author clh
 * @create 2021-04-19-11:12
 */
public class FlowMapper extends Mapper<LongWritable, Text,FlowBean,Text> {
    private Text outV=new Text();
    private FlowBean outK=new FlowBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //转换字符串
        String phone = value.toString();
        //切分
        String[] phones = phone.split("\t");
        outK.setUpFlow(Integer.parseInt(phones[1]));
        outK.setDownFlow(Integer.parseInt(phones[2]));
        outK.setSumFlow();
        outV.set(phones[0]);
        //写出
        context.write(outK,outV);
    }
}
