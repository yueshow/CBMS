package com.awesome.consumer.cbms.okhttputil.util;

/**
 * Create: 05/01/18 , 上午11:28
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 05/01/18)
 * <p>
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class Exceptions {
    public static void illegalArgument(String msg, Object... params) {
        throw new IllegalArgumentException(String.format(msg, params));
    }
}
