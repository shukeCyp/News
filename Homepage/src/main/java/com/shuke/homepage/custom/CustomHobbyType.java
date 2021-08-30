package com.shuke.homepage.custom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;

import com.shuke.homepage.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @ClassName CustomHobbyType
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/19 17:47
 * @Version 1.0
 */
public class CustomHobbyType extends ViewGroup {

    private int left=0;//子控件距离左边的距离
    private int top=0;//子控件距离顶部的距离
    private int curlen=0;//当前横排的长度,判断每个横行的一个长度,不允许超出父布局的长度
    //    private int maxhei=0;//当前最高的那个view高度
    private int rightmargin=15;//右边的外边距
    private int leftmargin = 20;
    private int topmargin = 20;

    private static final String TAG = "MyLinearView";

    public CustomHobbyType(Context context) {
        super(context);
        init();
    }

    public CustomHobbyType(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomHobbyType(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        left=0; //子控件外左边距
        top=0; //子控件外上边距
        curlen=0; //用于接收所有子控件宽度
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        /**
         * 整体思路是:
         * 第一个view正常布局.不管如何都是定边放置
         * 从第二个view开始. 要判断总长度.能放下 ,就放到一行.
         * 放不下的情况,要从头开始放置.
         * 不同的放置,就是调整left.和 top的位置.  让宽+left 和 高加top 即可.
         *
         */
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            //刚开始的时候第一个肯定不用测量，直接放进父布局里就可以
            if (i==0){
                //设置他的布局    距离左边距离，顶部距离，宽,高
                childAt.layout(left + 30,top + 10,childAt.getMeasuredWidth(),childAt.getMeasuredHeight());
                left+=childAt.getMeasuredWidth()+rightmargin;
//                top+=childAt.getMeasuredHeight();
                curlen+=childAt.getMeasuredWidth()+rightmargin;

                //当前子View的高度是否高于要最高的值
//                if (childAt.getMeasuredHeight()>maxhei){
//                    maxhei=childAt.getMeasuredHeight();
//                    top=childAt.getMeasuredHeight();
//                }
                //第一个不用考虑是否需要换行,所以直接跳出循环就行了
                continue;
            }
            //当前摆放的宽
            curlen+=childAt.getMeasuredWidth();
            //判断目前长度是否超出父类的长度
            if (curlen<getMeasuredWidth()){
//                Log.i(TAG, curlen+"--onLayout: "+left+"       wid"+getMeasuredWidth());
                childAt.layout(left + 10,top + 10,childAt.getMeasuredWidth()+left,childAt.getMeasuredHeight()+top);
                left+=childAt.getMeasuredWidth()+rightmargin;//左边+宽度
                //加上外边距，不在  curlen+=childAt.getMeasuredWidth(); 加,反而后加的原因是如果距离刚好，就不用管外边距
                curlen+=rightmargin;
//                curlen+=childAt.getMeasuredWidth();
//                curlen+=rightmargin;//当前的长度加上外边距
//                当前子View的高度是否高于要最高的值
//                if (childAt.getMeasuredHeight()>maxhei){
//                    maxhei=childAt.getMeasuredHeight();
//                }
            }else {//执行换行
//                Log.i(TAG, curlen+"--onLayout2: "+left+"       wid"+getMeasuredWidth());
                left=0;
                curlen=childAt.getMeasuredWidth()+rightmargin;
//                top=maxhei;//如果换行的话就给top添加
                top+=childAt.getMeasuredHeight();
                childAt.layout(left + 30,top + 10,childAt.getMeasuredWidth()+left,childAt.getMeasuredHeight()+top);
                left+=childAt.getMeasuredWidth()+rightmargin;//左边+宽度

            }
        }
    }

    private ArrayList<TextView> textViews=new ArrayList<>();
    private Map<TextView,Boolean> map=new HashMap<>();
    private boolean isclick = false;
    public static List<String> texts = new ArrayList<>();


    public void add(String msg){

        addItem(msg);
    }

    public void addItem(String msg){
        TextView textView=new TextView(getContext());
        textView.setText(msg);
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundResource(R.drawable.style_btn_false);
        LinearLayoutCompat.LayoutParams layoutParams = new LinearLayoutCompat.LayoutParams(250, 80);
        textView.setLayoutParams(layoutParams);
        textView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //如果在集合中存在
                if (map.containsKey(v)){
                    //取出集合里的key
                    Boolean aBoolean = map.get(v);
                    initText(v,aBoolean);
                }
                return true;
            }
        });
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView) v;
                isclick = !isclick;

                //判断选中背景样式
                if (isclick) {
                    texts.add(text.getText().toString());
                    textView.setBackgroundResource(R.drawable.style_btn_true);
                } else {
                    for (int i = 0; i < texts.size(); i++) {
                        if (text.getText().toString().equals(texts.get(i))) {
                            texts.remove(i);
                        }
                    }
                    textView.setBackgroundResource(R.drawable.style_btn_false);
                }
                Boolean aBoolean = map.get(v);
                //去除来值判断是可以删除
                if (aBoolean){
                    textViews.remove((TextView) v);
                    map.remove((TextView)v);
                    removeView(v);
                    invalidate();
                }else {
                }

            }
        });
        textViews.add(textView);
        map.put(textView,false);
        addView(textView);
        invalidate();
    }

    //被点击的时候        目的 :    点击其他地方,如果有被长按出现删除标记的时候  ,就把他给关掉
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //遍历迭代器
        Set<Map.Entry<TextView, Boolean>> entries = map.entrySet();
        Iterator<Map.Entry<TextView, Boolean>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<TextView, Boolean> next = iterator.next();
            //如果出现了长按的标记
//            Boolean value = iterator.next().getValue();
            Boolean value = next.getValue();
            if (value){
//                TextView key = iterator.next().getKey();
                TextView key = next.getKey();
                initText(key,value);
            }
        }
        return super.onTouchEvent(event);
    }

    private void initText(View v, Boolean aBoolean) {
        TextView textView= (TextView) v;
        if (aBoolean){
            //如果true,表示已经出现的删除的标记,也就是x号
            //否则,给他来个x号
            String s = textView.getText().toString();
            int x = s.lastIndexOf("x");
            CharSequence charSequence = s.subSequence(0, x);
            textView.setText(charSequence.toString());
            map.put((TextView) v,false);
        }else {
            //否则,给他来个x号
            textView.setText(textView.getText().toString()+"  x");
            map.put((TextView) v,true);
        }
    }

}
