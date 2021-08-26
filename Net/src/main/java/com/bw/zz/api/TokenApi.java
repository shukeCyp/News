package com.bw.zz.api;

import com.bw.zz.protocol.TokenRespEntity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @ClassName TokenApi
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/17 19:39
 * @Version 1.0
 */
public interface TokenApi {

    @FormUrlEncoded
    @POST("token")
    Call<TokenRespEntity> getToken(@Field("grant_type") String grant_type, @Field("username") String username, @Field("password") String password);
}
