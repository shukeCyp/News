package com.shuke.homepage.news.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bw.zz.protocol.BaseRespEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.shuke.homepage.BR;
import com.shuke.homepage.R;
import com.shuke.homepage.adapter.IndexNewsAdapter;
import com.shuke.homepage.databinding.MyNewsFragment;
import com.shuke.homepage.details.view.Detailspage;
import com.shuke.homepage.entity.NewsEntity;
import com.shuke.homepage.news.viewmodel.NewsViewModel;
import com.shuke.mvvmcore.view.MVVMFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName NewsFragment
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/20 20:02
 * @Version 1.0
 * <p>
 * 新闻数据页面
 */
public class NewsFragment extends MVVMFragment<MyNewsFragment, NewsViewModel> implements OnRefreshLoadMoreListener {

    RecyclerView rec;
    private SmartRefreshLayout smart;
    ViewStub OnRefreshed_ViewStub;

    IndexNewsAdapter indexNewsAdapter;

    Timer timer;
    int index = 0;

    int num = 0;
    boolean isRefresh = false;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==11){
                OnRefreshed_ViewStub.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public Map<Integer, Object> initVarMap(Map vars) {
        vars.put(BR.mine, this);
        return vars;
    }

    @Override
    public NewsViewModel createViewModel() {
        return new NewsViewModel();
    }

    @Override
    public void loadData() {
        rec = findView(R.id.news_rec);
        smart = findView(R.id.news_smart);
        OnRefreshed_ViewStub = findView(R.id.refresh_ViewStud);

        rec.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        smart.setOnRefreshLoadMoreListener(this);

    }

    @Override
    public void initEvent() {

        //请求数据
        viewModel.getNews(num).observe(this, new Observer<BaseRespEntity<List<NewsEntity.DataBean>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<NewsEntity.DataBean>> dat) {
                List<NewsEntity.DataBean> data = dat.getData();
                initRecy(data);
            }
        });
    }

    //多布局加入适配器
    private void initRecy(List<NewsEntity.DataBean> data) {

        smart.finishLoadMore(true);
        smart.finishRefresh(true);

        if (data != null) {
            //多布局判断
            for (int i = 0; i < data.size(); i++) {
                if (i % 4 == 0) {
                    data.get(i).setItemType(0);
                } else if (i % 4 == 1) {
                    data.get(i).setItemType(1);
                } else if (i % 4 == 2) {
                    data.get(i).setItemType(2);
                } else {
                    data.get(i).setItemType(3);
                }
            }
            if (indexNewsAdapter == null) {
                indexNewsAdapter = new IndexNewsAdapter(data);

                rec.setAdapter(indexNewsAdapter);
            } else {
                if (isRefresh) {
                    indexNewsAdapter.getData().clear();
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            index++;
                            if (index >= 3) {
                                timer.cancel();
                                index = 0;
                                handler.sendEmptyMessage(11);
                            }
                        }
                    },0,1000);
                }
                indexNewsAdapter.getData().addAll(data);
                indexNewsAdapter.notifyDataSetChanged();

            }

            //item点击
            indexNewsAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                    NewsEntity.DataBean o = (NewsEntity.DataBean) adapter.getData().get(position);
                    String newscode = o.getNewscode();
                    Intent intent = new Intent(getContext(), Detailspage.class);
                    intent.putExtra("code", newscode);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }

    //加载
    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        num++;
        isRefresh = false;
        initEvent();
    }

    //刷新
    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        num = 0;
        isRefresh = true;
        initEvent();
        OnRefreshed_ViewStub.setVisibility(View.VISIBLE);
    }
}
