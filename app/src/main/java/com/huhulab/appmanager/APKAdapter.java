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
        apkViewHolder.mApkIcon.setImageResource(R.mipmap.ic_launcher);
        apkViewHolder.mApkName.setText(apkInfo.apkName);
        apkViewHolder.mApkSize.setText(apkInfo.apkSize);
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
