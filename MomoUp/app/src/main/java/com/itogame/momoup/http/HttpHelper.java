package com.itogame.momoup.http;


import com.itogame.momoup.http.interfaces.FailedCallBack;
import com.itogame.momoup.http.interfaces.ITMOApiCallback;
import com.itogame.momoup.http.interfaces.SuccessCallBack;

/**
 * 请求中间层
 */

public class HttpHelper {

    public static void test(final ITMOApiCallback apiCallback) {
        new HttpRequestUtils("https://api.github.com/users/basil2style").setSuccessCallBack(new SuccessCallBack() {
            @Override
            public void onSucceed(String result) {
                apiCallback.onSuccessed(result);
            }
        }).setFailCallBack(new FailedCallBack() {
            @Override
            public void onFailed(Object... values) {
                apiCallback.onFailed(values[0].toString());
            }
        }).get();
    }

    public static void getSplanishAd(final SuccessCallBack apiCallback) {
        new HttpRequestUtils(String.format(ITMOApi.URL_GET_WELCOME_AD,"10245")).setSuccessCallBack(apiCallback).get();
    }






}
