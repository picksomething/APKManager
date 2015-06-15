package com.huhulab.appmanager;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class APKFragment extends Fragment {

    private RecyclerView mApkRecyclerView;
    private LinearLayoutManager mAPKLayoutManager;
    private Context mContext;

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
        APKAdapter apkAdapter = new APKAdapter(
                FileUtils.getResultList(mContext), mContext);
        mApkRecyclerView.setAdapter(apkAdapter);
        return apkView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
