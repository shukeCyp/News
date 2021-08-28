package com.shuke.homepage.details.view;

import com.shuke.homepage.R;
import com.shuke.homepage.details.viewmodel.DetaViewModel;
import com.shuke.mvvmcore.BaseViewModel;
import com.shuke.mvvmcore.view.MVVMActivity;

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
public class Detailspage extends MVVMActivity {
    @Override
    public void initEvent() {

    }

    @Override
    public void loadData() {

    }

    @NotNull
    @Override
    public Map<Integer, Object> initVarMap(@NotNull Map vars) {
        return null;
    }

    @NotNull
    @Override
    public BaseViewModel<?> createViewModel() {
        return new DetaViewModel(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.index_details;
    }
}
