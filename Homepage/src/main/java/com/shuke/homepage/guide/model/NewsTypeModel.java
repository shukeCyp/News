package com.shuke.homepage.guide.model;

import com.bw.zz.RetrofitFactory;
import com.shuke.homepage.api.Api;
import com.shuke.homepage.entity.NewsTypeEntity;
import com.shuke.mvvmcore.IModel;

import io.reactivex.Observer;
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

    public void getType(Observer<NewsTypeEntity> observer) {
        RetrofitFactory.getMyRetrofit().createRetrofit()
                .create(Api.class)
                .getType()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

    }
}
