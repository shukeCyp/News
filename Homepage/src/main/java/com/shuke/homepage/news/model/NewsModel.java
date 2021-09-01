package com.shuke.homepage.news.model;

import androidx.lifecycle.LiveData;

import com.bw.zz.RetrofitFactory;
import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.api.Api;
import com.shuke.homepage.entity.NewsEntity;
import com.shuke.mvvmcore.IModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName NewsModel
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/20 20:18
 * @Version 1.0
 */
public class NewsModel implements IModel {

    public LiveData<BaseRespEntity<List<NewsEntity.DataBean>>> getNews(int num) {

        return RetrofitFactory.getMyRetrofit()
                .createRetrofit()
                .create(Api.class)
                .getNews(1,num,10);

    }
}
