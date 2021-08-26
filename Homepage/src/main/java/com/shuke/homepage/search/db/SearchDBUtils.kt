package com.shuke.homepage.search.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 *   @Author:YaPeng
 *   @Date:2021/8/22
 *   @Email:3536815334@qq.com
 */
object SearchDBUtils {


    fun getSearchDB(context: Context): SearchDB{
        return Room.databaseBuilder(
            context.applicationContext,
            SearchDB::class.java, "News"
        ).build()
    }
}