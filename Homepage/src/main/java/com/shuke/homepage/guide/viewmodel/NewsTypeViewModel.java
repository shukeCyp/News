package com.shuke.homepage.guide.viewmodel;

import android.os.Looper;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.common.ThreadUtil;
import com.shuke.homepage.databinding.NewsType;
import com.shuke.homepage.entity.NewsEntity;
import com.shuke.homepage.entity.NewsTypeEntity;
import com.shuke.homepage.guide.repository.NewsTypeRepository;
import com.shuke.mvvmcore.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.reactivex.Observer;

/**
 * @ClassName NewsTypeViewModel
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/22 20:29
 * @Version 1.0
 */
public class NewsTypeViewModel extends BaseViewModel<NewsTypeRepository> {
    public MutableLiveData<NewsTypeEntity> liveData = new MutableLiveData<>();
    public NewsTypeViewModel(LifecycleOwner lifecycle) {
        super(lifecycle);
        NewsTypeEntity newsTypeEntity = new NewsTypeEntity();
        if (ThreadUtil.Companion.IsMainThread()) {
            liveData.setValue(newsTypeEntity);
        } else {
            liveData.postValue(newsTypeEntity);
        }
    }

    @Override
    public NewsTypeRepository createRepository() {
        return new NewsTypeRepository();
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public void initResource() {

    }

    public LiveData<BaseRespEntity<ArrayList<NewsTypeEntity.DataBean>>> getType() {
        return repo.getType();
    }


}
