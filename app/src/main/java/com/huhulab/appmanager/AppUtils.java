package com.huhulab.appmanager;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AppUtils {

    private static final String TAG = "app";

    private static List<APPInfo> appList = new ArrayList<>();
    private static List<PackageInfo> packages = new ArrayList<>();

    public static List<APPInfo> getAppList(Context context) {
        getInstallApps(context);
        Log.d(TAG, "install app size " + packages.size());
        getAPPInfo(context);
        Log.d(TAG, "app info size " + appList.size());
        return appList;
    }


    public static void getInstallApps(Context context) {
        packages = context.getPackageManager().getInstalledPackages(0);
    }


    public static void getAPPInfo(Context context) {
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
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
