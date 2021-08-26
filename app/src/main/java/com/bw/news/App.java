package com.bw.news;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @ClassName App
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/21 11:26
 * @Version 1.0
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // These two lines must be written before init, otherwise these configurations will be invalid in the init process
        ARouter.openLog();     // Print log
        ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        ARouter.init(this); // As early as possible, it is recommended to initialize in the Application
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //依赖超过65536
        MultiDex.install(this);
    }
}
