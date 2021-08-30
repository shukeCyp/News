package com.shuke.homepage.details.model;

import androidx.lifecycle.LiveData;

import com.bw.zz.RetrofitFactory;
import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.api.Api;
import com.shuke.homepage.details.model.entity.DetailsEntity;
import com.shuke.homepage.entity.CommentEntity;
import com.shuke.mvvmcore.IModel;

import java.util.ArrayList;

/**
 * @CreateDate: 2021/8/28 8:43
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: com.shuke.homepage.details.model
 * @ClassName: DetailsModel
 */
public class DetailsModel implements IModel {
    public LiveData<BaseRespEntity<DetailsEntity>> detail(String newcode){
        return RetrofitFactory.getMyRetrofit()
                .createRetrofit()
                .create(Api.class)
                .getDeta(newcode);
    }

    /**
     *
     * @param newsCode
     * @param parentid
     * @param userid
     * @return
     */
    public LiveData<BaseRespEntity<ArrayList<CommentEntity>>> comment(String newsCode, Integer parentid, Integer userid){
        LiveData<BaseRespEntity<ArrayList<CommentEntity>>> liveData=RetrofitFactory.getMyRetrofit()
                .createRetrofit()
                .create(Api.class)
                .getCommtexs(newsCode,parentid,userid);
        return liveData;

    }
}
