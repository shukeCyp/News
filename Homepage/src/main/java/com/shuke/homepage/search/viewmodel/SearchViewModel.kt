package com.shuke.homepage.search.viewmodel

import com.shuke.homepage.search.repository.SearchRepository
import com.shuke.mvvmcore.BaseViewModel

/**
 *   @Author:YaPeng
 *   @Date:2021/8/20
 *   @Email:3536815334@qq.com
 */
class SearchViewModel : BaseViewModel<SearchRepository>() {
    override fun createRepository(): SearchRepository {
        return SearchRepository()
    }

    override fun releaseResource() {

    }

    override fun initResource() {

    }
}