package com.bw.zz.retrofit;

import androidx.lifecycle.LiveData;

import com.bw.zz.protocol.BaseRespEntity;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

/**
 * @ClassName DefaultCallAdapter
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/17 20:33
 * @Version 1.0
 */
public class DefaultCallAdapter<R> implements CallAdapter<R, Object> {

    Type type;

    public DefaultCallAdapter(Type _t) {
        this.type = _t;
    }

    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public Object adapt(Call<R> call) {
        return call;
    }
}
