package com.shuke.mvvmcore.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.shuke.mvvmcore.BaseViewModel

/**
 *   @Author:YaPeng
 *   @Date:2021/8/17
 *   @Email:3536815334@qq.com
 */
abstract class MVVMActivity<V : ViewDataBinding,VM : BaseViewModel<*>> : AppCompatActivity(){
    protected lateinit var binding : V
    protected lateinit var viewModel : VM
    private var vars : MutableMap<Int,Any> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,getLayoutId())
        binding.lifecycleOwner = this
        viewModel = createViewModel()
        vars = initVarMap(vars)
        setVars(binding,vars)

        if(isClearBar()){
            clearBar()
        }
        loadData()
        initEvent()
    }

    /**
     * 沉浸式状态栏
     */

    fun clearBar(){
        if (Build.VERSION.SDK_INT >= 21){
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    /**
     * 是否实现沉浸式布局
     */
    fun isClearBar(): Boolean{
        return true
    }

    /**
     * 初始化数据
     */
    abstract fun initEvent()

    /**
     * 加载数据
     */
    abstract fun loadData()

    /**
     * 设置变量
     */
    fun setVars(binding: V, vars: MutableMap<Int, Any>){
        if (vars.size == 0){
            throw IllegalStateException("没有设置变量")
        }
        vars.forEach{
            binding.setVariable(it.key,it.value)
        }
    }

    /**
     * 设置变量Map集合
     */
    abstract fun initVarMap(vars: MutableMap<Int, Any>): MutableMap<Int, Any>

    /**
     * 创建ViewModel
     */
    abstract fun createViewModel(): VM

    /**
     * 获取布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * 根据Id获取控件
     */
    fun FindView(id:Int):View{
        return findViewById(id)
    }
}