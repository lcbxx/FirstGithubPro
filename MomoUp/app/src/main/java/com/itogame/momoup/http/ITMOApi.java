package com.itogame.momoup.http;

/**
 *  请求链接
 * @author leongcibo
 * @time 2017/11/29
 */

public class ITMOApi {

    public static final String URL_BASE = "https://mobile.itmo.com/";

    /**
     * 欢迎界面 广告
     */
    public static final String URL_GET_WELCOME_AD = URL_BASE + "strategy/getAd?position=12&device=3";

    /**
     * 获取服务器时间戳
     */
    public static final String URL_GET_TIME = URL_BASE + "client/timestamp";

    /**
     * 欢迎界面广告点击
     */
    public static final String URL_WELCOME_AD_CLICK = URL_BASE + "strategy/adStatistics?adId=%s";
}
