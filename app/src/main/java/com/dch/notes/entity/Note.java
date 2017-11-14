package com.dch.notes.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import static com.dch.notes.entity.Note.TABLE_NAME;

/**
 * 作者：MrCoder on 2017/11/14 0014 17:48
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
@Entity(tableName = TABLE_NAME)
public class Note {

    public static final String TABLE_NAME = "notes";

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "content")
    public String content;

    @ColumnInfo(name = "date")
    public String date;

    public Note(int id, String content, String date){
        this.id = id;
        this.content = content;
        this.date = date;
    }
}
