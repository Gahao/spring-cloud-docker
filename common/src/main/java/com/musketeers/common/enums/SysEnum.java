package com.musketeers.common.enums;

/**
 * 系统枚举值
 */
public class SysEnum {

    /**
     * 媒体报道状态：0未发布，1已发布
     */
    public static String MEDIA_STATUS_UNPUBLISH = "0";
    public static String MEDIA_STATUS_PUBLISH = "1";

    /**
     * 新闻报道状态：0未发布，1已发布
     */
    public static String NEWS_STATUS_UNPUBLISH = "0";
    public static String NEWS_STATUS_PUBLISH = "1";

    /**
     * 订单，交易状态：0未付款，1已付款
     */
    public static String ORDER_STATUS_UNPAY = "0";
    public static String ORDER_STATUS_AREADYPAY = "1";

    /**
     * 课程状态：0未开放，1开放
     */
    public static String CLASS_STATUS_UNOPEN = "0";
    public static String CLASS_STATUS_OPEN = "1";

    /**
     * 用户状态：0禁用，1正常
     */
    public static String USER_STATUS_DISENABLED = "0";
    public static String USER_STATUS_ENABLED = "1";

    /**
     * 用户存在session的名字
     */
    public static String USER_SESSION = "userInfo";
}
