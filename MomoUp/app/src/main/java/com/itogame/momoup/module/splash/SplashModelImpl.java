package com.itogame.momoup.module.splash;

import com.itogame.momoup.http.HttpHelper;
import com.itogame.momoup.http.interfaces.SuccessCallBack;

/**
 * function
 *
 * @author loengcibo
 * @time 2017/12/1.
 */

public class SplashModelImpl implements Splashmodel {
    @Override
    public void getAdInfo(SuccessCallBack callBack) {
        HttpHelper.getSplanishAd(callBack);
    }

    @Override
    public void getTimeStamp() {

    }

    @Override
    public void getAdInfoFromCache() {

    }
}
