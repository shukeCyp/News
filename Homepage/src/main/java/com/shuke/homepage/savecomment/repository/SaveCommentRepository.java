package com.shuke.homepage.savecomment.repository;

import androidx.lifecycle.LiveData;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.savecomment.model.SaveCommentModel;
import com.shuke.mvvmcore.BaseRepository;
import com.shuke.mvvmcore.annotation.Model;

/**
 * @ClassName SaveCommentRepository
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/29 19:28
 * @Version 1.0
 */
public class SaveCommentRepository extends BaseRepository {

    @Model
    SaveCommentModel model;

    public LiveData<BaseRespEntity<String>> push(String content, String newsCode, String commitTime, int parentId, int userId) {
        return model.push(content, newsCode, commitTime, parentId, userId);
    }
}
