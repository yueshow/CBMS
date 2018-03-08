package com.awesome.consumer.cbms.beans;

import java.util.Date;

/**
 * Create: 11/01/18 , 下午6:05
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 11/01/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class OrderBean {
    public boolean flag;
    public float money;
    public String explain1, explain2;
    public Date date;

    public String toString(){
        return "OrderBean : [" + money + ", " + explain1 + ", " + explain2 + ", " + date.toString() + "]";
    }
}
