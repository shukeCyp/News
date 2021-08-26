package com.shuke.homepage.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @ClassName MainVpAdapter
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/20 15:02
 * @Version 1.0
 */
public class MainVpAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragments;
    public MainVpAdapter(FragmentManager fm, int behavior, List<Fragment> fragments) {
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
