package com.dch.notes.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.dch.notes.dao.NoteDao;
import com.dch.notes.entity.Note;

/**
 * 作者：MrCoder on 2017/11/14 0014 17:57
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
