package com.itogame.momoup;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2017/11/29.
 */

public class ITMOApplication extends Application {

    public static Context AppContext;

    private static ITMOApplication itmoAppliaction;

    public static ITMOApplication getInstance() {
        return itmoAppliaction;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = getApplicationContext();
        itmoAppliaction = this;
    }

}
