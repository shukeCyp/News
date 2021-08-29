package com.shuke.homepage.savecomment.viewmodel;

import androidx.lifecycle.LiveData;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.savecomment.repository.SaveCommentRepository;
import com.shuke.mvvmcore.BaseViewModel;

/**
 * @ClassName SaveCommentViewModel
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/29 19:32
 * @Version 1.0
 */
public class SaveCommentViewModel extends BaseViewModel<SaveCommentRepository> {

    @Override
    public SaveCommentRepository createRepository() {
        return new SaveCommentRepository();
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public void initResource() {

    }

    public LiveData<BaseRespEntity<String>> push(String content, String newsCode, String commitTime, int parentId, int userId) {
        return repo.push(content, newsCode, commitTime, parentId, userId);
    }
}
