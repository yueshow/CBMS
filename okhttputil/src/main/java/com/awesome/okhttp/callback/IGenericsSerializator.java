package com.awesome.okhttp.callback;

/**
 * Create: 05/01/18 , 上午11:28
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 05/01/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public interface IGenericsSerializator {
    <T> T transform(String response, Class<T> classOfT);
}
