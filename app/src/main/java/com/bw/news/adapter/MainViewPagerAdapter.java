package com.bw.news.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public
/**
 *   @Author:YaPeng
 *   @Date:2021/8/25
 *   @Email:3536815334@qq.com
 */
class MainViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;

    public MainViewPagerAdapter(FragmentManager fm, int behavior, List<Fragment> fragments) {
        super(fm, behavior);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
