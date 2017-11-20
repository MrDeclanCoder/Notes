package com.dch.notes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.dch.notes.BaseApplication;
import com.dch.notes.model.Note;

import java.util.List;

/**
 * 作者：MrCoder on 2017/11/17 0017 16:48
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
public class NotesListViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<Note>> mObservableNotes;

    public NotesListViewModel(@NonNull Application application) {
        super(application);

        mObservableNotes = new MediatorLiveData<>();
        mObservableNotes.setValue(null);

        LiveData<List<Note>> notes = ((BaseApplication) application).getRepository().getNotes();
        mObservableNotes.addSource(notes, mObservableNotes::setValue);
    }

    public LiveData<List<Note>> getNotes(){
        return mObservableNotes;
    }
}
