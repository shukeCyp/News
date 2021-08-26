package com.shuke.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.view.View;
import android.widget.Toast;

import com.bw.zz.RetrofitFactory;
import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.login.databinding.LogMain;
import com.shuke.login.api.RegisterApi;
import com.shuke.login.pro.LogEntity;
import com.shuke.login.viewmodel.LogViewModel;
import com.shuke.mvvmcore.view.MVVMActivity;

import java.util.Map;

public class LogMainActivity extends MVVMActivity<LogMain, LogViewModel> {

    @Override
    public  LogViewModel createViewModel() {
        return new LogViewModel(this);
    }

    @Override
    public Map<Integer, Object> initVarMap(Map<Integer, Object> vars) {
        vars.put(BR.datapage,viewModel);
        vars.put(BR.mine,this);
        return vars;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_log_main;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void loadData() {
//        binding.setSecond(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    public void doSomthing(View view){
        String username = viewModel.pageSource.getValue().getUsername();
        String pwd = viewModel.pageSource.getValue().getPwd();
        viewModel.log(new LogEntity(0, username, pwd, "", ""))
        .observe(this, new Observer<BaseRespEntity<LogEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<LogEntity> logEntityBaseRespEntity) {

            }
        });
    }
}