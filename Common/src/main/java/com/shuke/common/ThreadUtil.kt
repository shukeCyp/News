package com.shuke.common

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executors

/**
 *   @Author:YaPeng
 *   @Date:2021/8/23
 *   @Email:3536815334@qq.com
 *   线程切换工具类
 */
class ThreadUtil {

    companion object{
        private var handler:Handler = Handler(Looper.getMainLooper())
        /**
         * 是否是主线程
         */
        fun IsMainThread(): Boolean {
            return Looper.getMainLooper().thread == Thread.currentThread()
        }
        /**
         * 切换到子线程
         */
        fun doTaskAsync(runnable: Runnable){
            Executors.newCachedThreadPool().submit(runnable)
        }

        /**
         * 切换到主线程
         */
        fun doTaskOnMianThread(runnable: Runnable){
            handler.post(runnable)
        }
    }
}