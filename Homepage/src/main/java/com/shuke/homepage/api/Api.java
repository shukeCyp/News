package com.shuke.homepage.api;

import com.shuke.homepage.entity.NewsEntity;
import com.shuke.homepage.entity.NewsTypeEntity;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
    Observable<NewsEntity> getNews(@Query("newstype") int type, @Query("pagenum") int num, @Query("pagesize") int size);

    @GET("api/NewsType/getAllTypes")
    Observable<NewsTypeEntity> getType();
}
