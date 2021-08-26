package com.bw.zz.retrofit;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * @ClassName LiveDataCallAdapterFactory
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/17 19:59
 * @Version 1.0
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    public static LiveDataCallAdapterFactory create() {
        return new LiveDataCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        //获取api中返回值是否支持泛型
        if (!(returnType instanceof ParameterizedType)) {
            try {
                throw new IllegalAccessException("要求返回值必须是可参数化的（支持泛型）");
            } catch (IllegalAccessException e) {
                Log.e("123", "CustomCallAdapter error info:" + e.getMessage() );
            }
        }
        //获取api中返回值
        Class<?> rawType = CallAdapter.Factory.getRawType(returnType);
        if (rawType != Call.class && rawType != LiveData.class) {
            try {
                throw new IllegalAccessException("返回值类型必须是LiveData或者Call类型");
            } catch (IllegalAccessException e) {
                Log.e("123", "CustomCallAdapter error info:" + e.getMessage() );
            }
        }

        Type t = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) returnType);

        /**
         * 判断api返回值类型决定返回的数据
         */
        if (rawType == Call.class) {
            return new DefaultCallAdapter<>(t);
        } else if (rawType == LiveData.class) {
            return new LiveDataCallAdapter<>(t);
        }
        return new DefaultCallAdapter<>(t);
    }
}
