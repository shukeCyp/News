package com.shuke.homepage.details.repository;

import androidx.lifecycle.LiveData;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.details.model.DetailsModel;
import com.shuke.homepage.details.model.entity.DetailsEntity;
import com.shuke.homepage.entity.CommentEntity;
import com.shuke.mvvmcore.BaseRepository;
import com.shuke.mvvmcore.annotation.Model;

import java.util.ArrayList;

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
    public LiveData<BaseRespEntity<DetailsEntity>> detail(String newcode){
        return detailsModel.detail(newcode);
    }

    /**
     *
     */
    @Model
    DetailsModel commentModel;

    public LiveData<BaseRespEntity<ArrayList<CommentEntity>>>  comment(String newsCode, Integer parentid, Integer userid){
        return commentModel.comment(newsCode,parentid,userid);
    }
}
