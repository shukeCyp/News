package com.shuke.login;


import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.login.databinding.RigMain;
import com.shuke.login.pro.RegisterEntity;
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
        vars.put(BR.mine,this);
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

    }

    public void doRegister(View view){
        String username = viewModel.pageSource.getValue().getUsername();
        String pwd = viewModel.pageSource.getValue().getPwd();
        Toast.makeText(this, ""+username+"  "+pwd, Toast.LENGTH_SHORT).show();
        viewModel.register(new RegisterEntity(0,username,pwd,"",""))
        .observe(this, new Observer<BaseRespEntity<RegisterEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<RegisterEntity> registerEntityBaseRespEntity) {
                startActivity(new Intent(RegisterActivity.this,LogMainActivity.class));
            }
        });
    }
}