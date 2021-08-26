package com.shuke.homepage.guide.repository;

import com.shuke.homepage.entity.NewsTypeEntity;
import com.shuke.homepage.guide.model.NewsTypeModel;
import com.shuke.mvvmcore.BaseRepository;
import com.shuke.mvvmcore.annotation.Model;

import io.reactivex.Observer;

/**
 * @ClassName NewsTypeRepository
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/22 20:24
 * @Version 1.0
 */
public class NewsTypeRepository extends BaseRepository {

    @Model
    private NewsTypeModel mModel;

    public void getType(Observer<NewsTypeEntity> observer) {
        mModel.getType(observer);
    }

}
