package com.awesome.consumer.cbms.beans.test;

import com.awesome.consumer.cbms.okhttputil.ResultBean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Create: 22/01/18 , 下午3:18
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 22/01/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class MemberJson extends ResultBean{
    @SerializedName("data")
    public Member data;

    public static class Member{

    }
}
