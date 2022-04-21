package com.atguigu.hadoop.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 136  一个文件
 * 137 一个文件
 * 138  一个文件
 * 139  一个文件
 * 剩下所有在一起
 */
public class ProvincePartitioner extends Partitioner<Text,FlowBean> {
    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        //获取手机号
        String phone = text.toString();
        //获取前3位
        String prePhone = phone.substring(0, 3);
        if ("136".equals(prePhone)){
            return 0;
        }else if("137".equals(prePhone)){
            return 1;
        }else if("138".equals(prePhone)){
            return 2;
        }else if("139".equals(prePhone)){
            return 3;
        }else {
            return 4;
        }
    }
}
