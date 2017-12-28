package com.dch.notes.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static com.dch.notes.model.Note.TABLE_NAME;

/**
 * 作者：MrCoder on 2017/11/14 0014 17:48
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
@Entity(tableName = TABLE_NAME)
public class Note {

    public static final String TABLE_NAME = "notes";
    public static final String DB_NAME = "note_db";

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "date")
    public String date;

    public Note(String content, String date){
        this.content = content;
        this.date = date;
    }
}
