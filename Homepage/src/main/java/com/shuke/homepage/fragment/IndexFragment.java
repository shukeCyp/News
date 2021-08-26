package com.shuke.homepage.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.android.material.tabs.TabLayout;
import com.shuke.homepage.R;
import com.shuke.homepage.adapter.MainVpAdapter;
import com.shuke.homepage.custom.CustomHobbyType;
import com.shuke.homepage.guide.view.NewsTypeActivity;
import com.shuke.homepage.news.view.NewsFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 */
public class IndexFragment extends Fragment {

    TabLayout tab;
    EditText search;
    ViewPager vp;
    List<Fragment> fragments = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_index, container, false);

        tab = inflate.findViewById(R.id.index_tab);
        vp = inflate.findViewById(R.id.index_vp);
        search = inflate.findViewById(R.id.index_search);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/homepage/searchActivity").navigation();
            }
        });

        fragments.add(new NewsFragment());

        //将选中的数据循环添加到tablayout中
        for (int i = 0; i < CustomHobbyType.texts.size(); i++) {
            tab.addTab(tab.newTab().setText(CustomHobbyType.texts.get(i)));
        }

        vp.setAdapter(new MainVpAdapter(getActivity().getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments));
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.scale_style);
        animation.setFillAfter(true);


        //tablayout选择监听
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String text = tab.getText().toString();

                @SuppressLint("ObjectAnimatorBinding") ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tab.getPosition(), "scaleX",  1, 2, 3, 2, 1,0.5f, 0.1f);
                objectAnimator.setDuration(1000);
                objectAnimator.start();



                Toast.makeText(getActivity(), ""+  tab.getText().toString(), Toast.LENGTH_SHORT).show();
                vp.setCurrentItem(0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return inflate;
    }
}