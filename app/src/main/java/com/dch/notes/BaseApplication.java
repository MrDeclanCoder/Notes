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

    private NoteDatabase note_db;
    private static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        note_db = Room.databaseBuilder(getApplicationContext(), NoteDatabase.class, "note_db").build();
    }

    public static BaseApplication getInstance(){
        return mInstance;
    }

    public NoteDatabase getNote_db() {
        return note_db;
    }
}
