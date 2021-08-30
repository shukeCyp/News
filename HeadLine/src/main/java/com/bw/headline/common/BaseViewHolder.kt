package com.bw.headline.common

import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author:YaPeng
 * @Date:2021/8/29
 * @Email:3536815334@qq.com
 */
class BaseViewHolder(var binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
    //稀疏数组，代替Map
    private var variables = SparseArray<Any>()

    /**
     * 绑定子视图
     */
    fun bind(position:Int){
        initVariables(variables)
        setVariables(binding,variables)
        binding.executePendingBindings()
    }

    /**
     * 设置变量
     */
    fun setVariables(binding: ViewDataBinding, variables: SparseArray<Any>) {
        if (variables != null && variables.size() > 0){
            for (i in 0 .. variables.size()){
                val keyAt = variables.keyAt(i)
                val valueAt = variables.valueAt(i)
                binding.setVariable(keyAt,valueAt)
            }
        }
    }

    /**
     * 初始化变量
     */
    fun initVariables(_variables: SparseArray<Any>) {
        this.variables = _variables
    }

}