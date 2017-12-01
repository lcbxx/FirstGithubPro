package com.itogame.momoup.uitils;


import android.content.SharedPreferences;

import com.itogame.momoup.ITMOApplication;
import com.itogame.momoup.bean.AdInfo;

/**
 *  数据存储工具类
 * @author loengcibo
 * @time 2017/11/19
 */

public class PreferencesUtil {

    private static final String PREFERENCES_NAME = "itmo";

    private static SharedPreferences getDefaultSharedPreferences() {
        ITMOApplication itmoApp = ITMOApplication.getInstance();
        SharedPreferences sp = itmoApp.getSharedPreferences(PREFERENCES_NAME, 0);
        return sp;
    }

    /**
     * 获取缓存的广告信息
     */
    public static AdInfo getADInfo() {
        SharedPreferences sp = getDefaultSharedPreferences();
        if (sp.getBoolean("hasAd", false)) {
            AdInfo adInfo = new AdInfo();
            adInfo.setId(sp.getString("ad_id", ""));
            adInfo.setTitle﻿﻿﻿(sp.getString("ad_title", ""));
            adInfo.setSubtitle(sp.getString("ad_subtitle", ""));
            adInfo.setUrl(sp.getString("ad_url", ""));
            adInfo.setIcon﻿﻿(sp.getString("ad_icon﻿", ""));
            adInfo.setRemaintTime(sp.getString("ad_remaintTime", ""));
            adInfo.setGame_id(sp.getString("ad_game_id", ""));
            return adInfo;
        } else {
            return null;
        }
    }

    /**
     * 保存广告信息
     *
     * @param advert
     */
    public static void setADInfo(AdInfo advert) {
        SharedPreferences sp = getDefaultSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ad_id", advert.getId());
        editor.putString("ad_title", advert.getTitle﻿﻿﻿());
        editor.putString("ad_subtitle", advert.getSubtitle());
        editor.putString("ad_url", advert.getUrl());
        editor.putString("ad_icon﻿", advert.getIcon﻿﻿());
        editor.putString("ad_remaintTime", advert.getRemaintTime());
        editor.putString("ad_game_id", advert.getGame_id());
        editor.putBoolean("hasAd", true);
        editor.commit();
    }

    /**
     * 清除广告信息
     */
    public static void clearADInfo() {
        SharedPreferences sp = getDefaultSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("ad_id", null);
        editor.putString("ad_title", null);
        editor.putString("ad_subtitle", null);
        editor.putString("ad_url", null);
        editor.putString("ad_icon﻿", null);
        editor.putString("ad_remaintTime", null);
        editor.putString("ad_game_id", null);
        editor.putBoolean("hasAd", false);
        editor.commit();
    }


}
