package com.huhulab.apkmanager;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileUtils {
    private static int INSTALLED = 0;
    private static int UNINSTALLED = 1;
    private static int INSTALLED_UPDATE = 2;

    public static final String TAG = "file";
    public static List<APKInfo> apkInfoList = new ArrayList<>();
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);

    public static List<APKInfo> getResultList(final Context context, final OnScanOverEvent overEvent) {
        if (apkInfoList.size() != 0) {
            apkInfoList.clear();
        }
        fixedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                getAllFiles(new File("/sdcard"), context, overEvent);
            }
        });
        Log.d(TAG, "apkInfo List size =" + apkInfoList.size());
        return apkInfoList;
    }

    public static void getAllFiles(File root, Context context, OnScanOverEvent scanOverEvent) {
        if (!root.exists()) return;
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory() && f.getName().startsWith(".")) {
                    continue;
                }
                if (f.isDirectory() && f.canRead()) {
                    getAllFiles(f, context, scanOverEvent);
                } else if (f.isFile() && f.getName().endsWith(".apk")) {
                    APKInfo apkInfo = new APKInfo();
                    String apkPath = f.getAbsolutePath();
                    String apkSize = formatFileSize(getFileSize(f));
                    PackageManager pm = context.getPackageManager();
                    PackageInfo packageInfo = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
                    if (packageInfo != null) {
                        ApplicationInfo appInfo = packageInfo.applicationInfo;
                        if (Build.VERSION.SDK_INT >= 8) {
                            appInfo.sourceDir = apkPath;
                            appInfo.publicSourceDir = apkPath;
                        }
                        Drawable apk_icon = appInfo.loadIcon(pm);
                        apkInfo.apkIcon = apk_icon;
                        apkInfo.apkName = f.getName().substring(0, f.getName().lastIndexOf("."));
                        apkInfo.apkSize = apkSize;
                        apkInfo.apkPath = apkPath;

                        String packageName = packageInfo.packageName;
                        // apkInfo.apkPackage = packageName;
                        String versionName = packageInfo.versionName;
                        apkInfo.apkVersionName = versionName;
                        int versionCode = packageInfo.versionCode;
                        apkInfo.apkVersionCode = versionCode;
                        int type = doType(pm, packageName, versionCode);
                        apkInfo.apkType = type;
                        apkInfoList.add(apkInfo);
                        scanOverEvent.onScanOver(apkInfoList);
                    }
                }
            }
        }
    }

    /*
     * 判断该应用是否在手机上已经安装过，有以下集中情况出现
     * 1.未安装，这个时候按钮应该是“安装”点击按钮进行安装
     * 2.已安装，按钮显示“已安装” 可以卸载该应用
     * 3.已安装，但是版本有更新，按钮显示“更新” 点击按钮就安装应用
     */

    /**
     * 判断该应用在手机中的安装情况
     *
     * @param pm          PackageManager
     * @param packageName 要判断应用的包名
     * @param versionCode 要判断应用的版本号
     */

    private static int doType(PackageManager pm, String packageName, int versionCode) {
        List<PackageInfo> packageInfos = pm.getInstalledPackages(
                PackageManager.GET_UNINSTALLED_PACKAGES);
        for (PackageInfo pi : packageInfos) {
            String pi_packageName = pi.packageName;
            int pi_versionCode = pi.versionCode;
            if (packageName.equals(pi_packageName)) {
                if (versionCode == pi_versionCode) {
                    Log.d(TAG, "have installed don't need update");
                    return INSTALLED;
                } else if (versionCode > pi_versionCode) {
                    Log.d(TAG, "have installed but can update");
                    return INSTALLED_UPDATE;
                }
            }
        }
        Log.d(TAG, "not installed");
        return UNINSTALLED;
    }


    private static long getFileSize(File file) {
        long size = 0;
        try {
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                size = fis.available();
            } else {
                file.createNewFile();
                Log.d(TAG, "file not exist");
            }
        } catch (Exception e) {
            Log.d(TAG, "get file size error " + e);
        }
        return size;
    }

    public static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString;
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public interface OnScanOverEvent {
        void onScanOver(List<APKInfo> apkInfoList);
    }
}
