package com.bw.headline.addheadline.repository

import com.bw.headline.addheadline.model.AddHeadLineRemoteModel
import com.shuke.mvvmcore.BaseRepository
import com.shuke.mvvmcore.annotation.Model

/**
 *   @Author:YaPeng
 *   @Date:2021/8/30
 *   @Email:3536815334@qq.com
 */
class AddHeadLineRepository : BaseRepository(){

    @Model
    lateinit var remoteModel:AddHeadLineRemoteModel
}