package com.lx.backstagemanagement.constant;

public class SysKeyWord {
    private static String userName = "USER_NAME";
    private static String IMAGE_ADDRESS = "http://193.112.113.194:9999";

    public static String getImageAddress() {
        return IMAGE_ADDRESS;
    }

    public static void setImageAddress(String imageAddress) {
        IMAGE_ADDRESS = imageAddress;
    }

    private static Integer pageSize = 12;

    public static String getUserName() {
        return userName;
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