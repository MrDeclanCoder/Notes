package com.dch.notes.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.dch.notes.model.Note;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * 作者：MrCoder on 2017/11/14 0014 17:53
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
@Dao
public interface NoteDao {

    @Query("SELECT * FROM "+Note.TABLE_NAME)
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM "+Note.TABLE_NAME + " WHERE id = :noteId")
    LiveData<Note> loadNote(final int noteId);

    @Insert
    void addNote(Note note);

    @Delete
    void deleteEvent(Note note);

    @Update(onConflict = REPLACE)
    void updateNote(Note note);

}
