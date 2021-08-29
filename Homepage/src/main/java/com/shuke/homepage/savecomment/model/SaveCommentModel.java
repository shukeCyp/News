package com.shuke.homepage.savecomment.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bw.zz.RetrofitFactory;
import com.bw.zz.protocol.BaseRespEntity;
import com.google.gson.JsonObject;
import com.shuke.homepage.api.Api;
import com.shuke.mvvmcore.IModel;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @ClassName SaveCommentModel
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/29 18:44
 * @Version 1.0
 */
public class SaveCommentModel implements IModel {

    public LiveData<BaseRespEntity<String>> push(String content, String newsCode, String commitTime, int parentId, int userId) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",1);
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
