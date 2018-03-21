package com.core.constant;

import java.util.HashMap;
import java.util.Map;

public class SysKeyWord {
    private static String userName = "USER_NAME";
    private static String IMAGE_ADDRESS = "http://193.112.113.194:9999";
    private static Integer pageSize = 12;
    private static String IMAGE_DFS = "http://193.112.113.194:8000";

    public static String getUserName() {
        return userName;
    }

    public static String getImageAddress() {
        return IMAGE_ADDRESS;
    }

    public static String getImageDfs() {
        return IMAGE_DFS;
    }

    public static void setImageDfs(String imageDfs) {
        IMAGE_DFS = imageDfs;
    }

    public static void setImageAddress(String imageAddress) {
        IMAGE_ADDRESS = imageAddress;
    }

    public static void setUserName(String userName) {
        SysKeyWord.userName = userName;
    }

    public static Integer getPageSize() {
        return pageSize;
    }

    public static void setPageSize(Integer pageSize) {
        SysKeyWord.pageSize = pageSize;
    }
}
