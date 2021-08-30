package com.bw.headline.viewmodel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bw.headline.entity.HeadLineRespEntity
import com.bw.headline.repository.HeadLineRepository
import com.bw.zz.protocol.BaseRespEntity
import com.shuke.mvvmcore.BaseViewModel

/**
 *   @Author:YaPeng
 *   @Date:2021/8/27
 *   @Email:3536815334@qq.com
 */
class HeadLineViewModel : BaseViewModel<HeadLineRepository> {

    constructor(lifecycle: LifecycleOwner):super(lifecycle)

    override fun createRepository(): HeadLineRepository {
        return HeadLineRepository()
    }



    /**
     * 释放资源
     */
    override fun releaseResource() {

    }

    /**
     * 初始化资源
     */
    override fun initResource() {

    }

    /**
     * 获取数据
     */
    fun getHeadLineList():LiveData<BaseRespEntity<MutableList<HeadLineRespEntity>>>{
        return repo.getHeadLineList()
    }

}