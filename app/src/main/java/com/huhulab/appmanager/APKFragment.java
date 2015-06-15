package com.huhulab.appmanager;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class APKFragment extends Fragment {

    private RecyclerView mApkRecyclerView;
    private LinearLayoutManager mAPKLayoutManager;
    private Context mContext;
    private APKAdapter mApkAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View apkView = inflater.inflate(R.layout.layout_tab_apk, container, false);
        mContext = this.getActivity().getApplicationContext();
        mApkRecyclerView = (RecyclerView) apkView.findViewById(R.id.apkList);
        mApkRecyclerView.setHasFixedSize(true);
        mApkRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAPKLayoutManager = new LinearLayoutManager(this.getActivity());
        mAPKLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mApkRecyclerView.setLayoutManager(mAPKLayoutManager);
        FileUtils.getResultList(mContext, onScanOverEvent);
        return apkView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private FileUtils.OnScanOverEvent onScanOverEvent = new FileUtils.OnScanOverEvent() {
        @Override
        public void onScanOver(final List<APKInfo> apkInfoList) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    mApkAdapter = new APKAdapter(apkInfoList, mContext);
                    mApkRecyclerView.setAdapter(mApkAdapter);
                }
            });

        }
    };
}
