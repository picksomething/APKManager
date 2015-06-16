package com.huhulab.appmanager;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class APKFragment extends Fragment {

    private static final String TAG = "APK";

    private RecyclerView mApkRecyclerView;
    private LinearLayoutManager mAPKLayoutManager;
    private Context mContext;
    private APKAdapter mApkAdapter;
    private Dialog mLoading;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(TAG, "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        mContext = this.getActivity().getApplicationContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Log.d(TAG, "onCreateView");
        View apkView = inflater.inflate(R.layout.layout_tab_apk, container, false);
        mApkRecyclerView = (RecyclerView) apkView.findViewById(R.id.apkList);
        return apkView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
        mLoading = Utils.createLoadingDialog(this.getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        mLoading.show();
        mApkRecyclerView.setHasFixedSize(true);
        mApkRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAPKLayoutManager = new LinearLayoutManager(this.getActivity());
        mAPKLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mApkRecyclerView.setLayoutManager(mAPKLayoutManager);
        FileUtils.getResultList(mContext, onScanOverEvent);
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

    private FileUtils.OnScanOverEvent onScanOverEvent = new FileUtils.OnScanOverEvent() {
        @Override
        public void onScanOver(final List<APKInfo> apkInfoList) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    mApkAdapter = new APKAdapter(apkInfoList, mContext);
                    mApkRecyclerView.setAdapter(mApkAdapter);
                    mLoading.dismiss();
                }
            });

        }
    };
}
