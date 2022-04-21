package com.atguigu.hadoop.combine;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * combiner在Mapper之后 所以它的输入一定是mapper输出
 * combiner在reducer之后 所以它的输出一定是reducer输入
 * 又因mapper输出一定是reducer输入
 * 所以combiner里面两对泛型是一模一样的
 */
public class WordCountCombiner extends Reducer<Text, IntWritable,Text,IntWritable> {
    private IntWritable outV=new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        //定义求和变量
        int sum=0;
        //1.atguigu,[1,1]
        for (IntWritable value : values) {
            sum+=value.get();
        }
        //把当前sum做封装
        outV.set(sum);
        //把最终结果写出
        context.write(key,outV);
    }
}
