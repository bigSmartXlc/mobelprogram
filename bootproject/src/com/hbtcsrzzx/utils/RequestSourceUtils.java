package com.hbtcsrzzx.utils;

public class RequestSourceUtils {

    private final static String[] agent = { "Android", "iPhone", "iPod","iPad", "Windows Phone", "MQQBrowser" }; //定义移动端请求的所有可能类型
    /**
     * 获取请求来自移动端还是pc端 移动端返回true
     * @return
     */
    public static boolean getRequestSource(String ua){
        boolean flag = false;
        if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {
            // 排除 苹果桌面系统
            if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {
                for (String item : agent) {
                    if (ua.contains(item)) {
                        flag = true;
                        break;
                    }
                }
            }
        }
        return flag;

    }
}
