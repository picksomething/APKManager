package com.huhulab.appmanager;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.List;

public class APKAdapter extends RecyclerView.Adapter<APKAdapter.APKViewHolder> {

    private List<APKInfo> mApkInfoList;
    private Context mContext;

    public APKAdapter(List<APKInfo> mApkInfoList, Context mContext) {
        this.mApkInfoList = mApkInfoList;
        this.mContext = mContext;
    }

    @Override
    public APKAdapter.APKViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_apk_item, viewGroup, false);
        return new APKViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(APKViewHolder apkViewHolder, final int i) {
        APKInfo apkInfo = mApkInfoList.get(i);
        if (apkInfo.apkIcon == null) {
            apkViewHolder.mApkIcon.setImageResource(R.mipmap.ic_launcher);
        } else {
            apkViewHolder.mApkIcon.setImageDrawable(apkInfo.apkIcon);
        }
        apkViewHolder.mApkName.setText(apkInfo.apkName);
        apkViewHolder.mApkSize.setText(apkInfo.apkSize);
        apkViewHolder.mDelApk.setOnClickListener(new MyButtonListener(i));
        switch (apkInfo.apkType) {
            case 0:
                apkViewHolder.mInstallApk.setText("已安装");
                apkViewHolder.mInstallApk.setEnabled(false);
                apkViewHolder.mInstallApk.setAlpha(0.3f);
                break;
            case 1:
                apkViewHolder.mInstallApk.setEnabled(true);
                apkViewHolder.mInstallApk.setText("安装");
                apkViewHolder.mInstallApk.setAlpha(1.0f);
                apkViewHolder.mInstallApk.setOnClickListener(new MyButtonListener(i));
                break;
            case 2:
                apkViewHolder.mInstallApk.setEnabled(true);
                apkViewHolder.mInstallApk.setText("可更新");
                apkViewHolder.mInstallApk.setAlpha(1.0f);
                apkViewHolder.mInstallApk.setOnClickListener(new MyButtonListener(i));
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mApkInfoList.size();
    }

    public static class APKViewHolder extends RecyclerView.ViewHolder {
        public ImageView mApkIcon;
        public TextView mApkName;
        public TextView mApkSize;
        public Button mDelApk;
        public Button mInstallApk;

        public APKViewHolder(View v) {
            super(v);
            mApkIcon = (ImageView) v.findViewById(R.id.apkIcon);
            mApkName = (TextView) v.findViewById(R.id.apkName);
            mApkSize = (TextView) v.findViewById(R.id.apkSize);
            mDelApk = (Button) v.findViewById(R.id.delBt);
            mInstallApk = (Button) v.findViewById(R.id.installBt);
        }
    }

    public class MyButtonListener implements View.OnClickListener {

        public int position;

        private MyButtonListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            Log.d("apk", "on click and position = " + position);
            switch (view.getId()) {
                case R.id.delBt:
                    File apkFile = new File(mApkInfoList.get(position).apkPath);
                    if (apkFile.exists()) apkFile.delete();
                    Log.d("apk", "file is deleted");
                    mApkInfoList.remove(position);
                    notifyItemRemoved(position);
                    break;
                case R.id.installBt:
                    startToInstall(mApkInfoList.get(position).apkPath);
                    break;
                default:
                    break;
            }
        }
    }

    public void startToInstall(String path) {
        try {
            Runtime.getRuntime().exec("chmod 777 " + path).waitFor();
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setDataAndType(Uri.fromFile(
                    new File(path)), "application/vnd.android.package-archive");
            mContext.startActivity(i);
        } catch (Exception e) {
            Log.d("apk", "startToInstall fail exception = " + e);
        }
    }
}
