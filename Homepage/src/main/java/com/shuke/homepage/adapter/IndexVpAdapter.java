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
public class IndexVpAdapter extends FragmentStatePagerAdapter {
    List<Fragment> fragments;
    List<String> titles;
    public IndexVpAdapter(FragmentManager fm, int behavior, List<Fragment> fragments, List<String> titles) {
        super(fm, behavior);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
