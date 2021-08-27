package com.bw.headline;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * @ClassName App
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/24 13:36
 * @Version 1.0
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
