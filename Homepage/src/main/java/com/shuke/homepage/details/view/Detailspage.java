package com.shuke.homepage.details.view;

import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.BR;
import com.shuke.homepage.R;
import com.shuke.homepage.databinding.DetaPage;
import com.shuke.homepage.details.model.entity.DetailsEntity;
import com.shuke.homepage.details.viewmodel.DetaViewModel;
import com.shuke.mvvmcore.BaseViewModel;
import com.shuke.mvvmcore.view.MVVMActivity;
import com.tencent.smtt.sdk.WebViewClient;

import org.jetbrains.annotations.NotNull;

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

    @Override
    public void initEvent() {
        actDetailWebView = findViewById(R.id.act_detail_WebView);
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
                actDetailWebView.loadUrl(url);
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

}
