package com.awesome.consumer.cbms.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.config.Config;

/**
 * Create: 23/01/18 , 下午5:07
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 23/01/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：检查字符串合法性工具类
 * -----------------------------------------------------------------
 */
public class StringUtils {
    public static final int LENGTH_60 = 60, LENGTH_30 = 30;
    /**
     * 检查传入的字符串是否合法
     * @param param 检查的字符串
     * @return      不为空，截去头尾空串外长度大于0，为合法，返回 true
     */
    public static boolean isSafeString(String param){
        if (null != param && 0 < param.trim().length()){
            return true;
        }
        return false;
    }

    /**
     *  检查传入的字符串是否合法，length如传入0或负数，会造成一切返回均为 false
     * @param param     检查的字符串
     * @param length    字符串是否在该长度以内
     * @return          不为空，截去头尾空串外长度 <= length，为合法，返回 true
     */
    public static boolean checkLengthSafe(String param, int length){
        if (isSafeString(param) && 0 < length && param.trim().length() <= length){
            return true;
        }
        return false;
    }

    public static boolean checkAndShowToast(String param, Context context, int toastResourceId) {
        if (isSafeString(param)) {
            return true;
        }
        Toast.makeText(context, toastResourceId, Toast.LENGTH_LONG).show();
        return false;
    }
}
