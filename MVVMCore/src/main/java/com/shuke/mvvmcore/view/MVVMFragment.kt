package com.shuke.mvvmcore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.shuke.mvvmcore.BaseViewModel

/**
 *   @Author:YaPeng
 *   @Date:2021/8/19
 *   @Email:3536815334@qq.com
 */
abstract class MVVMFragment<V : ViewDataBinding,VM : BaseViewModel<*>> : Fragment(){
    protected lateinit var viewModel : VM
    protected lateinit var binding : V
    protected var vars : MutableMap<Int,Any> = mutableMapOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = createViewModel()
        vars = initVarMap(vars)
        setVars(binding,vars)

        loadData()
        initEvent()


    }


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
     * 创建并初始化ViewModel
     */
    abstract fun createViewModel(): VM

    /**
     * 初始化事件
     */
    abstract fun initEvent()

    /**
     * 加载数据
     */
    abstract fun loadData()

    /**
     * 获取布局Id
     */
    abstract fun getLayoutId(): Int

    /**
     * 通过Id获取控件
     */
    fun <T : View?> findView(id:Int):T{
        return binding.root.findViewById<T>(id)
    }
}