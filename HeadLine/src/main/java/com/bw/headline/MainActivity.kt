package com.bw.headline

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bw.headline.view.HeadLineFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
//        window.requestFeature(Window.FEATURE_NO_TITLE) //取消状态栏的标题
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //判断SDK的版本是否>=21
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION) //允许页面可以拉伸到顶部状态栏并且定义顶部状态栏透名
//            window.getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or  //设置全屏显示
//                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            )
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            window.setStatusBarColor(Color.TRANSPARENT) //设置状态栏为透明
//            window.setNavigationBarColor(Color.TRANSPARENT) //设置虚拟键为透明
//        }
//        val actionBar: ActionBar? = supportActionBar
//        actionBar?.hide() //将actionBar隐藏

        if (Build.VERSION.SDK_INT >= 21){
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            window.statusBarColor = Color.parseColor("#BC2A2A")
        }

    }
}


