package com.dch.notes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.dch.notes.db.NoteDatabase;
import com.dch.notes.model.Note;

import java.util.List;

/**
 * 作者：MrCoder on 2017/11/17 0017 17:01
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
public class DataRepository {

    private static DataRepository sInstance;
    private final NoteDatabase mNoteDataBase;

    private MediatorLiveData<List<Note>> mObservableNotes;

    private DataRepository(final NoteDatabase noteDatabase) {
        this.mNoteDataBase = noteDatabase;
        mObservableNotes = new MediatorLiveData<>();

        mObservableNotes.addSource(mNoteDataBase.noteDao().getAllNotes(),
                notes -> {
//                    if (mNoteDataBase.getda)
                    mObservableNotes.postValue(notes);
                });
    }

    public static DataRepository getInstance(final NoteDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<Note>> getNotes() {
        return mObservableNotes;
    }

    public LiveData<Note> loadNote(final int noteId) {
        return mNoteDataBase.noteDao().loadNote(noteId);
    }

}
