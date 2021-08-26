package com.shuke.homepage.adapter;

import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.shuke.homepage.R;
import com.shuke.homepage.entity.NewsEntity;

import java.util.List;

/**
 * @ClassName IndexNewsAdapter
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/20 19:52
 * @Version 1.0
 */
public class IndexNewsAdapter extends BaseMultiItemQuickAdapter<NewsEntity.DataBean, BaseViewHolder> {
    public IndexNewsAdapter(List<NewsEntity.DataBean> data) {
        super(data);
        addItemType(NewsEntity.DataBean.ONE, R.layout.news_item_first);
        addItemType(NewsEntity.DataBean.TWO, R.layout.news_item_second);
        addItemType(NewsEntity.DataBean.THREE, R.layout.news_item_third);
        addItemType(NewsEntity.DataBean.FOUR, R.layout.news_item_fourth);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsEntity.DataBean dataBean) {

        switch (dataBean.getItemType()){
            case NewsEntity.DataBean.ONE:
                baseViewHolder.setText(R.id.item_one_tv_title,dataBean.getTitle());
                baseViewHolder.setText(R.id.item_one_tv_sourcesitename,dataBean.getSourcesitename());
                break;
            case NewsEntity.DataBean.TWO:
                baseViewHolder.setText(R.id.item_two_tv_title,dataBean.getTitle());
                baseViewHolder.setText(R.id.item_two_tv_sourcesitename,dataBean.getSourcesitename());
                Glide.with(getContext()).load(dataBean.getMainimgurl()+".jpg").into((ImageView) baseViewHolder.getView(R.id.item_two_iv_mainimgurl));
                break;
            case NewsEntity.DataBean.THREE:
                baseViewHolder.setText(R.id.item_three_tv_title,dataBean.getTitle());
                baseViewHolder.setText(R.id.item_three_tv_sourcesitename,dataBean.getSourcesitename());
                Glide.with(getContext()).load(dataBean.getMainimgurl()+".jpg").into((ImageView) baseViewHolder.getView(R.id.item_three_iv_mainimgurl));
                break;
            case NewsEntity.DataBean.FOUR:
                baseViewHolder.setText(R.id.item_four_tv_title,dataBean.getTitle());
                baseViewHolder.setText(R.id.item_four_tv_sourcesitename,dataBean.getSourcesitename());
                Glide.with(getContext()).load(dataBean.getMainimgurl()+".jpg").into((ImageView) baseViewHolder.getView(R.id.item_four_iv_mainimgurl_one));
                Glide.with(getContext()).load(dataBean.getMainimgurl()+".jpg").into((ImageView) baseViewHolder.getView(R.id.item_four_iv_mainimgurl_two));
                Glide.with(getContext()).load(dataBean.getMainimgurl()+".jpg").into((ImageView) baseViewHolder.getView(R.id.item_four_iv_mainimgurl_three));
                break;
        }
    }
}
