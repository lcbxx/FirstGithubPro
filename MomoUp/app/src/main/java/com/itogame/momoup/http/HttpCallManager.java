package com.itogame.momoup.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 *  请求管理
 */

public class HttpCallManager {
    static Map<String, Call> CALL_MAP = new HashMap<>();

    /*
    *添加某个请求
    *@author Administrator
    *@date 2016/10/12 11:00
    */
    public static synchronized void putCall(Object tag, String url, Call call) {
        if (tag == null)
            return;
        synchronized (CALL_MAP) {
            CALL_MAP.put(tag.toString() + url, call);
        }
    }

    /*
    *取消某个界面都所有请求，或者是取消某个tag的所有请求
    * 如果要取消某个tag单独请求，tag需要转入tag+url
    *@author Administrator
    *@date 2016/10/12 10:57
    */
    public static synchronized void cancel(Object tag) {
        if (tag == null)
            return;
        List<String> list = new ArrayList<>();
        synchronized (CALL_MAP) {
            for (String key : CALL_MAP.keySet()) {
                if (key.startsWith(tag.toString())) {
                    CALL_MAP.get(key).cancel();
                    list.add(key);
                }
            }
        }
        for (String s : list) {
            removeCall(s);
        }

    }

    /*
    *移除某个请求
    *@author Administrator
    *@date 2016/10/12 10:58
    */
    public static synchronized void removeCall(String url) {
        synchronized (CALL_MAP) {
            for (String key : CALL_MAP.keySet()) {
                if (key.contains(url)) {
                    url = key;
                    break;
                }
            }
            CALL_MAP.remove(url);
        }
    }
}
