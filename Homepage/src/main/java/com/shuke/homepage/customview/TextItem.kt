package com.shuke.homepage.customview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.shuke.homepage.R
import com.shuke.homepage.search.db.SearchHistoryEntity

/**
 *   @Author:YaPeng
 *   @Date:2021/8/19
 *   @Email:3536815334@qq.com
 */
class TextItem : View {
    //背景画笔
    lateinit var bgPaint : Paint
    //画text的画笔
    lateinit var textPaint : Paint
    //画X的画笔
    lateinit var XPaint : Paint

    var text : String = "你没传值啊我的老baby"
    var IsHaveX : Boolean = true
    var color : Int = Color.parseColor("#F1F1F1")

    lateinit var item:SearchHistoryEntity

    /**
     * 设置默认宽高
     */
    var defaultHeight : Float = 0F
    var defaultWidth : Float = 0F

    /**
     * 删除标志
     */
    lateinit var delBitmap : Bitmap
    lateinit var createBitmap : Bitmap

    /**
     * 字体宽高
     */
    var textWidth : Float = 0F
    var textHeight : Float = 0F

    constructor(context: Context?) : super(context){init()}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        getAttrsValues(context,attrs)
        init()
    }
    fun setT(_text:String){
        this.text = _text
        measureWH()
        setMeasuredDimension(defaultWidth.toInt(),defaultHeight.toInt())
    }
    private fun getAttrsValues(context: Context?, attrs: AttributeSet?) {
        if (attrs == null){
            return
        }
        val Attris:TypedArray =
            context!!.obtainStyledAttributes(attrs, R.styleable.radios_textitem)
        text = Attris.getString(R.styleable.radios_textitem_text).toString()
        IsHaveX = Attris.getBoolean(R.styleable.radios_textitem_isHaveX, false)
        color = Attris.getColor(R.styleable.radios_textitem_bgColor, Color.WHITE)
        Attris.recycle()
    }
    fun measureWH(){
        textWidth = textPaint.measureText(text)
        val paint = Paint()
        val rect = Rect()
        paint.getTextBounds(text, 0, text.length, rect)
        textHeight = rect.height().toFloat()
        defaultWidth = textWidth + 70
        defaultHeight = 45F
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun init() {
        //初始化画笔
        bgPaint = Paint()
        bgPaint.style = Paint.Style.FILL
        bgPaint.setColor(color)

        textPaint = Paint()
        textPaint.textSize = 28F
        textPaint.setColor(Color.BLACK)

        measureWH()


        val drawable = resources.getDrawable(R.drawable.delete)

        delBitmap = BitmapFactory.decodeResource(resources, R.drawable.delete)

        //缩小Bitmap图
        var scaleWidth : Float = 45F/delBitmap.width
        var scaleHeight : Float = 45F/delBitmap.height
        var matrix:Matrix = Matrix()
        matrix.postScale(scaleWidth,scaleHeight)

        createBitmap =
            Bitmap.createBitmap(delBitmap, 0, 0, delBitmap.width, delBitmap.height, matrix, true)


        if (IsHaveX){
            XPaint = Paint()
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(defaultWidth.toInt(), defaultHeight.toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawRoundRect(RectF(0F,0F,defaultWidth,defaultHeight),22F,22F,bgPaint)
        if (IsHaveX){
            canvas!!.drawBitmap(createBitmap,defaultWidth - 45,0F,XPaint)
            canvas!!.drawText(text,(defaultWidth-textWidth - 45F) / 2,defaultHeight/ 2+textHeight/2+2,textPaint)
        }else{
            canvas!!.drawText(text,(defaultWidth-textWidth) / 2,defaultHeight/ 2+textHeight/2+2,textPaint)
        }
    }

    /**
     * 点击删除按钮的监听事件
     */
    private var childViewLisenter: ChildViewLisenter? = null

    fun setChildViewLisenter(_childViewLisenter: ChildViewLisenter){
        childViewLisenter = _childViewLisenter
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event!!.x in (defaultWidth - 45F)..defaultWidth){
            Log.i("TAG", "onTouchEvent: "+(childViewLisenter==null))
           if (childViewLisenter != null){
               childViewLisenter!!.onDel(this)
           }
        }
        return super.onTouchEvent(event)
    }

}