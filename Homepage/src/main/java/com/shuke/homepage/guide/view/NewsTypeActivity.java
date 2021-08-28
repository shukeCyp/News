package com.shuke.homepage.guide.view;

import android.content.Intent;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.BR;
import com.shuke.homepage.MainActivity;
import com.shuke.homepage.R;
import com.shuke.homepage.custom.CustomHobbyType;
import com.shuke.homepage.databinding.NewsType;
import com.shuke.homepage.entity.NewsTypeEntity;
import com.shuke.homepage.guide.viewmodel.NewsTypeViewModel;
import com.shuke.mvvmcore.view.MVVMActivity;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

//引导页，获取新闻类别
public class NewsTypeActivity extends MVVMActivity<NewsType, NewsTypeViewModel> {

    private CustomHobbyType custom;
    private Button customNext;

    @Override
    public Map<Integer, Object> initVarMap(Map<Integer, Object> vars) {
        vars.put(BR.mMine,this);
        return vars;
    }

    @Override
    public NewsTypeViewModel createViewModel() {
        return new NewsTypeViewModel(this);
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
        //获取兴趣分类
        viewModel.getType().observe(this, new Observer<BaseRespEntity<ArrayList<NewsTypeEntity.DataBean>>>() {
            @Override
            public void onChanged(BaseRespEntity<ArrayList<NewsTypeEntity.DataBean>> entity) {
                Log.d("123", "onChanged: " + entity.getData().toString());
                ArrayList<NewsTypeEntity.DataBean> data = entity.getData();

                for (int i = 0; i < data.size(); i++){
                    custom.add(data.get(i).getTypename());
                }
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