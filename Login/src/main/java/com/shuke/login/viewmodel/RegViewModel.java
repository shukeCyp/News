package com.shuke.login.viewmodel;

import android.os.Looper;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.login.pro.RegisterEntity;
import com.shuke.login.reposi.RegRepoImpl;
import com.shuke.mvvmcore.BaseViewModel;

/**
 * @CreateDate: 2021/8/24 9:56
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: com.shuke.login.viewmodel
 * @ClassName: RegViewModel
 */
public class RegViewModel extends BaseViewModel<RegRepoImpl> {
    public MutableLiveData<RegisterEntity> pageSource = new MutableLiveData<>();
    public RegViewModel(LifecycleOwner lifecycle) {
        super(lifecycle);
        RegisterEntity registerEntity = new RegisterEntity();
        if (Looper.myLooper()!=Looper.getMainLooper()){
            pageSource.postValue(registerEntity);
        }else{
            pageSource.setValue(registerEntity);
        }
    }

    @Override
    public RegRepoImpl createRepository() {
        return new RegRepoImpl();
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public void initResource() {

    }
    public LiveData<BaseRespEntity<RegisterEntity>> register(RegisterEntity entity){
        return repo.register(entity);
    }
}
