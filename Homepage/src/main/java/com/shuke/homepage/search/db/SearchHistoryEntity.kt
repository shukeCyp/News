package com.shuke.homepage.search.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *   @Author:YaPeng
 *   @Date:2021/8/22
 *   @Email:3536815334@qq.com
 */
@Entity(tableName = "Historys")
data class SearchHistoryEntity(
    @ColumnInfo(name = "name")
    var name: String?
    ){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
