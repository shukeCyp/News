package com.shuke.homepage.details.view;


import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.common.ThreadUtil;
import com.shuke.homepage.BR;
import com.shuke.homepage.R;
import com.shuke.homepage.adapter.CommentAdapter;
import com.shuke.homepage.databinding.DetaPage;
import com.shuke.homepage.details.model.entity.DetailsEntity;
import com.shuke.homepage.details.viewmodel.DetaViewModel;
import com.shuke.homepage.entity.CommentEntity;
import com.shuke.mvvmcore.BaseViewModel;
import com.shuke.mvvmcore.view.MVVMActivity;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    private android.widget.EditText actDetailWrite;
    private ImageView actDetailCollect;
    private ViewStub loadPage;

    String code;
    boolean isChecked = false;

    @Override
    public void loadData() {
        actDetailWebView = findViewById(R.id.act_detail_WebView);
        rc = (RecyclerView) findViewById(R.id.rc);
        actDetailSay = (ImageView) findViewById(R.id.act_detail_say);
        actDetailWrite = (EditText) findViewById(R.id.act_detail_write);
        actDetailCollect = (ImageView) findViewById(R.id.act_detail_collect);
        loadPage = findViewById(R.id.loadPage);

        /**
         * 解决RecyclerView与ScrollView滑动冲突
         * 按照ScrollView滑动
         */
        rc.setNestedScrollingEnabled(false);

        //设置回车为搜索
        actDetailWrite.setImeOptions(EditorInfo.IME_ACTION_DONE);

        //监听WebView页面加载完成，去除底部空白
        actDetailWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onLoadResource(WebView webView, String s) {
                super.onLoadResource(webView, s);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);

                //布局里高度固定，这里设置高度为自适应，解决WebView下空白问题
                actDetailWebView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                        , ViewGroup.LayoutParams.WRAP_CONTENT));
            }
        });

        //显示加载动画
        loadPage.setVisibility(View.VISIBLE);
    }

    @Override
    public void initEvent() {


        //新闻详情
        code = getIntent().getStringExtra("code");
        viewModel.detail(code)
                .observe(this, new Observer<BaseRespEntity<DetailsEntity>>() {
                    @Override
                    public void onChanged(BaseRespEntity<DetailsEntity> detailsEntityBaseRespEntity) {
                        //关闭加载动画
                        loadPage.setVisibility(View.GONE);
                        viewModel.liveData.setValue(detailsEntityBaseRespEntity.getData());
                        String url = detailsEntityBaseRespEntity.getData().getUrl();
                        actDetailWebView.loadUrl(url);
                    }
                });

        //监听回车，回车之后请求网络
        actDetailWrite.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    String s = actDetailWrite.getText().toString();
                    //获取时间并执行请求
                    push(s);
                }
                return true;
            }
        });

        //点击收藏
        actDetailCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isChecked = !isChecked;
                if (isChecked) {
                    actDetailCollect.setImageResource(R.drawable.collect_true);
                } else {
                    actDetailCollect.setImageResource(R.drawable.collect);
                }
            }
        });

        //获取评论
        comment();
    }

    //获取时间并执行请求
    void push(String content) {

        //获取当前时间并执行请求
        ThreadUtil.Companion.doTaskAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://www.baidu.com");
                    URLConnection urlConnection = url.openConnection();
                    urlConnection.connect();
                    long webTime = urlConnection.getDate();
                    Date date = new Date(webTime);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                    String format = simpleDateFormat.format(date);
                    String time = format.substring(5);

                    //切换到主线程请求评论
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.push(content, code, time,-1, 11)
                                    .observe(Detailspage.this, new Observer<BaseRespEntity<String>>() {
                                        @Override
                                        public void onChanged(BaseRespEntity<String> stringBaseRespEntity) {
                                            String data = stringBaseRespEntity.getData();
                                            Toast.makeText(Detailspage.this, "" +data, Toast.LENGTH_SHORT).show();
                                            if (data.equals("保存成功")) {
                                                actDetailWrite.setText("");
                                                comment();
                                                commentAdapter.notifyDataSetChanged();
                                            }
                                        }
                                    });
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //获取评论
    public void comment() {
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

}
