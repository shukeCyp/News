package com.shuke.homepage.customview

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.shuke.common.ThreadUtil
import com.shuke.homepage.search.db.SearchDBUtils
import com.shuke.homepage.search.db.SearchHistoryEntity
import kotlinx.android.synthetic.main.search_activity_layout.*
import java.lang.ref.WeakReference

/**
 *   @Author:YaPeng
 *   @Date:2021/8/20
 *   @Email:3536815334@qq.com
 */
object FluidViewUtils {

    fun Adaptive(fluidView: FluidView, entitys: MutableList<SearchHistoryEntity>, context: Context){
        val weakContext : WeakReference<Context> = WeakReference(context)
        entitys.forEach {
            var itemtext:TextItem = TextItem(weakContext.get())
            itemtext.setT(it.name.toString())
            itemtext.item = it
            fluidView.addChildView(itemtext)
        }
        fluidView.setchildLisenter()
    }

    fun InsertView(fluidView: FluidView, entity: SearchHistoryEntity, context: Context){
        val weakContext : WeakReference<Context> = WeakReference(context)
        var itemtext:TextItem = TextItem(weakContext.get())
        itemtext.setT(entity.name.toString())
        itemtext.item = entity
        fluidView.addChildView(itemtext)
        itemtext.setChildViewLisenter(object : ChildViewLisenter{
            override fun onDel(view: View) {
                fluidView.removeChildView(view)
                del(view,context)
            }
        })
        ThreadUtil.doTaskAsync(object : Runnable{
            override fun run() {
                weakContext.get()?.let { SearchDBUtils.getSearchDB(it).searchHistoryDao().insertEntity(entity) }
            }
        })
    }


    fun del(view:View,context: Context){
        val weakContext : WeakReference<Context> = WeakReference(context)
        ThreadUtil.doTaskAsync(object : Runnable{
            override fun run() {
                weakContext.get()?.let { SearchDBUtils.getSearchDB(it).searchHistoryDao().delOne((view as TextItem).item) }
            }
        })
    }


}