package com.huhulab.appmanager;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class APKAdapter extends RecyclerView.Adapter<APKAdapter.APKViewHolder> {

    private List<APKInfo> mApkInfoList;

    public APKAdapter(List<APKInfo> mApkInfoList) {
        this.mApkInfoList = mApkInfoList;
    }

    @Override
    public APKAdapter.APKViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_apk_item, viewGroup, false);
        return new APKViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(APKViewHolder apkViewHolder, int i) {
        APKInfo apkInfo = mApkInfoList.get(i);
        if (apkInfo.apkIcon == null) {
            apkViewHolder.mApkIcon.setImageResource(R.mipmap.ic_launcher);
        } else {
            apkViewHolder.mApkIcon.setImageDrawable(apkInfo.apkIcon);
        }
        apkViewHolder.mApkName.setText(apkInfo.apkName);
        apkViewHolder.mApkSize.setText(apkInfo.apkSize);
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
                break;
            case 2:
                apkViewHolder.mInstallApk.setEnabled(true);
                apkViewHolder.mInstallApk.setText("更新");
                apkViewHolder.mInstallApk.setAlpha(1.0f);
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
}
