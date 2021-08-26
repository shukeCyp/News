package com.bw.zz.retrofit;

import com.bw.zz.protocol.BaseRespEntity;
import com.bw.zz.protocol.TokenRespEntity;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @ClassName CustomResponseBodyConverter
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/17 21:07
 * @Version 1.0
 */
public class CustomResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    @Override
    public T convert(ResponseBody value) throws IOException {
        String s = value.toString();
        if (s.contains("access_")) {
            return (T) new Gson().fromJson(s, TokenRespEntity.class);
        }
        BaseRespEntity baseRespEntity = new Gson().fromJson(s, BaseRespEntity.class);

        return (T) baseRespEntity;
    }
}
