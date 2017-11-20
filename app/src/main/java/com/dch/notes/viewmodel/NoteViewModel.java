package com.dch.notes.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.test.mock.MockApplication;

import com.dch.notes.BaseApplication;
import com.dch.notes.DataRepository;
import com.dch.notes.model.Note;

/**
 * 作者：MrCoder on 2017/11/17 0017 17:48
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
public class NoteViewModel extends AndroidViewModel {
    private final LiveData<Note> mObservableNote;

    public ObservableField<Note> note = new ObservableField<>();

    private final int mNoteId;
    public NoteViewModel(@NonNull Application application, DataRepository dataRepository, final int noteId) {
        super(application);
        mNoteId = noteId;
        mObservableNote = dataRepository.loadNote(mNoteId);
    }

    public LiveData<Note> getObservableNote() {
        return mObservableNote;
    }

    public void setNote(Note note){
        this.note.set(note);
    }

    static class Factory extends ViewModelProvider.NewInstanceFactory{

        @NonNull
        private final Application mApplication;

        private final int mNoteId;

        private final DataRepository mRepository;
        public Factory(@NonNull Application application, int noteId) {
            this.mApplication = application;
            this.mNoteId = noteId;
            mRepository = ((BaseApplication) application).getRepository();
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new NoteViewModel(mApplication,mRepository,mNoteId);
        }
    }
}
