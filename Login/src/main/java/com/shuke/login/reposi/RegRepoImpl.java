package com.shuke.login.reposi;

import androidx.lifecycle.LiveData;

import com.shuke.login.model.RegModelImpl;
import com.shuke.login.pro.RegisterEntity;
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
public class RegRepoImpl extends BaseRepository {
    @Model
    RegModelImpl regModel;
    public LiveData<RegisterEntity> register(RegisterEntity entity){
        return regModel.register(entity);
    }

}
