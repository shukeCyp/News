package com.shuke.homepage.details.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.BR;
import com.shuke.homepage.R;
import com.shuke.homepage.adapter.CommentAdapter;
import com.shuke.homepage.databinding.DetaPage;
import com.shuke.homepage.details.model.entity.DetailsEntity;
import com.shuke.homepage.details.viewmodel.DetaViewModel;
import com.shuke.homepage.entity.CommentEntity;
import com.shuke.mvvmcore.BaseViewModel;
import com.shuke.mvvmcore.view.MVVMActivity;
import com.tencent.smtt.sdk.WebViewClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

/**
 * @CreateDate: 2021/8/28 8:36
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: com.shuke.homepage.details.view
 * @ClassName: Detailspage
 */
public class Detailspage extends MVVMActivity<DetaPage,DetaViewModel> {
    private com.tencent.smtt.sdk.WebView actDetailWebView;
    private RecyclerView rc;
    int parentid=-1;
    int userid=-1;
    private android.widget.ImageView actDetailSay;
    CommentAdapter commentAdapter;
    @Override
    public void initEvent() {
        actDetailWebView = findViewById(R.id.act_detail_WebView);
        rc = (RecyclerView) findViewById(R.id.rc);
        actDetailSay = (ImageView) findViewById(R.id.act_detail_say);

    }

    @Override
    public void loadData() {
        String code = getIntent().getStringExtra("code");
        viewModel.detail(code)
        .observe(this, new Observer<BaseRespEntity<DetailsEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<DetailsEntity> detailsEntityBaseRespEntity) {
                viewModel.liveData.setValue(detailsEntityBaseRespEntity.getData());
                String url = detailsEntityBaseRespEntity.getData().getUrl();
                Toast.makeText(Detailspage.this, ""+url, Toast.LENGTH_SHORT).show();
                actDetailWebView.loadUrl(url);
            }
        });
        viewModel.comment(code,parentid,userid).observe(this, new Observer<BaseRespEntity<ArrayList<CommentEntity>>>() {
            @Override
            public void onChanged(BaseRespEntity<ArrayList<CommentEntity>> arrayListBaseRespEntity) {

                commentAdapter = new CommentAdapter(arrayListBaseRespEntity.getData());
                rc.setAdapter(commentAdapter);
                rc.setLayoutManager(new LinearLayoutManager(Detailspage.this));
            }
        });

    }

    @NotNull
    @Override
    public Map<Integer, Object> initVarMap(@NotNull Map vars) {
        vars.put(BR.datasou,viewModel);
        return vars;
    }


    @Override
    public int getLayoutId() {
        return R.layout.index_details;
    }

    @NotNull
    @Override
    public DetaViewModel createViewModel() {
        return new DetaViewModel(this);
    }

    private void initView() {

    }
}
