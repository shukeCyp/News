package com.shuke.homepage.guide.model;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.bw.zz.RetrofitFactory;
import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.api.Api;
import com.shuke.homepage.entity.NewsEntity;
import com.shuke.homepage.entity.NewsTypeEntity;
import com.shuke.mvvmcore.IModel;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @ClassName NewsTypeModel
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/22 20:14
 * @Version 1.0
 */
public class NewsTypeModel implements IModel {

    public LiveData<BaseRespEntity<ArrayList<NewsTypeEntity.DataBean>>> getType() {
        return   RetrofitFactory.getMyRetrofit()
                .createRetrofit()
                .create(Api.class)
                .getType();
    }
}
