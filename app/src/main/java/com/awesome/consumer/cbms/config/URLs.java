package com.awesome.consumer.cbms.config;

/**
 * Create: 23/01/18 , 下午4:53
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 23/01/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class URLs {
    private static final String mBaseUrl = "http://192.168.0.2/memberapi/";
    private static final String mLogin = "?ct=member&ac=login";
    private static final String mVerifyRegisterStep1 = "?ct=member&ac=verify_reg_step1";
    private static final String mVerifyRegisterStep2 = "?ct=member&ac=verify_reg_step2";
    private static final String mVerifyRegisterStep3 = "?ct=member&ac=verify_reg_step3";

    public static String getLoginUrl(){
        return mBaseUrl + mLogin;
    }
    public static String getVerifyRegisterStep1Url(){
        return mBaseUrl + mVerifyRegisterStep1;
    }

    public static String getVerifyRegisterStep2Url(){
        return mBaseUrl + mVerifyRegisterStep2;
    }

    public static String getVerifyRegisterStep3Url(){
        return mBaseUrl + mVerifyRegisterStep3;
    }

}
