package com.shuke.homepage.details.viewmodel;

import androidx.lifecycle.LifecycleOwner;

import com.shuke.homepage.details.repository.DetailsRepo;
import com.shuke.mvvmcore.BaseViewModel;

import org.jetbrains.annotations.NotNull;

/**
 * @CreateDate: 2021/8/28 9:03
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: com.shuke.homepage.details.viewmodel
 * @ClassName: DetaViewModel
 */
public class DetaViewModel extends BaseViewModel<DetailsRepo> {
    @NotNull
    @Override
    public DetailsRepo createRepository() {
        return new DetailsRepo();
    }

    public DetaViewModel(@NotNull LifecycleOwner lifecycle) {
        super(lifecycle);

    }

    @Override
    public void releaseResource() {

    }

    @Override
    public void initResource() {

    }
}
