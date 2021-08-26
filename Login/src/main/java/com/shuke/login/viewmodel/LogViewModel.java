package com.shuke.login.viewmodel;

import android.os.Looper;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;


import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.login.pro.LogEntity;

import com.shuke.login.reposi.LogRepoImpl;
import com.shuke.mvvmcore.BaseViewModel;

/**
 * @CreateDate: 2021/8/24 9:56
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: com.shuke.login.viewmodel
 * @ClassName: RegViewModel
 */
public class LogViewModel extends BaseViewModel<LogRepoImpl> {
    public MutableLiveData<LogEntity> pageSource = new MutableLiveData<>();
    public LogViewModel(LifecycleOwner lifecycle) {
        super(lifecycle);
        LogEntity entity = new LogEntity();
        if (Looper.myLooper()!=Looper.getMainLooper()){
            pageSource.postValue(entity);
        }else{
            pageSource.setValue(entity);
        }
    }

    @Override
    public LogRepoImpl createRepository() {
        return new LogRepoImpl();
    }

    @Override
    public void releaseResource() {

    }

    @Override
    public void initResource() {

    }
    public LiveData<BaseRespEntity<LogEntity>> log(LogEntity entity){

        return repo.log(entity);
    }
}
