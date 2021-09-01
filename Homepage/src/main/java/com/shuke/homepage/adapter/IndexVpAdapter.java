package com.shuke.homepage.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

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

    public IndexVpAdapter(FragmentManager fm, int behavior, List<Fragment> fragments) {
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
