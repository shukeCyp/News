package com.bw.headline.view

import android.util.Log
import android.util.SparseArray
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.bw.headline.BR
import com.bw.headline.R
import com.bw.headline.common.BaseRecyclerViewAdapter
import com.bw.headline.common.BaseViewHolder
import com.bw.headline.databinding.HeadLineFm
import com.bw.headline.entity.HeadLineRespEntity
import com.bw.headline.viewmodel.HeadLineViewModel
import com.bw.zz.protocol.BaseRespEntity
import com.shuke.common.ThreadUtil
import com.shuke.mvvmcore.view.MVVMFragment

/**
 *   @Author:YaPeng
 *   @Date:2021/8/27
 *   @Email:3536815334@qq.com
 */
class HeadLineFragment : MVVMFragment<HeadLineFm,HeadLineViewModel>(){

    private var datas : MutableLiveData<MutableList<HeadLineRespEntity>> = MutableLiveData()
    var adapter :BaseRecyclerViewAdapter<HeadLineRespEntity>? = null

    override fun initVarMap(vars: MutableMap<Int, Any>): MutableMap<Int, Any> {
        vars.put(BR.headlineFrag,this)
        vars.put(BR.viewmodel,viewModel)
        return vars
    }

    override fun createViewModel(): HeadLineViewModel {
        return HeadLineViewModel(this)
    }

    override fun initEvent() {

    }

    override fun loadData() {
        datas.value = mutableListOf()
        val bean : MutableLiveData<BaseRespEntity<MutableList<HeadLineRespEntity>>> = viewModel.getHeadLineList() as MutableLiveData<BaseRespEntity<MutableList<HeadLineRespEntity>>>
        bean.observe(this,object : Observer<BaseRespEntity<MutableList<HeadLineRespEntity>>>{
            override fun onChanged(t: BaseRespEntity<MutableList<HeadLineRespEntity>>?) {
                if (t != null) {
                    datas.value = t.data
                }
            }
        })

        adapter = object : BaseRecyclerViewAdapter<HeadLineRespEntity>(datas,viewLifecycleOwner){
            override fun createHolder(inflate: ViewDataBinding?): BaseViewHolder {
                return BaseViewHolder(inflate!!)
            }

            override fun getLayoutId(): Int {
                return R.layout.headline_fragment_itemlayout
            }

            override fun setVariables(position: Int): SparseArray<Any> {
                var sparseArray : SparseArray<Any> = SparseArray()
                sparseArray.put(BR.item,datas!!.value!!.get(position))
                return sparseArray
            }

        }
    }

    override fun getLayoutId(): Int {
        return R.layout.headline_fragment_layout
    }



}