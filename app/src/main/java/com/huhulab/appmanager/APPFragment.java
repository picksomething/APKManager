package com.huhulab.appmanager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class APPFragment extends Fragment {

    private RecyclerView mAppRecyclerView;
    private LinearLayoutManager mAPPLayoutManager;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View appView = inflater.inflate(R.layout.layout_tab_app, container, false);
        mContext = this.getActivity().getApplicationContext();
        mAppRecyclerView = (RecyclerView) appView.findViewById(R.id.appList);
        mAppRecyclerView.setHasFixedSize(true);
        mAPPLayoutManager = new LinearLayoutManager(this.getActivity());
        mAPPLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAppRecyclerView.setLayoutManager(mAPPLayoutManager);
        APPAdapter appAdapter = new APPAdapter(
                AppUtils.getAppList(mContext), mContext);
        mAppRecyclerView.setAdapter(appAdapter);
        return appView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
