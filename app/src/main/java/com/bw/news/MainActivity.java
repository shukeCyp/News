package com.bw.news;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bw.news.adapter.MainViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> fragments;
    private ViewPager mainVp;
    private BottomNavigationBar mainBottombar;

    private MainViewPagerAdapter mainViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initBottomView();

        clearBar();


    }

    /**
     * 沉浸式
     */
    private void clearBar() {
        if (Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR|
                    View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 初始化并设置底部导航栏
     */
    private void initBottomView() {
        mainBottombar.setMode(BottomNavigationBar.MODE_FIXED)
                .setInActiveColor("#939393")
                .setActiveColor("#FB575C")
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        mainBottombar.addItem(new BottomNavigationItem(R.drawable.index, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.video, "视频"))
                .addItem(new BottomNavigationItem(R.drawable.more, "微头条"))
                .addItem(new BottomNavigationItem(R.drawable.mine, "我的"))
                .initialise();
        //BottomView 和 ViewPager联动
        fragments = new ArrayList<>();
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment());

        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments);
        mainVp.setAdapter(mainViewPagerAdapter);

        mainVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        mainBottombar.selectTab(0);
                        break;
                    case 1:
                        mainBottombar.selectTab(1);
                        break;
                    case 2:
                        mainBottombar.selectTab(2);
                        break;
                    case 3:
                        mainBottombar.selectTab(3);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mainBottombar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position){
                    case 0:
                        mainVp.setCurrentItem(0);
                        break;
                    case 1:
                        mainVp.setCurrentItem(1);
                        break;
                    case 2:
                        mainVp.setCurrentItem(2);
                        break;
                    case 3:
                        mainVp.setCurrentItem(3);
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }


    private void initView() {
        mainVp = (ViewPager) findViewById(R.id.main_vp);
        mainBottombar = (BottomNavigationBar) findViewById(R.id.main_bottombar);
    }
}