package com.itogame.momoup.http;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.itogame.momoup.http.interfaces.FailedCallBack;
import com.itogame.momoup.http.interfaces.SuccessCallBack;
import com.itogame.momoup.uitils.CommonUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 网络请求
 */

public class HttpRequestUtils {

    private Object tag;
    private String url;
    private SuccessCallBack successCallBack;
    private FailedCallBack failedCallBack;
    private Map<String, Object> params = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();

    public HttpRequestUtils(String url) {
        if (TextUtils.isEmpty(url)) {
            throw new NullPointerException("absolute url can not be empty");
        }
        this.url = url;
        this.url = CommonUtil.enCodeUrl(url);
        setHeader("sign", CommonUtil.getEnCodeHeader(this.url));
        this.successCallBack = new SuccessCallBack() {
            @Override
            public void onSucceed(String result) {

            }
        };
        this.failedCallBack = new FailedCallBack() {
            @Override
            public void onFailed(Object... values) {

            }
        };
    }

    /**
     * 是否允许缓存，传入时间如：1*3600 代表一小时缓存时效
     *
     * @param time 缓存时间 单位：秒
     */
    public HttpRequestUtils cacheTime(int time) {
        setHeader("Cache-Time", time + "");
        return this;
    }

    public HttpRequestUtils tag(@NonNull Object tag) {
        this.tag = tag;
        return this;
    }

    public HttpRequestUtils setParams(@NonNull Map<String, String> params) {
        this.params.putAll(params);
        return this;
    }

    public HttpRequestUtils setParams(@NonNull String key, String value) {
        this.params.put(key, value);
        return this;
    }

    public HttpRequestUtils setHeaders(@NonNull Map<String, String> headers) {
        this.headers.putAll(headers);
        return this;
    }

    public HttpRequestUtils setHeader(@NonNull String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    public HttpRequestUtils setSuccessCallBack(SuccessCallBack successCallBack) {
        this.successCallBack = successCallBack;
        return this;
    }

    public HttpRequestUtils setFailCallBack(FailedCallBack failedCallBack) {
        this.failedCallBack = failedCallBack;
        return this;
    }

    public Map getParams() {
        //retrofit的params的值不能为null，此处做下校验，  防止出错
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() == null) {
                params.put(entry.getKey(), "");
            }
        }
        return params;
    }

    public Map getHeaders() {
        //retrofit的params的值不能为null，此处做下校验，  防止出错
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            if (entry.getValue() == null) {
                headers.put(entry.getKey(), "");
            }
        }
        return headers;
    }

    /**
     * GET请求
     */
    public void get() {

        Call call = RetrofitFactory.getRetrofit().create(RetrofitHttpService.class).get(this.url, getHeaders());
        HttpCallManager.putCall(tag, url, call);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() == 200) {
                    successCallBack.onSucceed(response.body().toString());
                } else {
                    failedCallBack.onFailed(messageFilter(response.message()));
                }
                if (tag != null)
                    HttpCallManager.removeCall(url);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                t.printStackTrace();
                failedCallBack.onFailed(messageFilter(t.getMessage()));
                if (tag != null)
                    HttpCallManager.removeCall(url);
            }
        });
    }

    /**
     * POST请求
     */
    public void post() {
        Call call = RetrofitFactory.getRetrofit().create(RetrofitHttpService.class).post(this.url, getParams(), getHeaders());
        HttpCallManager.putCall(tag, url, call);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    successCallBack.onSucceed(response.body());
                } else {
                    failedCallBack.onFailed(messageFilter(response.message()));
                }
                if (tag != null)
                    HttpCallManager.removeCall(url);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                failedCallBack.onFailed(messageFilter(t.getMessage()));
                if (tag != null)
                    HttpCallManager.removeCall(url);
            }
        });
    }

    private String messageFilter(String msg) {
        {
            if (TextUtils.isEmpty(msg)) {
                msg = "服务器异常，请稍后再试";
            }

            if (msg.equals("timeout") || msg.equals("SSL handshake timed out")) {
                return "网络请求超时";
            }
            return msg;
        }
    }

}
