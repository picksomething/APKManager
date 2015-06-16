package com.huhulab.apkmanager;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class APPFragment extends Fragment {

    private static final String TAG = "APP";

    private RecyclerView mAppRecyclerView;
    private LinearLayoutManager mAPPLayoutManager;
    private Context mContext;
    private List<APPInfo> mAppList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getActivity().getApplicationContext();
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView");
        View appView = inflater.inflate(R.layout.layout_tab_app, container, false);
        mAppRecyclerView = (RecyclerView) appView.findViewById(R.id.appList);
        return appView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        mAppRecyclerView.setHasFixedSize(true);
        mAPPLayoutManager = new LinearLayoutManager(this.getActivity());
        mAPPLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mAppRecyclerView.setLayoutManager(mAPPLayoutManager);
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        mAppList = AppUtils.getAppList(mContext);
        APPAdapter appAdapter = new APPAdapter(mAppList, mContext);
        mAppRecyclerView.setAdapter(appAdapter);
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
