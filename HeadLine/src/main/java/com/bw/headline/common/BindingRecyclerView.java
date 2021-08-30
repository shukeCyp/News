package com.bw.headline.common;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public
/**
 *   @Author:YaPeng
 *   @Date:2021/8/29
 *   @Email:3536815334@qq.com
 */
class BindingRecyclerView extends RecyclerView {
    public BindingRecyclerView(@NonNull @NotNull Context context, @Nullable @org.jetbrains.annotations.Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public <T> void setBindingAdapter(){

    }
}
