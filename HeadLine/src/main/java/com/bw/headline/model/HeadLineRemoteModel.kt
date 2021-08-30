package com.bw.headline.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bw.headline.api.HeadLineApi
import com.bw.headline.entity.HeadLineRespEntity
import com.bw.zz.RetrofitFactory
import com.bw.zz.protocol.BaseRespEntity
import com.shuke.mvvmcore.IModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *   @Author:YaPeng
 *   @Date:2021/8/28
 *   @Email:3536815334@qq.com
 */
class HeadLineRemoteModel : IModel{

    fun getMyList():LiveData<BaseRespEntity<MutableList<HeadLineRespEntity>>>{
        return  RetrofitFactory.getMyRetrofit().createRetrofit()
                    .create(HeadLineApi::class.java)
                     .getList()
    }
}