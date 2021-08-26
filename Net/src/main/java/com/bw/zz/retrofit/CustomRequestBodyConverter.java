package com.bw.zz.retrofit;

import com.bw.zz.protocol.BaseRespEntity;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @ClassName CustomRequestBodyConverter
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/17 21:08
 * @Version 1.0
 */
public class CustomRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.get("application/json; charset=UTF-8");

    @Override
    public RequestBody convert(T value) throws IOException {
        //将请求的对象转为json格式
        String s = new Gson().toJson(value);
        return RequestBody.create(MEDIA_TYPE,s);
    }
}
