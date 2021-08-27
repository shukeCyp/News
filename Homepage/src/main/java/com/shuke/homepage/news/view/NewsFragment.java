package com.shuke.homepage.news.view;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.BR;
import com.shuke.homepage.R;
import com.shuke.homepage.adapter.IndexNewsAdapter;
import com.shuke.homepage.databinding.MyNewsFragment;
import com.shuke.homepage.entity.NewsEntity;
import com.shuke.homepage.news.viewmodel.NewsViewModel;
import com.shuke.mvvmcore.view.MVVMFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * @ClassName NewsFragment
 * @Description TODO
 * @Author ZZQ
 * @Date 2021/8/20 20:02
 * @Version 1.0
 *
 * 新闻数据页面
 */
public class NewsFragment extends MVVMFragment<MyNewsFragment, NewsViewModel>{

    RecyclerView rec;
    private List<NewsEntity.DataBean> list = new ArrayList<>();

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
        rec.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void initEvent() {

        //请求数据
        viewModel.getNews().observe(this, new Observer<BaseRespEntity<List<NewsEntity.DataBean>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<NewsEntity.DataBean>> data) {
                //多布局判断
                for(int i = 0; i < data.getData().size(); i++) {
                    if (i % 4 == 0) {
                        data.getData().get(i).setItemType(0);
                    } else if (i % 4 == 1) {
                        data.getData().get(i).setItemType(1);
                    } else if (i % 4 == 2) {
                        data.getData().get(i).setItemType(2);
                    } else {
                        data.getData().get(i).setItemType(3);
                    }
                    IndexNewsAdapter indexNewsAdapter = new IndexNewsAdapter(data.getData());
                    rec.setAdapter(indexNewsAdapter);
                }
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_news;
    }


}
