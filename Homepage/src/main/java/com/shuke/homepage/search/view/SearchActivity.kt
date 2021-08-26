package com.shuke.homepage.search.view

import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.shuke.common.ThreadUtil
import com.shuke.homepage.BR
import com.shuke.homepage.R
import com.shuke.homepage.customview.FluidViewUtils
import com.shuke.homepage.databinding.SearchActView
import com.shuke.homepage.search.db.SearchDBUtils
import com.shuke.homepage.search.db.SearchHistoryEntity
import com.shuke.homepage.search.viewmodel.SearchViewModel
import com.shuke.mvvmcore.view.MVVMActivity
import kotlinx.android.synthetic.main.search_activity_layout.*

/**
 *   @Author:YaPeng
 *   @Date:2021/8/20
 *   @Email:3536815334@qq.com
 */
@Route(path = "/homepage/searchActivity")
class SearchActivity : MVVMActivity<SearchActView,SearchViewModel>(){

    override fun initEvent() {

    }

    override fun loadData() {
        fragment_homepage_search_textview.setOnEditorActionListener(object : TextView.OnEditorActionListener{
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEND
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                        val text = fragment_homepage_search_textview.text.toString().trim()
                    if (!text.isEmpty()){
                        var searchHistory = SearchHistoryEntity(text)
                        FluidViewUtils.InsertView(fluidview,searchHistory,this@SearchActivity)
                    }
                   return true
                }
                return false
            }
        })

        ThreadUtil.doTaskAsync(object : Runnable{
            override fun run() {
                var allHistory = SearchDBUtils.getSearchDB(this@SearchActivity).searchHistoryDao().queryAll()
                allHistory.forEach {
                    Log.i("TAG", it.toString()+it.id)
                }
                FluidViewUtils.Adaptive(fluidview,allHistory,this@SearchActivity)
            }
        })

    }

    override fun initVarMap(vars: MutableMap<Int, Any>): MutableMap<Int, Any> {
        vars.put(BR.myAct,this)
        return vars
    }

    override fun createViewModel(): SearchViewModel {
        return SearchViewModel()
    }

    override fun getLayoutId(): Int {
        return R.layout.search_activity_layout
    }

    fun delAll(){
        fluidview.delall(this@SearchActivity)
    }
}