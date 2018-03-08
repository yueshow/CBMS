package com.awesome.consumer.cbms.okhttputil.builder;

import com.awesome.consumer.cbms.okhttputil.request.OtherRequest;
import com.awesome.consumer.cbms.okhttputil.request.RequestCall;
import com.awesome.consumer.cbms.okhttputil.OkHttpUtils;

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
public class HeadBuilder extends GetBuilder {
    @Override
    public RequestCall build() {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers, id).build();
    }
}
