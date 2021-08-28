package com.shuke.homepage.details.viewmodel;

import android.os.Looper;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.homepage.details.model.entity.DetailsEntity;
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
    public MutableLiveData<DetailsEntity> liveData = new MutableLiveData<>();
    @NotNull
    @Override
    public DetailsRepo createRepository() {
        return new DetailsRepo();
    }

    public DetaViewModel(@NotNull LifecycleOwner lifecycle) {
        super(lifecycle);
        DetailsEntity detailsEntity = new DetailsEntity();
        if (Looper.myLooper()!=Looper.getMainLooper()){
            liveData.postValue(detailsEntity);
        }else{
            liveData.setValue(detailsEntity);
        }
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public void initResource() {

    }
    public LiveData<BaseRespEntity<DetailsEntity>> detail(String newcode){
       return repo.detail(newcode);
    }
}
