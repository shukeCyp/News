package com.shuke.homepage;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;

import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

/**
 * @ClassName App
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/21 11:30
 * @Version 1.0
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 在调用TBS初始化、创建WebView之前进行如下配置
        HashMap map = new HashMap();
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(map);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //依赖超过65536
        MultiDex.install(this);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull @NotNull Thread thread, @NonNull @NotNull Throwable throwable) {

            }
        });
    }
}
