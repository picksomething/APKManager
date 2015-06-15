package com.huhulab.appmanager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class APPAdapter extends RecyclerView.Adapter<APPAdapter.APPViewHolder> {
    private List<APPInfo> mAppInfoList;
    private Context mContext;

    public APPAdapter(List<APPInfo> mAppInfoList, Context mContext) {
        Log.d("app", "data size is " + mAppInfoList.size());
        this.mAppInfoList = mAppInfoList;
        this.mContext = mContext;
    }


    @Override
    public APPAdapter.APPViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.layout_app_item, viewGroup, false);
        return new APPViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(APPViewHolder appViewHolder, int i) {
        APPInfo appInfo = mAppInfoList.get(i);
        appViewHolder.mAppIcon.setImageDrawable(appInfo.appIcon);
        appViewHolder.mAppName.setText(appInfo.appName);
        appViewHolder.mAppVersion.setText(appInfo.appVersionName);
        appViewHolder.mOpenIt.setOnClickListener(new MyButtonListener(i));
        appViewHolder.mUninstallIt.setOnClickListener(new MyButtonListener(i));
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
        public ImageButton mUninstallIt;

        public APPViewHolder(View v) {
            super(v);
            mAppIcon = (ImageView) v.findViewById(R.id.appIcon);
            mAppName = (TextView) v.findViewById(R.id.appName);
            mAppVersion = (TextView) v.findViewById(R.id.appVersion);
            mOpenIt = (Button) v.findViewById(R.id.openIt);
            mUninstallIt = (ImageButton) v.findViewById(R.id.uninstallIt);
        }
    }

    private class MyButtonListener implements View.OnClickListener {

        public int position;

        private MyButtonListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            Log.d("apk", "on click and position = " + position);
            switch (view.getId()) {
                case R.id.openIt:
                    openApp(mAppInfoList.get(position).appPackage);
                    break;
                case R.id.uninstallIt:
                    uninstallAPK(mAppInfoList.get(position).appPackage);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * del app
     *
     * @param packageName
     */
    public void uninstallAPK(String packageName) {
        Uri uri = Uri.parse("package:" + packageName);
        Intent intent = new Intent(Intent.ACTION_DELETE, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * open app
     *
     * @param packageName
     */
    public void openApp(String packageName) {
        PackageManager pm = mContext.getPackageManager();
        Intent i = pm.getLaunchIntentForPackage(packageName);
        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (i != null)
            mContext.startActivity(i);
    }
}
