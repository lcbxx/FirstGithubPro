package com.itogame.momoup.module.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itogame.momoup.BaseActivity;
import com.itogame.momoup.R;
import com.itogame.momoup.bean.AdInfo;
import com.itogame.momoup.http.HttpHelper;
import com.itogame.momoup.http.interfaces.SuccessCallBack;
import com.itogame.momoup.module.main.MainActivity;
import com.itogame.momoup.uitils.ImageLoader;
import com.itogame.momoup.uitils.PreferencesUtil;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Administrator on 2017/11/29.
 */

public class SpanishActivity extends BaseActivity {

    private ImageView imgAdver;
    private TextView tvSkip;
    private ImageView imgLogo;
    private AdInfo adInfo_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        initView();
        showAd();
        getAdInfo();
    }

    private void initView() {
        imgAdver = findViewById(R.id.img_ad);
        imgLogo = findViewById(R.id.img_default_icon);
        tvSkip = findViewById(R.id.tv_welcome_ad_skip);
    }

    private void showAd() {
        Observable.timer(5, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startActivity(new Intent(SpanishActivity.this, MainActivity.class));
                    }
                });
    }

    private void getAdInfo() {
        HttpHelper.getSplanishAd(new SuccessCallBack() {
            @SuppressLint("ByteOrderMark")
            @Override
            public void onSucceed(String result) {
                Toast.makeText(SpanishActivity.this, "111", Toast.LENGTH_LONG).show();
                if (result.toString() != null && !result.toString().equals("")) {
                    try {
                        JSONObject obj = new JSONObject(result);
                        adInfo_get = new AdInfo();
                        adInfo_get.setIcon﻿﻿(obj.getString("icon"));
                        adInfo_get.setId(obj.getString("id"));
                        adInfo_get.setGame_id(obj.getString("game_id"));
                        PreferencesUtil.setADInfo(adInfo_get);
                        if (imgAdver != null && adInfo_get.getId() != null && !adInfo_get.getId().equals("")) {
                            ImageLoader.loadCenterCrop(SpanishActivity.this, adInfo_get.getIcon﻿﻿(), imgAdver, getResources().getColor(R.color.bgSpanish));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    PreferencesUtil.clearADInfo(); // 获取广告为空，则设置数据为空
                }
            }
        });
    }
}
