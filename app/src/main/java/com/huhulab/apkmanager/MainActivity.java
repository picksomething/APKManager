package com.huhulab.apkmanager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private static final String TAG = "apk";

    private List<Fragment> mFragmentList = new ArrayList<>();
    private FragmentPagerAdapter mFragmentAdapter;

    private ViewPager mPager;

    private TextView mTabAPK, mTabAPP;
    private LinearLayout mTabAPKLL, mTabAPPLL;
    private ImageView mTabLine;

    private APKFragment mAPKFragment;
    private APPFragment mAPPFragment;

    private int mCurrentIndex;
    private int mScreenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
        initDatas();
        initTabLineWidth();
    }

    private void initTabLineWidth() {
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mScreenWidth = metrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();
        lp.width = mScreenWidth / 2;
        mTabLine.setLayoutParams(lp);
    }

    private void initDatas() {
        mAPKFragment = new APKFragment();
        mAPPFragment = new APPFragment();
        mFragmentList.add(mAPKFragment);
        mFragmentList.add(mAPPFragment);
        mFragmentAdapter = new FragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mPager.setAdapter(mFragmentAdapter);
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(mPageListener);
    }

    private void setListeners() {
        mTabAPKLL.setOnClickListener(this);
        mTabAPPLL.setOnClickListener(this);
    }

    private void findViews() {
        mTabAPK = (TextView) findViewById(R.id.apk_tv);
        mTabAPP = (TextView) findViewById(R.id.app_tv);
        mTabAPKLL = (LinearLayout) findViewById(R.id.tab_apk_ll);
        mTabAPPLL = (LinearLayout) findViewById(R.id.tab_app_ll);
        mTabLine = (ImageView) findViewById(R.id.tab_line_iv);
        mPager = (ViewPager) findViewById(R.id.viewPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_apk_ll:
                mPager.setCurrentItem(0);
                break;
            case R.id.tab_app_ll:
                mPager.setCurrentItem(1);
                break;
            default:
                break;
        }
    }

    private ViewPager.OnPageChangeListener mPageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float offset, int offsetPixels) {
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLine.getLayoutParams();
            Log.d(TAG, offset + "");
            if (mCurrentIndex == 0 && position == 0) {
                lp.leftMargin = (int) (offset * (mScreenWidth * 1.0 / 2))
                        + mCurrentIndex * (mScreenWidth / 2);
            } else if (mCurrentIndex == 1 && position == 0) {
                lp.leftMargin = (int) (-(1 - offset) * (mScreenWidth * 1.0 / 2)
                        + mCurrentIndex * (mScreenWidth / 2));
            }
            mTabLine.setLayoutParams(lp);
        }

        @Override
        public void onPageSelected(int position) {
            resetTabTitle();
            switch (position) {
                case 0:
                    mTabAPK.setTextColor(getResources().getColor(R.color.tiffany_blue));
                    break;
                case 1:
                    mTabAPP.setTextColor(getResources().getColor(R.color.tiffany_blue));
                    break;
                default:
                    break;
            }
            mCurrentIndex = position;
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            Log.d(TAG, "onPageScrollStateChanged");
        }
    };

    private void resetTabTitle() {
        mTabAPK.setTextColor(Color.BLACK);
        mTabAPP.setTextColor(Color.BLACK);
    }
}
