package com.shuke.homepage.guide.view;

import android.content.Intent;

import android.view.View;
import android.widget.Button;

import com.shuke.homepage.BR;
import com.shuke.homepage.MainActivity;
import com.shuke.homepage.R;
import com.shuke.homepage.custom.CustomHobbyType;
import com.shuke.homepage.databinding.NewsType;
import com.shuke.homepage.entity.NewsTypeEntity;
import com.shuke.homepage.guide.viewmodel.NewsTypeViewModel;
import com.shuke.mvvmcore.view.MVVMActivity;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

//引导页，获取新闻类别
public class NewsTypeActivity extends MVVMActivity<NewsType, NewsTypeViewModel> {

    private CustomHobbyType custom;
    private Button customNext;

    public static List<NewsTypeEntity.DataBean> data = new ArrayList<>();

    @Override
    public Map<Integer, Object> initVarMap(Map<Integer, Object> vars) {
        vars.put(BR.mMine,this);
        return vars;
    }

    @Override
    public NewsTypeViewModel createViewModel() {
        return new NewsTypeViewModel();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_newstype;
    }

    @Override
    public void loadData() {
        customNext = findViewById(R.id.custom_next);
        custom = findViewById(R.id.custom);
    }

    @Override
    public void initEvent() {

        viewModel.getType(new Observer<NewsTypeEntity>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewsTypeEntity newsTypeEntity) {
                data = newsTypeEntity.getData();
                for (int i = 0; i < data.size(); i++) {
                    custom.add(data.get(i).getTypename());
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        customNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewsTypeActivity.this, MainActivity.class));
            }
        });
    }

    private void initView() {
    }

}