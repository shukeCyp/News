package com.bw.headline.addheadline.viewmodel

import com.bw.headline.addheadline.repository.AddHeadLineRepository
import com.shuke.mvvmcore.BaseViewModel

/**
 *   @Author:YaPeng
 *   @Date:2021/8/30
 *   @Email:3536815334@qq.com
 */
class AddHeadLineViewModel : BaseViewModel<AddHeadLineRepository>() {
    override fun createRepository(): AddHeadLineRepository {
        return AddHeadLineRepository()
    }

    override fun releaseResource() {

    }

    override fun initResource() {
        TODO("Not yet implemented")
    }
}