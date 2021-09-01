package com.shuke.homepage.details.repository;

import androidx.lifecycle.LiveData;

import com.bw.zz.RetrofitFactory;
import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.api.Api;
import com.shuke.homepage.details.model.DetailsModel;
import com.shuke.homepage.details.model.entity.DetailsEntity;
import com.shuke.mvvmcore.BaseRepository;
import com.shuke.mvvmcore.annotation.Model;

/**
 * @CreateDate: 2021/8/28 9:01
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: com.shuke.homepage.details.repository
 * @ClassName: DetailsRepo
 */
public class DetailsRepo extends BaseRepository {
    @Model
    DetailsModel detailsModel;
    //新闻详情
    public LiveData<BaseRespEntity<DetailsEntity>> detail(String newcode){
        return detailsModel.detail(newcode);
    }

    //保存评论
    public LiveData<BaseRespEntity<String>> push(String content, String newsCode, String commitTime, int parentId, int userId) {
        return detailsModel.push(content, newsCode, commitTime, parentId, userId);
    }
}
