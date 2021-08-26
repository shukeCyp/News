package com.shuke.homepage.search.db

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 *   @Author:YaPeng
 *   @Date:2021/8/22
 *   @Email:3536815334@qq.com
 */
@Database(entities = [SearchHistoryEntity::class],version = 1,exportSchema = false)
 abstract class SearchDB : RoomDatabase() {
    abstract fun searchHistoryDao():SearchHistoryDao


}