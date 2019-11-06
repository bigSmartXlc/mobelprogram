package com.hbtcsrzzx.utils.ConfigConsts;

import java.util.HashMap;
import java.util.Map;


public class Constants {
    //用户的常量字段
    //主要针对用户isonline字段
    public final static int ONLINE = 1;
    public final static int OUTLINE = 0;
    //主要针对管理员auth字段
    public final static int SUPER_ADMIN = 0; //超级管理员
    public final static int NORMAL_ADMIN = 1; //普通管理员
    //主要针对state字段
    public final static int DEL_STATE = -1; //删除
    public final static int NORMAL_STATE = 0;
    //主要针对新闻公告TYPE
    public final static int NORMAL_TYPE = 0;
    public final static int TOP_TYPE = 1;//置顶
    public final static int STRONG_TYPE = 2; //加粗置顶
    //主要针对type
    public final static int ADMIN = 0;
    public final static int EXPERT = 1;
    public final static int TEACHER = 2;
    public final static int USER = 3;

    //主要返回样式
    public final static int QUERY_OK = 200;
    public final static int QUERY_FAIL = -1;


    //后台用户信息:用户角色
    public static final String ROLE_NAME_INSTITUTION = "INSTITUTION";
    public static final String ROLE_NAME_SALESMAN = "SALESMAN";

    //考生信息:支付结果返回字段
    public final static int PAY_SUCCESS = 1;
    public final static int PAY_FAIL = 0;


    /**
     * 考生信息:缴费字段
     */
    public final static int NOT_PAY_COST = 0;
    public final static int PAY_COST = 1;


    /**
     * 用户信息:推荐人级别
     */
    public final static String RECOMMENDATION_LEVEL_1 = "1";
    public final static String RECOMMENDATION_LEVEL_2 = "2";


    /**
     * 毫秒时间
     */
    public final static Long MONTH = 30 * 24 * 60 * 60 * 1000L;


    /**
     * 分销比例:分销等级
     */
    public final static String DISTRIBUTION_LEVEL_1 = "1";
    public final static String DISTRIBUTION_LEVEL_2 = "2";
    public final static String DISTRIBUTION_LEVEL_DEFAULT = "3";
    public static Map map = new HashMap<String, Object>();


    /**
     * 支付日志信息:交易状态
     */
    public final static int TRADE_STATE_NO = 0;
    public final static int TRADE_STATE_YES = 1;
    public final static int TRADE_STATE_TIMEOUT = 2;

    /**
     * 支付日志信息:支付类型
     */
    public final static int PAY_TYPE_WX = 0;
    public final static int PAY_TYPE_ZFB = 1;


    /**
     * 第三方登录信息:应用名称
     */
    public final static String WeiBo = "微博";
    public final static String WeiXin = "微信";
    public final static String QQ = "QQ";
}
