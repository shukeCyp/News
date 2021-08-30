package com.bw.headline.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bw.headline.entity.HeadLineRespEntity
import com.bw.headline.model.HeadLineLocalModel
import com.bw.headline.model.HeadLineRemoteModel
import com.bw.zz.protocol.BaseRespEntity
import com.shuke.mvvmcore.BaseRepository
import com.shuke.mvvmcore.annotation.Model
import io.reactivex.Observer

/**
 *   @Author:YaPeng
 *   @Date:2021/8/27
 *   @Email:3536815334@qq.com
 */
class HeadLineRepository : BaseRepository() {

    /**
     * 本地Model
     */
    @Model
    lateinit var localModel:HeadLineLocalModel

    /**
     * 远程Model
     */
    @Model
    lateinit var remoteModel:HeadLineRemoteModel

    /**
     * 获取数据
     */
    fun getHeadLineList():LiveData<BaseRespEntity<MutableList<HeadLineRespEntity>>>{
        return remoteModel.getMyList()
    }
}