package com.huhulab.appmanager;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {

    private static final String TAG = "app";
    private static List<APPInfo> appList = new ArrayList<>();

    public static List<APPInfo> getAppList(Context context) {
        if(appList.size() != 0){
            appList.clear();
        }
        getAPPInfo(context);
        Log.d(TAG, "app info size " + appList.size());
        return appList;
    }

    public static void getAPPInfo(Context context) {
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                Log.d(TAG, "system app");
                APPInfo tmpInfo = new APPInfo();
                tmpInfo.appName = packageInfo.applicationInfo.loadLabel(
                        context.getPackageManager()).toString();
                Log.d(TAG, "appName = " + tmpInfo.appName);
                tmpInfo.appPackage = packageInfo.packageName;
                Log.d(TAG, "appPackage = " + packageInfo.packageName);
                tmpInfo.appVersionName = packageInfo.versionName;
                Log.d(TAG, "appVersionName = " + packageInfo.versionName);
                tmpInfo.appVersionCode = packageInfo.versionCode;
                Log.d(TAG, "appVersionCode = " + packageInfo.versionCode);
                tmpInfo.appIcon = packageInfo.applicationInfo.loadIcon(context.getPackageManager());
                appList.add(tmpInfo);
            }
        }
    }
}
