package com.atguigu.hadoop.writablecomparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 136  一个文件
 * 137 一个文件
 * 138  一个文件
 * 139  一个文件
 * 剩下所有在一起
 */
public class ProvincePartitoner2 extends Partitioner<FlowBean, Text> {
    @Override
    public int getPartition(FlowBean flowBean, Text text, int numPartitions) {
        //转化手机号
        String phone = text.toString();
        //截取前三位
        String prePhone = phone.substring(0, 3);
        int partition=0;
        switch (prePhone){
            case "136":
                partition=0;
                break;
            case "137":
                partition=1;
                break;
            case "138":
                partition=2;
                break;
            case "139":
                partition=3;
                break;
            default:
                partition=4;
        }
        return partition;
    }
}
