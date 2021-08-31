package com.bw.headline.addheadline.view

import androidx.recyclerview.widget.GridLayoutManager
import com.bw.headline.BR
import com.bw.headline.R
import com.bw.headline.addheadline.adapter.AddHeadLineAdapter
import com.bw.headline.addheadline.viewmodel.AddHeadLineViewModel
import com.bw.headline.databinding.AddheadlineLayoutBinding
import com.shuke.mvvmcore.BaseViewModel
import com.shuke.mvvmcore.view.MVVMActivity
import kotlinx.android.synthetic.main.addheadline_layout.*

/**
 *   @Author:YaPeng
 *   @Date:2021/8/30
 *   @Email:3536815334@qq.com
 */
class AddHeadLineAct : MVVMActivity<AddheadlineLayoutBinding,AddHeadLineViewModel>(){

    override fun initVarMap(vars: MutableMap<Int, Any>): MutableMap<Int, Any> {
        vars.put(BR.viewmodel,viewModel)
        vars.put(BR.mine,this)
        return vars
    }

    override fun createViewModel(): AddHeadLineViewModel {
        return AddHeadLineViewModel()
    }

    override fun getLayoutId(): Int {
        return R.layout.addheadline_layout
    }

    override fun initEvent() {
        var list:MutableList<String> = mutableListOf()
        list.add("http://shukespicture.oss-cn-beijing.aliyuncs.com/20210831165515")
        list.add("http://shukespicture.oss-cn-beijing.aliyuncs.com/20210831165515")
        list.add("http://shukespicture.oss-cn-beijing.aliyuncs.com/20210831165515")
        list.add("http://shukespicture.oss-cn-beijing.aliyuncs.com/20210831165515")
        list.add("http://shukespicture.oss-cn-beijing.aliyuncs.com/20210831165515")
        list.add("http://shukespicture.oss-cn-beijing.aliyuncs.com/20210831165515")
        list.add("http://shukespicture.oss-cn-beijing.aliyuncs.com/20210831165515")
        list.add("http://shukespicture.oss-cn-beijing.aliyuncs.com/20210831165515")
        list.add("http://shukespicture.oss-cn-beijing.aliyuncs.com/20210831165515")
        addheadline_activity_recycler.layoutManager = GridLayoutManager(this,3)
        addheadline_activity_recycler.adapter = AddHeadLineAdapter(list)
    }

    override fun loadData() {

    }
}