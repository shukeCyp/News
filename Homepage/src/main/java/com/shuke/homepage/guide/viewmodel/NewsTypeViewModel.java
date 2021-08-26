package com.shuke.homepage.guide.viewmodel;

import com.shuke.homepage.entity.NewsTypeEntity;
import com.shuke.homepage.guide.repository.NewsTypeRepository;
import com.shuke.mvvmcore.BaseViewModel;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Observer;

/**
 * @ClassName NewsTypeViewModel
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/22 20:29
 * @Version 1.0
 */
public class NewsTypeViewModel extends BaseViewModel<NewsTypeRepository> {
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

    public void getType(Observer<NewsTypeEntity> observer) {
        repo.getType(observer);
    }
}
