package com.shuke.homepage.news.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.entity.NewsEntity;
import com.shuke.homepage.news.repository.NewsRepository;
import com.shuke.mvvmcore.BaseRepository;
import com.shuke.mvvmcore.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Observer;

/**
 * @ClassName NewsViewModel
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/20 20:19
 * @Version 1.0
 */
public class NewsViewModel extends BaseViewModel<NewsRepository> {

    @Override
    public NewsRepository createRepository() {
        return new NewsRepository();
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public void initResource() {

    }

    public LiveData<BaseRespEntity<List<NewsEntity.DataBean>>> getNews() {
        return repo.getNews();
    }

}
