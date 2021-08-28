package com.shuke.homepage.api;

import androidx.lifecycle.LiveData;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.details.model.entity.DetailsEntity;
import com.shuke.homepage.entity.NewsEntity;
import com.shuke.homepage.entity.NewsTypeEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @ClassName Api
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/20 20:36
 * @Version 1.0
 */
public interface Api {
    @GET("api/News/getNews?")
    LiveData<BaseRespEntity<List<NewsEntity.DataBean>>> getNews(@Query("newstype") int type, @Query("pagenum") int num, @Query("pagesize") int size);

    @GET("/api/NewsType/getAllTypes")
    LiveData<BaseRespEntity<ArrayList<NewsTypeEntity.DataBean>>> getType();

    @GET("/api/NewsDetail/getNewsDetail?")
    LiveData<BaseRespEntity<DetailsEntity>> getDeta(@Query("newscode") String newscode);
}
