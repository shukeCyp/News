package com.shuke.mvvmcore

import androidx.lifecycle.*

/**
 *   @Author:YaPeng
 *   @Date:2021/8/17
 *   @Email:3536815334@qq.com
 */

abstract class BaseViewModel<Repo : BaseRepository>()  : ViewModel() , LifecycleObserver {

    private lateinit var owner: LifecycleOwner

    constructor(lifecycle: LifecycleOwner) : this(){
        owner = lifecycle
        owner.lifecycle.addObserver(this)
    }

    protected lateinit var repo: Repo

    init {

        repo = createRepository()

    }

    /**
     * 创建并初始化Repository
     */
    
    abstract fun createRepository() : Repo


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun uiConnection(){
        initResource()
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun disConnection(){
        releaseResource()
        owner.lifecycle.removeObserver(this)
    }

    /**
     * 释放资源
     */
    abstract fun releaseResource()


    /**
     * 初始化资源
     */
    abstract fun initResource()


}