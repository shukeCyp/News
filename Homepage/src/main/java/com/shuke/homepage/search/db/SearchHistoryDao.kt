package com.shuke.homepage.search.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 *   @Author:YaPeng
 *   @Date:2021/8/22
 *   @Email:3536815334@qq.com
 */
@Dao
interface SearchHistoryDao {
    @Insert
    fun insertEntity(entity: SearchHistoryEntity)

    @Query(value = "SELECT * FROM HISTORYS")
    fun queryAll() : MutableList<SearchHistoryEntity>

    @Delete(entity = SearchHistoryEntity::class)
    fun delOne(entity: SearchHistoryEntity)

}