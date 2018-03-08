package com.awesome.consumer.cbms.config;

import com.awesome.consumer.cbms.beans.User;

/**
 * Create: 27/12/17 , 下午3:00
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 27/12/17)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class Config {
    public static final String TAG = "Awesome_CBMS";

    public static final String CONSUMER_TYPE = "CONSUMER_TYPE";
    public static final String AUTO_LOGIN = "AUTO_LOGIN";
    public static final String PHOTO_INDEX = "PHOTO_INDEX";
    public static final String ADDRESS = "ADDRESS";
    public static final String AREA_TYPE = "AREA_TYPE";
    public static final String AREA = "AREA";
    public static final String AREA_PARENT = "AREA_PARENT";

    public static User globalUser = new User();
    public static final int RESULT_OK = 0x01;
    public static final int PROEINCE = 0x02;
    public static final int CITY = 0x03;

    public static final int REQUEST_CODE_COUNTRY = 0x01;
    public static final int REQUEST_CODE_AREA = 0x02;
    public static final int REQUEST_CODE_CITY = 0x03;

}
