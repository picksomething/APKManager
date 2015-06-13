package com.huhulab.appmanager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class APKFragment extends Fragment {

    private RecyclerView mApkRecyclerView;
    private LinearLayoutManager mAPKLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View apkView = inflater.inflate(R.layout.layout_tab_apk, container, false);
        mApkRecyclerView = (RecyclerView) apkView.findViewById(R.id.apkList);
        mApkRecyclerView.setHasFixedSize(true);
        mAPKLayoutManager = new LinearLayoutManager(this.getActivity());
        mAPKLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mApkRecyclerView.setLayoutManager(mAPKLayoutManager);
        APKAdapter apkAdapter = new APKAdapter(
                FileUtils.getResultList(this.getActivity()));
        mApkRecyclerView.setAdapter(apkAdapter);
        return apkView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private List<APKInfo> createList(int size) {

        List<APKInfo> apkInfoList = new ArrayList<APKInfo>();
        for (int i = 1; i <= size; i++) {
            APKInfo appInfo = new APKInfo();
            appInfo.apkName = "效率解锁清理";
            appInfo.apkSize = "0.17M";
            apkInfoList.add(appInfo);

        }
        return apkInfoList;
    }
}
