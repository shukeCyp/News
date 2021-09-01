package com.shuke.homepage.adapter;

import com.bw.zz.protocol.BaseRespEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shuke.homepage.R;
import com.shuke.homepage.entity.CommentEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @Name:yao
 * @CreateDate: 2021/8/30 14:03
 * @ProjectName: News
 * @Package: com.shuke.homepage.adapter
 * @ClassName: CommentAdapter
 */
public class CommentAdapter extends BaseQuickAdapter<CommentEntity, BaseViewHolder> {


    public CommentAdapter(List<CommentEntity> data) {
        super(R.layout.comment_activity,data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CommentEntity commentEntity) {
        baseViewHolder.setText(R.id.tv2,commentEntity.getCommenttime());
        baseViewHolder.setText(R.id.tv,commentEntity.getContent());
    }
}
