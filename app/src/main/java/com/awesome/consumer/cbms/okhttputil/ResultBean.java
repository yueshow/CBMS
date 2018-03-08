package com.awesome.consumer.cbms.okhttputil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Create: 20/01/18 , 下午3:29
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 20/01/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class ResultBean {
    public Integer code;
    public String msg;
    public Object data;

    public static ResultBean getResultBean(String responseString){
        ResultBean bean = null;
        try {
            bean = new ResultBean();
            JSONObject jsonObject = new JSONObject(responseString);
            bean.code = jsonObject.getInt("code");
            bean.msg = jsonObject.getString("msg");
            bean.data = jsonObject.getString("data");
            return bean;
        } catch (JSONException e) {
            return null;
        }
    }
}
