package com.awesome.okhttp.builder;

import java.util.Map;

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
public interface HasParamsable {
    OkHttpRequestBuilder params(Map<String, String> params);

    OkHttpRequestBuilder addParams(String key, String val);
}
