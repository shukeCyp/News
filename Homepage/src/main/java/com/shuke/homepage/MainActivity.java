package com.shuke.homepage;


import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.shuke.homepage.adapter.MainVpAdapter;
import com.shuke.homepage.fragment.IndexFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面、接收各个fragment
 */
public class MainActivity extends AppCompatActivity {

    private ViewPager mainVp;
    private Button sure;

    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b);

        //沉浸式
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

//        ViewGroup viewById = this.findViewById(android.R.id.content);
//        View childAt = viewById.getChildAt(0);
//        if (childAt != null && Build.VERSION.SDK_INT >= 14) {
//            childAt.setFitsSystemWindows(true);
//        }

        initView();

        fragments.add(new IndexFragment());

        mainVp.setAdapter(new MainVpAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments));

        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainVp.setCurrentItem(0);
            }
        });
    }

    private void initView() {
        mainVp = (ViewPager) findViewById(R.id.main_vp);
        sure = (Button) findViewById(R.id.sure);
    }
}