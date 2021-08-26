package com.shuke.login;


import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.bw.zz.RetrofitFactory;
import com.bw.zz.protocol.BaseRespEntity;

import com.shuke.login.api.RegisterApi;
import com.shuke.login.databinding.LogMain;
import com.shuke.login.databinding.RigMain;
import com.shuke.login.pro.LogEntity;
import com.shuke.login.pro.RegisterEntity;
import com.shuke.login.viewmodel.LogViewModel;

import com.shuke.login.viewmodel.RegViewModel;
import com.shuke.mvvmcore.view.MVVMActivity;

import java.util.Map;

public class RegisterActivity extends MVVMActivity<RigMain, RegViewModel> {

    @Override
    public  RegViewModel createViewModel() {
        return new RegViewModel(this);
    }

    @Override
    public Map<Integer, Object> initVarMap(Map<Integer, Object> vars) {
        vars.put(BR.datapage,viewModel);
        return vars;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_reg_main;
    }

    @Override
    public void initEvent() {

    }

    @Override
    public void loadData() {
        binding.setSecond(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.register(new RegisterEntity(0,"123963","0099","",""));
                RetrofitFactory.getMyRetrofit()
                        .createRetrofit()
                        .create(RegisterApi.class)
                        .log(new LogEntity(0,"123963","0099","",""))
                        .observe(RegisterActivity.this, new Observer<BaseRespEntity<LogEntity>>() {
                            @Override
                            public void onChanged(BaseRespEntity<LogEntity> logEntityBaseRespEntity) {
                                Toast.makeText(RegisterActivity.this, "跳转"+logEntityBaseRespEntity.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}