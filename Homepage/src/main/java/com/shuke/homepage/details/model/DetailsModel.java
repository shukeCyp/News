package com.shuke.homepage.details.model;

import androidx.lifecycle.LiveData;

import com.bw.zz.RetrofitFactory;
import com.bw.zz.protocol.BaseRespEntity;
import com.google.gson.JsonObject;
import com.shuke.homepage.api.Api;
import com.shuke.homepage.details.model.entity.DetailsEntity;
import com.shuke.homepage.entity.CommentEntity;
import com.shuke.mvvmcore.IModel;

import java.util.ArrayList;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * @CreateDate: 2021/8/28 8:43
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: com.shuke.homepage.details.model
 * @ClassName: DetailsModel
 */
public class DetailsModel implements IModel {

    //新闻详情
    public LiveData<BaseRespEntity<DetailsEntity>> detail(String newcode){
        return RetrofitFactory.getMyRetrofit()
                .createRetrofit()
                .create(Api.class)
                .getDeta(newcode);
    }

    /**
     *获取评论
     * @param newsCode
     * @param parentid
     * @param userid
     * @return
     */
    public LiveData<BaseRespEntity<ArrayList<CommentEntity>>> comment(String newsCode, Integer parentid, Integer userid) {
        LiveData<BaseRespEntity<ArrayList<CommentEntity>>> liveData = RetrofitFactory.getMyRetrofit()
                .createRetrofit()
                .create(Api.class)
                .getCommtexs(newsCode, parentid, userid);
        return liveData;
    }

    //保存评论
    public LiveData<BaseRespEntity<String>> push(String content, String newsCode, String commitTime, int parentId, int userId) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", 1);
        jsonObject.addProperty("content", content);
        jsonObject.addProperty("newscode", newsCode);
        jsonObject.addProperty("commenttime", commitTime);
        jsonObject.addProperty("parentid", parentId);
        jsonObject.addProperty("userid", userId);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), String.valueOf(jsonObject));

        LiveData<BaseRespEntity<String>> push = RetrofitFactory.getMyRetrofit().createRetrofit()
                .create(Api.class)
                .push(requestBody);
        return push;
    }
}
