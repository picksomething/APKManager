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

public class APPFragment extends Fragment {

    private RecyclerView mAppRecyclerView;
    private LinearLayoutManager mAPPLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View appView = inflater.inflate(R.layout.layout_tab_app, container, false);
        mAppRecyclerView = (RecyclerView) appView.findViewById(R.id.appList);
        mAppRecyclerView.setHasFixedSize(true);
        mAPPLayoutManager = new LinearLayoutManager(this.getActivity());
        mAPPLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAppRecyclerView.setLayoutManager(mAPPLayoutManager);
        APPAdapter appAdapter = new APPAdapter(
                AppUtils.getAppList(this.getActivity().getApplicationContext()));
        mAppRecyclerView.setAdapter(appAdapter);
        return appView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private List<APPInfo> createList(int size) {

        List<APPInfo> appInfoList = new ArrayList<APPInfo>();
        for (int i = 1; i <= size; i++) {
            APPInfo appInfo = new APPInfo();
            appInfo.appName = "效率解锁清理";
            appInfo.appVersionName = "V1.2.12";
            appInfoList.add(appInfo);

        }
        return appInfoList;
    }
}
