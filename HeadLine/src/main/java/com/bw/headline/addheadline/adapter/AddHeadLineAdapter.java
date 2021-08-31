package com.bw.headline.addheadline.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.headline.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public
/**
 *   @Author:YaPeng
 *   @Date:2021/8/31
 *   @Email:3536815334@qq.com
 */
class AddHeadLineAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public AddHeadLineAdapter(@Nullable List<String> data) {
        super(R.layout.addheadline_item_layout, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
        Glide.with(getContext()).load(s).into((ImageView) baseViewHolder.findView(R.id.addheadline_item_image));
    }
}
