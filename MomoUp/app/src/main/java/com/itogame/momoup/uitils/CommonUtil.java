package com.itogame.momoup.uitils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.Toast;

import com.itogame.momoup.ITMOApplication;
import com.itogame.momoup.http.HttpRequestUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class CommonUtil {
    public static final String KEY = "XEV3_6(R2<o<krJj";

    private static Toast toast = null;
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,2-9]))\\d{8}$";

    /**
     * Convert Dp to Pixel 将dp转换为pixel
     */
    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * @param value
     * @return 将dip或者dp转为float
     */
    public static float dipOrDpToFloat(String value) {
        if (value.indexOf("dp") != -1) {
            value = value.replace("dp", "");
        } else {
            value = value.replace("dip", "");
        }
        return Float.parseFloat(value);
    }

    /**
     * 获取屏幕的 宽高
     *
     * @param context
     * @return
     */
    public static DisplayMetrics getPhoneDisplay(Context context) {
        try {
            DisplayMetrics display = new DisplayMetrics();
            WindowManager localWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

            localWindowManager.getDefaultDisplay().getMetrics(display);

            return display;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return null;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getWidth(Context context) {
        DisplayMetrics display = getPhoneDisplay(context);
        return display.widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getHeight(Context context) {
        DisplayMetrics display = getPhoneDisplay(context);
        return display.heightPixels;
    }


    /**
     * 获取设备IMEI号
     *
     * @return
     */
    public static String getIMEI() {
//		ITMOApplication context = ITMOApplication.getInstance();
//		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//		if (tm.getDeviceId() != null) {
//			return tm.getDeviceId();
//		} else {
//			return Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
//		}
        return "866338037764311";
    }

    /**
     * 获取程序的PackageInfo信息
     *
     * @param mContext
     * @return
     * @throws Exception
     */
    public static PackageInfo getPackageInfo(Context mContext) throws Exception {
        // 获取PackageManager的实例
        PackageManager packageManager = mContext.getPackageManager();
        // getPackageName()是你当前类的包名,0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
        return packInfo;
    }

    /**
     * 判断当前网络是否为WAP网络
     *
     * @return
     */
    public static boolean isWap() {
        String proxyHost = android.net.Proxy.getDefaultHost();
        if (proxyHost != null) {
            return true;
        } else {
            return false;
        }
    }


    // 时间截取例如：2014-05-06 变成 05-06
    public static String getSubString(String utime) {
        String str = "";
        if (!TextUtils.isEmpty(utime)) {
            int start = utime.indexOf("-") + 1;
            str = utime.substring(start, utime.length());
            return str;
        }
        return str;
    }


    /**
     * 半角转换为全角
     *
     * @param input
     * @return
     */
    public static String ToDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    /**
     * 去除特殊字符或将所有中文标号替换为英文标号
     *
     * @param str
     * @return
     */
    public static String stringFilter(String str) {
        str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!").replaceAll("：", ":");// 替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 半角转化为全角的方法
     *
     * @param input
     * @return
     */
    public String ToSBC(String input) {
        // 半角转全角：
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 32) {
                c[i] = (char) 12288;
                continue;
            }
            if (c[i] < 127 && c[i] > 32)
                c[i] = (char) (c[i] + 65248);
        }
        return new String(c);
    }

    /**
     * md5加密
     *
     * @param string
     * @return
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String channel = null;

    /**
     * 获取渠道号
     *
     * @param context
     * @return
     */
    public static String getChannel(Context context) {
        if (channel != null) {
            return channel;
        }

        final String start_flag = "META-INF/channel_";
        ApplicationInfo appinfo = context.getApplicationInfo();
        String sourceDir = appinfo.sourceDir;
        ZipFile zipfile = null;
        try {
            zipfile = new ZipFile(sourceDir);
            Enumeration<?> entries = zipfile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String entryName = entry.getName();
                if (entryName.contains(start_flag)) {
                    channel = entryName.replace(start_flag, "");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (zipfile != null) {
                try {
                    zipfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (channel == null || channel.length() <= 0) {
            channel = "guanwang";// 读不到渠道号就默认是官方渠道
        }
        return channel;
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 提示持续显示
     */
    public static void showToastShort(Context context, String hint) {
        if (toast == null) {
            toast = Toast.makeText(context, hint, Toast.LENGTH_SHORT);
        } else {
            toast.setText(hint);
            toast.setDuration(Toast.LENGTH_SHORT);
        }

        toast.show();
    }

    /**
     * 提示持续显示
     */
    public static void showToastLong(Context context, String hint) {
        if (toast == null) {
            toast = Toast.makeText(context, hint, Toast.LENGTH_LONG);
        } else {
            toast.setText(hint);
            toast.setDuration(Toast.LENGTH_LONG);
        }

        toast.show();
    }


    /**
     * 网页数据提交中，处理非法字符
     */
    public static String stringToUrl(String url) {
        if (url.contains(" ")) {
            if (url.substring(url.length() - 1) == " ") {
                url = url.substring(0, url.length() - 1);
            } else {
                url = url.replace(" ", "%20");
            }
        }
        if (url.contains("\"")) {
            url = url.replace("\"", "%22");
        }
        if (url.contains("{")) {
            url = url.replace("{", "%7B");
        }
        if (url.contains("}")) {
            url = url.replace("{", "%7D");
        }
        return url;
    }


    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
//        if (context == null) {
//            context = ITMOApplication.getInstance();
//        }
//        String versionName = "";
//        try {
//            PackageManager pm = context.getPackageManager();
//            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
//            versionName = pi.versionName;
//            if (versionName == null || versionName.length() <= 0) {
//                return "";
//            }
//        } catch (Exception e) {
//            Log.e("VersionInfo", "Exception", e);
//        }
//        return versionName;
        return "3.0.8";
    }

    /**
     * 获取手机渠道
     *
     * @param
     * @return
     */
    public static String getAppChannel() {
//        String channel = "";
//        Context context = ITMOApplication.getInstance();
//        try {
//            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
//                    PackageManager.GET_META_DATA);
//            Bundle bundle = ai.metaData;
//            channel = bundle.getString("BaiduMobAd_CHANNEL");
//            if (!TextUtils.isEmpty(channel)) {
//                return channel;
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return channel;
        return "itmo";
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return stringToUrl(android.os.Build.MODEL);
    }


    /**
     * 请求连接加密
     *
     * @param url
     * @return
     */
    public static String enCodeUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }

        // 给所有连接加上手机信息
        if (url.contains("device_id")) {
            url = url + PHONE_INFO_WITHOUT_DEVICEID;
        } else {
            if (url.contains("?")) {
                url = url + PHONE_INFO;
            } else {
                url = url + PHONE_INFO_NO_PARAMS;
            }
        }

        return url;
    }

    public static String PHONE_INFO_WITHOUT_DEVICEID = "&client_ver=" + CommonUtil.getAppVersionName(null) + "&os_ver="
            + android.os.Build.VERSION.RELEASE + "&client=android" + "&channel=" + CommonUtil.getAppChannel()
            + "&device_model=" + CommonUtil.getPhoneModel();

    public static String PHONE_INFO = "&device_id=" + CommonUtil.getIMEI() + PHONE_INFO_WITHOUT_DEVICEID;

    public static String PHONE_INFO_NO_PARAMS = "?device_id=" + CommonUtil.getIMEI() + PHONE_INFO_WITHOUT_DEVICEID;

    /**
     * 加密值
     *
     * @param url
     * @return
     */
    @SuppressLint("DefaultLocale")
    public static String getEnCodeHeader(String url) {
        if (TextUtils.isEmpty(url)) {
            return url;
        }

        String key = url.toLowerCase();
        int start = key.indexOf("?") + 1;
        key = key.substring(start, key.length());
        key = CommonUtil.md5(key + KEY);
        key = key.substring(0, 16);

        return key;
    }

    /**
     * 设置评论数
     */
    public static String getComment(int num) {
        int w = 10000;
        if (1000 <= num && num < w) {
            num = num / 1000;
            return num + "千+";
        } else if (w <= num && num < w * 10) {
            num = num / w;
            return num + "万+";
        } else if (w * 100 <= num && num < w * 1000) {
            num = num / (w * 100);
            return num + "百万";
        } else if (w * 1000 <= num && num < w * w) {
            num = num / (w * 1000);
            return num + "千万";
        } else if (w * w <= num) {
            num = num / (w * w);
            return num + "亿";
        } else {
            return String.valueOf(num);
        }
    }
}
