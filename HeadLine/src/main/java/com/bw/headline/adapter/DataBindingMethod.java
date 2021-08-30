package com.bw.headline.adapter;


import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bw.headline.common.BaseRecyclerViewAdapter;
import com.bw.headline.common.BindingRecyclerView;

import org.w3c.dom.Text;

/**
 *   @Author:YaPeng
 *   @Date:2021/8/29
 *   @Email:3536815334@qq.com
 */
public class DataBindingMethod {

    @BindingAdapter(value = {"photoPic"})
    public static void setPhotoPic(ImageView view, String path){
        String[] split = path.split("\\|");
        Glide.with(view).load(split[0]).circleCrop().into(view);
    }

    @BindingAdapter(value = {"Pic1"})
    public static void setPic1(ImageView view, String path){
        String[] split = path.split("\\|");
        Glide.with(view).load(split[0]).into(view);
    }
    @BindingAdapter(value = {"Pic2"})
    public static void setPic2(ImageView view, String path){
        String[] split = path.split("\\|");
        Glide.with(view).load(split[1]).into(view);
    }
    @BindingAdapter(value = {"Pic3"})
    public static void setPic3(ImageView view, String path){
        String[] split = path.split("\\|");
        Glide.with(view).load(split[2]).into(view);
    }
    @BindingAdapter(value = {"Pic4"})
    public static void setPic4(ImageView view, String path){
        String[] split = path.split("\\|");
        Glide.with(view).load(split[3]).into(view);
    }
    @BindingAdapter(value = {"adapter"},requireAll = false)
    public static void setAdapter(BindingRecyclerView recycler, BaseRecyclerViewAdapter adapter){
        if (recycler.getLayoutManager() == null){
            recycler.setLayoutManager(new LinearLayoutManager(recycler.getContext()));
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler.getContext(), LinearLayoutManager.VERTICAL);
            recycler.addItemDecoration(dividerItemDecoration);
        }
        recycler.setAdapter(adapter);
    }
    @BindingAdapter(value = {"titleText"})
    public static void setTitle(TextView view, String title){
        view.setText(title);
    }
}
