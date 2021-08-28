package com.shuke.homepage.guide.repository;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.entity.NewsTypeEntity;
import com.shuke.homepage.guide.model.NewsTypeModel;
import com.shuke.mvvmcore.BaseRepository;
import com.shuke.mvvmcore.annotation.Model;

import java.util.ArrayList;

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

    public LiveData<BaseRespEntity<ArrayList<NewsTypeEntity.DataBean>>> getType( ) {
        return mModel.getType();
    }

}
