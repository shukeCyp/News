package com.shuke.homepage.details.viewmodel;

import android.os.Looper;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.details.model.entity.DetailsEntity;
import com.shuke.homepage.details.repository.DetailsRepo;
import com.shuke.homepage.entity.CommentEntity;
import com.shuke.mvvmcore.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @CreateDate: 2021/8/28 9:03
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: com.shuke.homepage.details.viewmodel
 * @ClassName: DetaViewModel
 */
public class DetaViewModel extends BaseViewModel<DetailsRepo> {
    public MutableLiveData<DetailsEntity> liveData = new MutableLiveData<>();
    public MutableLiveData<CommentEntity> pageViewModel=new MutableLiveData<>();

    @NotNull
    @Override
    public DetailsRepo createRepository() {
        return new DetailsRepo();
    }

    public DetaViewModel(@NotNull LifecycleOwner lifecycle) {
        super(lifecycle);
        CommentEntity commentEntity = new CommentEntity();
        DetailsEntity detailsEntity = new DetailsEntity();
        if (Looper.myLooper()!=Looper.getMainLooper()){
            liveData.postValue(detailsEntity);
            pageViewModel.postValue(commentEntity);
        }else{
            liveData.setValue(detailsEntity);
            pageViewModel.setValue(commentEntity);
        }
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public void initResource() {

    }

    //新闻详情
    public LiveData<BaseRespEntity<DetailsEntity>> detail(String newcode){
       return repo.detail(newcode);
    }

    //获取评论
    public LiveData<BaseRespEntity<ArrayList<CommentEntity>>> comment(String newsCode, Integer parentid, Integer userid) {
        return repo.comment(newsCode, parentid, userid);
    }

    //保存评论
    public LiveData<BaseRespEntity<String>> push(String content, String newsCode, String commitTime, int parentId, int userId) {
        return repo.push(content, newsCode, commitTime, parentId, userId);

    }
}
