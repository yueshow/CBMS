package com.awesome.consumer.cbms.utils;

import java.util.Random;

/**
 * Create: 27/02/18 , 上午11:13
 * Author: 越秀
 * Version: V100R001C01
 * Changes (from 27/02/18)
 * *
 * -----------------------------------------------------------------
 * 文件描述 ：
 * -----------------------------------------------------------------
 */
public class FileUtils {
    private static Random random = new Random();
    public static final int randomInt(){
        return Math.abs(random.nextInt());
    }
    public static final String randomFileName(String extensionName){
        return randomInt() + "." + extensionName;
    }
}
