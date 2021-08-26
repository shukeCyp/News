package com.bw.zz.retrofit;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bw.zz.protocol.BaseRespEntity;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ClassName LiveDataCallAdapter
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/17 20:32
 * @Version 1.0
 */
public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<BaseRespEntity<R>>> {

    Type type;

    public LiveDataCallAdapter(Type _t) {
        this.type = _t;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public LiveData<BaseRespEntity<R>> adapt(Call<R> call) {
        MutableLiveData<BaseRespEntity<R>> liveData = new MutableLiveData<>();
        //异步执行
        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    liveData.setValue((BaseRespEntity<R>) response.body());
                } else {
                    liveData.postValue((BaseRespEntity<R>) response.body());
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                //失败返回数据
                BaseRespEntity entity = new BaseRespEntity();
                entity.setCode(-11);
                entity.setMsg(t.getMessage());
                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    liveData.setValue(entity);
                } else {
                    liveData.postValue(entity);
                }
            }
        });

        return liveData;
    }
}
