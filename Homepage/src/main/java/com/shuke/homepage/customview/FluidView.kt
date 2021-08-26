package com.shuke.homepage.customview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import com.shuke.common.ThreadUtil
import com.shuke.homepage.search.db.SearchDBUtils
import com.shuke.homepage.search.view.SearchActivity

/**
 *   @Author:YaPeng
 *   @Date:2021/8/19
 *   @Email:3536815334@qq.com
 *   自定义流式布局
 */
class FluidView : ViewGroup{

    var _left : Int = 0
    var _top : Int = 0

    var curlen : Int = 0

    var maxHeight : Int = 0

    var marRight : Int = 20
    var marBottom : Int = 20


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    /**
     * 添加子布局
     */
    fun addChildView(view:View){
        addView(view)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var count = childCount

        /**
         * 挨个测量
         */
        for (i in 0 .. (count - 1)){
            val childAt = getChildAt(i)
            measureChild(childAt,widthMeasureSpec,heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

        layoutAll()
    }

    private fun layoutAll() {

        _left = 0
        _top = 0
        curlen = 0
        maxHeight = 0

        var count : Int = childCount

        for (i in 0 .. (count - 1)){
            val childView : View = getChildAt(i)

            if(i == 0){
                childView.layout(_left,_top,childView.measuredWidth,childView.measuredHeight)
                _left = childView.measuredWidth
                curlen += (childView.measuredWidth + marRight)
                maxHeight = childView.measuredHeight
                continue
            }

            curlen += (childView.measuredWidth + marRight)

            if (curlen <= measuredWidth){
                if (childView.measuredHeight > maxHeight){
                    maxHeight = childView.measuredHeight
                }
                childView.layout(_left+ marRight,_top,childView.measuredWidth+_left+ marRight,childView.measuredHeight+_top)
                _left += (childView.measuredWidth + marRight)
            }
            else{
                _left = 0
                _top += marBottom
                curlen = childView.measuredWidth
                _top += maxHeight
                maxHeight = childView.measuredHeight
                childView.layout(_left,_top,childView.measuredWidth + _left,childView.measuredHeight + _top)
                _left +=childView.measuredWidth
            }
        }
    }



    fun setchildLisenter() {
        for (i in 1..childCount){
            val childAt : TextItem = this.getChildAt(i - 1) as TextItem
            childAt.setChildViewLisenter(object : ChildViewLisenter{
                override fun onDel(view: View) {
                    removeChildView(view)
                    ThreadUtil.doTaskAsync(object : Runnable{
                        override fun run() {
                            SearchDBUtils.getSearchDB(context).searchHistoryDao().delOne((view as TextItem).item)
                        }
                    })
                }
            })
        }
    }

    //删除子控件
    fun removeChildView(view:View){
        removeView(view)
        layoutAll()
    }

    fun delall(context: Context?) {
        removeAllViews()
        layoutAll()
    }

}