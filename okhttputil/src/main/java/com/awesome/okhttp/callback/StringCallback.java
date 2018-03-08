package com.awesome.okhttp.callback;

import java.io.IOException;

import okhttp3.Response;

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
public abstract class StringCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException {
        return response.body().string();
    }
}
