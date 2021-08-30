package com.bw.headline.common

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

/**
 *   @Author:YaPeng
 *   @Date:2021/8/29
 *   @Email:3536815334@qq.com
 */
abstract class BaseRecyclerViewAdapter<T>(var dataSource:MutableLiveData<MutableList<T>>,
var owner: LifecycleOwner) : RecyclerView.Adapter<BaseViewHolder>() {
    init{
        setDataChangedLisenter(dataSource)
    }

    /**
     * 数据变化监听
     */
    fun setDataChangedLisenter(dataSource: MutableLiveData<MutableList<T>>){
        dataSource.observe(owner,object : Observer<MutableList<T>>{
            override fun onChanged(t: MutableList<T>?) {
                notifyDataSetChanged()
            }
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflate = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),
            getLayoutId(),
            parent,
            false)
        return createHolder(inflate)
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.initVariables(setVariables(position))
        holder.bind(position)
    }



    override fun getItemCount(): Int {
        return dataSource.value!!.size
    }

    /**
     * 创建ViewHolder
     */
    abstract fun createHolder(inflate: ViewDataBinding?): BaseViewHolder

    /**
     * 获取布局ID
     */
    abstract fun getLayoutId(): Int

    abstract fun setVariables(position: Int): SparseArray<Any>

}