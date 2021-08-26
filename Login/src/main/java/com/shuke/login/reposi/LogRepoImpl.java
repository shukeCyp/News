package com.shuke.login.reposi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.bw.zz.protocol.BaseRespEntity;
import com.shuke.login.model.LogModelImpl;
import com.shuke.login.pro.LogEntity;
import com.shuke.mvvmcore.BaseRepository;
import com.shuke.mvvmcore.annotation.Model;

/**
 * @CreateDate: 2021/8/24 9:41
 * @name:haonan
 * @Contact information:qq by 1612258670
 * @ProjectName: News
 * @Package: com.shuke.login.reposi
 * @ClassName: RegRepoImpl
 */
public class LogRepoImpl extends BaseRepository {
    @Model
    LogModelImpl logModel;
    public LiveData<BaseRespEntity<LogEntity>> log(LogEntity entity){
        return logModel.log(entity);
    }
}
