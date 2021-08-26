package com.shuke.common;

import android.util.Log;

import java.util.Locale;

/**
 * @CreateDate: 2021/8/23 20:18
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: utis
 * @ClassName: LogUtil
 */
public class LogUtil {
    private static boolean LOGV = true;
    private static boolean LOGD = true;
    private static boolean LOGI = true;
    private static boolean LOGW = true;
    private static boolean LOGE = true;

    public static void v(String tag,String msg){
        if (LOGV){
            Log.v(tag, msg);
        }
    }

    public static void d(String tag,String msg){
        if (LOGD){
            Log.d(tag, msg);
        }
    }

    public static void i(String tag,String msg){
        if (LOGI){
            Log.i(tag, msg);
        }
    }

    public static void w(String tag,String msg){
        if (LOGW){
            Log.w(tag, msg);
        }
    }

    public static void e(String tag,String msg){
        if (LOGE){
            Log.e(tag, msg);
        }
    }

    /**
     * 获取到调用类的类名
     */
    private static String getTag(){
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String callingClass = "";
        for (int i = 0; i < trace.length; i++) {
            Class<? extends StackTraceElement> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)){
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.')+1);
                break;
            }
        }
        return callingClass;
    }
    /**
     * 不需要自定义TAG，直接打印信息
     */
    public static void notagv(String msg){
        if (LOGV){
            Log.v(getTag(), msg);
        }
    }

    public static void notagd(String msg){
        if (LOGD){
            Log.d(getTag(), msg);
        }
    }

    public static void notagi(String msg){
        if (LOGI){
            Log.i(getTag(), msg);
        }
    }

    public static void notagw(String msg){
        if (LOGW){
            Log.w(getTag(), msg);
        }
    }

    public static void notage(String msg){
        if (LOGE){
            Log.e(getTag(), msg);
        }
    }

    /**
     * 通过线程ID 打印方法名和信息
     */
    private static String buildMessage(String msg){
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String caller = "";
        for (int i = 0; i < trace.length; i++) {
            Class<? extends StackTraceElement> clazz = trace[i].getClass();
            if (!clazz.equals(LogUtil.class)){
                caller = trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US,"[%d] %s :%s",Thread.currentThread().getId(),caller,msg);
    }


    public static void idv(String msg){
        if (LOGV){
            Log.v(getTag(), buildMessage(msg));
        }
    }

    public static void idd(String msg){
        if (LOGD){
            Log.d(getTag(), buildMessage(msg));
        }
    }

    public static void idi(String msg){
        if (LOGI){
            Log.i(getTag(), buildMessage(msg));
        }
    }

    public static void idw(String msg){
        if (LOGW){
            Log.w(getTag(), buildMessage(msg));
        }
    }

    public static void ide(String msg){
        if (LOGE){
            Log.e(getTag(), buildMessage(msg));
        }
    }
}
