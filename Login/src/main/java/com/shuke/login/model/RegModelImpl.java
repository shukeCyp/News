package com.shuke.login.model;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bw.zz.RetrofitFactory;
import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.login.api.RegisterApi;
import com.shuke.login.pro.RegisterEntity;
import com.shuke.mvvmcore.IModel;

/**
 * @CreateDate: 2021/8/24 9:33
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: com.shuke.login.model
 * @ClassName: RegModelImpl
 */
public class RegModelImpl implements IModel {
    public LiveData<BaseRespEntity<RegisterEntity>> register(RegisterEntity entity){
        return RetrofitFactory.getMyRetrofit()
                .createRetrofit()
                .create(RegisterApi.class)
                .regis(entity);
    }
}
