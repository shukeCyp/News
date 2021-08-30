package com.bw.headline.api

import androidx.lifecycle.LiveData
import com.bw.headline.entity.HeadLineRespEntity
import com.bw.zz.protocol.BaseRespEntity
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *   @Author:YaPeng
 *   @Date:2021/8/28
 *   @Email:3536815334@qq.com
 */
interface HeadLineApi {

    @GET("api/HeadLine/getHeadlineByUserId?userid=513")
    fun getList():LiveData<BaseRespEntity<MutableList<HeadLineRespEntity>>>

}