package com.bw.zz;

import android.text.TextUtils;
import android.util.Log;

import com.bw.zz.api.TokenApi;
import com.bw.zz.common.Config;
import com.bw.zz.protocol.TokenRespEntity;
import com.bw.zz.retrofit.CustomGsonConverterFactory;
import com.bw.zz.retrofit.LiveDataCallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName MyRetrofit
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/17 16:30
 * @Version 1.0
 */
public class RetrofitFactory {

    private volatile static RetrofitFactory myRetrofit = null;

    private Retrofit retrofit;

    public RetrofitFactory() {

        retrofit = createRetrofit();
    }

    /**
     * 双重锁成就单例
     * @return
     */




    public static synchronized RetrofitFactory getMyRetrofit() {
        if (myRetrofit == null) {
            synchronized (RetrofitFactory.class) {
                if (myRetrofit == null) {
                    myRetrofit = new RetrofitFactory();
                }
            }
        }
        return myRetrofit;
    }

    /**
     * 创建retrofit实例
     * @return
     */
    public Retrofit createRetrofit() {
         Retrofit retro = new Retrofit.Builder()
                .baseUrl("http://39.98.153.96:8080/")
                 .client(createHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .build();
        return retro;
    }

    /**
     * 创建OkHttp的client实例
     * @return
     */
    private OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(createTokenInterceptor())
                .addNetworkInterceptor(createNetInterceptor())
                .readTimeout(Config.connectTime, TimeUnit.SECONDS)
                .writeTimeout(Config.connectTime, TimeUnit.SECONDS)
                .connectTimeout(Config.connectTime, TimeUnit.SECONDS)
                .build();
    }

    //接收Token
    private String mToken = "";

    /**
     * 处理Token拦截器
     * @return
     */
    private Interceptor createTokenInterceptor() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //获取本地Token
                String localToken = mToken;

                if (!TextUtils.isEmpty(localToken)) {
                    return resetRequest(request,localToken,chain);
                }
                Response response = chain.proceed(request);
                //如果是401 同步请求Token然后加载到新请求的Header里，重新发起业务请求
                if (checkHttpCode401(response)) {
                    String token = requestToken();
                    if (TextUtils.isEmpty(token)) {
                        return response;
                    }
                    mToken =token ;
                    return resetRequest(request,token,chain);
                }
                return response;
            }
        };

        return interceptor;
    }

    /**
     * 重置请求
     */
    private Response resetRequest(Request request, String toKen, Interceptor.Chain chain) {
        Request.Builder newBuilder = request.newBuilder().addHeader("Authorization", "bearer "+toKen);
        Request build = newBuilder.build();
        try {
            return chain.proceed(build);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断HTTP CODE 是否是401  -- TOKEN失效
     * @param proceed
     * @return
     */
    private boolean checkHttpCode401(Response proceed) {
        if (proceed == null) {
            return false;
        }

        if (proceed.code() == 401) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 获取Token的同步网络请求
     * @return
     */
    private String requestToken() {
        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenRespEntity> tokenService = tokenApi.getToken("password", Config.AUTH_CODE, "");
        try {
            retrofit2.Response<TokenRespEntity> result = tokenService.execute();
            if (result != null && result.body() != null) {
                return result.body().getAccess_token();
            }
        } catch (IOException e) {
            Log.e("123", "error info:" + e.getMessage() );
        }

        return "";
    }

    /**
     * 设置网络拦截器
     * @return
     */
    private Interceptor createNetInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * 通过api接口获取到实例
     * @param tClass
     * @param <T>
     * @return
     */
    private <T> T create(Class<?> tClass) {
        return (T) retrofit.create(tClass);
    }
}
