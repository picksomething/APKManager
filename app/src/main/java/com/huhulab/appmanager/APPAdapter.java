package com.huhulab.appmanager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class APPAdapter extends RecyclerView.Adapter<APPAdapter.APPViewHolder> {
    private List<APPInfo> mAppInfoList;

    public APPAdapter(List<APPInfo> mAppInfoList) {
        this.mAppInfoList = mAppInfoList;
    }


    @Override
    public APPViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.layout_app_item, viewGroup, false);

        return new APPViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(APPViewHolder appViewHolder, int i) {
        appViewHolder.mAppIcon.setImageResource(R.mipmap.ic_launcher);
        appViewHolder.mAppName.setText("hello.apk");
        appViewHolder.mAppVersion.setText("V1.0.00");
    }

    @Override
    public int getItemCount() {
        return mAppInfoList.size();
    }

    public static class APPViewHolder extends RecyclerView.ViewHolder {
        public ImageView mAppIcon;
        public TextView mAppName;
        public TextView mAppVersion;
        public Button mOpenIt;
        public Button mUninstallIt;

        public APPViewHolder(View v) {
            super(v);
            mAppIcon = (ImageView) v.findViewById(R.id.appIcon);
            mAppName = (TextView) v.findViewById(R.id.appName);
            mAppVersion = (TextView) v.findViewById(R.id.appVersion);
            mOpenIt = (Button) v.findViewById(R.id.delBt);
            mUninstallIt = (Button) v.findViewById(R.id.installBt);
        }
    }
}
