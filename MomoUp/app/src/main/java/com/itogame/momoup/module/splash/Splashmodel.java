package com.itogame.momoup.module.splash;

import com.itogame.momoup.http.interfaces.SuccessCallBack;

/**
 * 引导页接口
 *
 * @author loengcibo
 * @time 2017/12/1.
 */

public interface Splashmodel {

    void getAdInfo(SuccessCallBack callBack);

    void getTimeStamp();

    void getAdInfoFromCache();

}
