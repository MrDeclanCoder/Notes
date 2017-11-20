package com.dch.notes;

import android.arch.persistence.room.Room;
import android.support.multidex.MultiDexApplication;

import com.dch.notes.db.NoteDatabase;

/**
 * 作者：MrCoder on 2017/11/14 0014 18:24
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
public class BaseApplication extends MultiDexApplication {

    private static BaseApplication mInstance;
    private AppExecutors mAppExcutors;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        mAppExcutors = new AppExecutors();
    }

    public static BaseApplication getInstance(){
        return mInstance;
    }

    public NoteDatabase getDataBase() {
        return NoteDatabase.getInstance(this,mAppExcutors);
    }

    public DataRepository getRepository(){
        return DataRepository.getInstance(getDataBase());
    }
}
