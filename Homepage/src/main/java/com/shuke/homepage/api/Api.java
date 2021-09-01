package com.shuke.homepage.api;

import androidx.lifecycle.LiveData;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.entity.CommentEntity;
import com.shuke.homepage.details.model.entity.DetailsEntity;
import com.shuke.homepage.entity.NewsEntity;
import com.shuke.homepage.entity.NewsTypeEntity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;


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
    //获取新闻
    @GET("api/News/getNews?")
    LiveData<BaseRespEntity<List<NewsEntity.DataBean>>> getNews(@Query("newstype") int type, @Query("pagenum") int num, @Query("pagesize") int size);

    //获取兴趣类别
    @GET("/api/NewsType/getAllTypes")
    LiveData<BaseRespEntity<ArrayList<NewsTypeEntity.DataBean>>> getType();

    //获取新闻详情
    @GET("/api/NewsDetail/getNewsDetail?")
    LiveData<BaseRespEntity<DetailsEntity>> getDeta(@Query("newscode") String newscode);


    //保存评论信息
    @POST("/api/Comment/addComment")
    LiveData<BaseRespEntity<String>> push(@Body RequestBody body);

    @GET("api/Comment/getComment?")
    LiveData<BaseRespEntity<ArrayList<CommentEntity>>> getCommtexs(@Query("newscode") String newscode, @Query("parentid") int parentid, @Query("userid") int userid);

}
