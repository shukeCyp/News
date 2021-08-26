package com.shuke.homepage.search.repository

import com.shuke.homepage.search.model.SearchHistoryModel
import com.shuke.mvvmcore.BaseRepository
import com.shuke.mvvmcore.annotation.Model

/**
 *   @Author:YaPeng
 *   @Date:2021/8/20
 *   @Email:3536815334@qq.com
 */
class SearchRepository : BaseRepository() {

    @Model
    lateinit var searchHistoryModel:SearchHistoryModel
}