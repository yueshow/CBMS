package com.awesome.consumer.cbms.beans;

import java.io.Serializable;

/**
 * Create: 05/03/18 , 下午3:37
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 05/03/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class City implements Serializable {

    public int id, parentId;
    public String zh, tw, en, kh;
    public City(int id, int parentId, String zh, String tw, String en, String kh){
        this.id = id;
        this.parentId = parentId;
        this.zh = zh;
        this.tw = tw;
        this.en = en;
        this.kh = kh;
    }
}
