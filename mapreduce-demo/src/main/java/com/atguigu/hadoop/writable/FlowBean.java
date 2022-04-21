package com.atguigu.hadoop.writable;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author clh
 * @create 2021-04-17-10:34
 */
public class FlowBean implements Writable {
    private Integer upFlow;
    private Integer downFlow;
    private Integer sumFlow;

    public FlowBean() {
    }

    public Integer getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Integer upFlow) {
        this.upFlow = upFlow;
    }

    public Integer getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Integer downFlow) {
        this.downFlow = downFlow;
    }

    public Integer getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Integer sumFlow) {
        this.sumFlow = sumFlow;
    }
    public void setSumFlow() {
        this.sumFlow =this.upFlow+this.downFlow;
    }
    //序列化方法
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(upFlow);
        out.writeInt(downFlow);
        out.writeInt(sumFlow);
    }
    //反序列化方法  反序列化的顺序和序列化的顺序需要一直
    @Override
    public void readFields(DataInput in) throws IOException {
       upFlow=in.readInt();
       downFlow=in.readInt();
       sumFlow=in.readInt();
    }

    @Override
    public String toString() {
        return upFlow+"\t"+downFlow+"\t"+sumFlow;
    }
}
