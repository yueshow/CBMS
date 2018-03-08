package com.awesome.consumer.cbms.beans;

import android.util.Log;

import com.awesome.consumer.cbms.config.Config;
import com.awesome.consumer.cbms.okhttputil.callback.IGenericsSerializator;
import com.google.gson.Gson;

/**
 * Create: 18/01/18 , 下午9:47
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 18/01/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class JsonGenericsSerializator implements IGenericsSerializator {
    Gson mGson = new Gson();
    @Override
    public <T> T transform(String response, Class<T> classOfT) {
        return mGson.fromJson(response, classOfT);
    }
}
