package com.atguigu.hadoop.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 继承reducer
 */
public class FlowReducer  extends Reducer<Text, FlowBean,Text, FlowBean> {
    private FlowBean outV=new FlowBean();
    //13568436656 [flowBean,flowBean]
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        //定义一个上行和下行总和
        int sumUpFlow=0;
        int sumDownFlow=0;
        //遍历集合
        for (FlowBean value : values) {
            sumUpFlow+=value.getUpFlow();
            sumDownFlow+=value.getDownFlow();
        }
        outV.setUpFlow(sumUpFlow);
        outV.setDownFlow(sumDownFlow);
        outV.setSumFlow();
        //结果写出
        context.write(key,outV);
    }
}
