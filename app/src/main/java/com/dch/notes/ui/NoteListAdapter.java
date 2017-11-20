package com.dch.notes.ui;

import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dch.notes.BR;
import com.dch.notes.R;
import com.dch.notes.model.Note;

import java.util.List;

/**
 * 作者：MrCoder on 2017/11/17 0017 17:31
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private List<? extends Note> mNoteList;
    @Nullable
    private final NoteClickCallback mClickCallback;

    public NoteListAdapter(@Nullable NoteClickCallback clickCallback) {
        this.mClickCallback = clickCallback;
    }

    public void setmNoteList(final List<? extends Note> notes) {
        if (mNoteList == null) {
            this.mNoteList = notes;
            notifyItemRangeInserted(0, notes.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mNoteList.size();
                }

                @Override
                public int getNewListSize() {
                    return notes.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mNoteList.get(oldItemPosition).id == notes.get(newItemPosition).id;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Note newNote = notes.get(newItemPosition);
                    Note oldNote = mNoteList.get(oldItemPosition);
                    return newNote.id == oldNote.id && newNote.content == oldNote.content && newNote.date == oldNote.date;
                }
            });
            mNoteList = notes;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.note_item, parent, false);
//        binding.setCallback(mClickCallback);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
//        holder.binding.setNote(mNoteList.get(position));
        holder.binding.setVariable(BR.note,mNoteList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mNoteList == null ? 0 : mNoteList.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        public NoteViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class NoteViewBinding extends ViewDataBinding{

        private NoteClickCallback callback;
        private Note note;

        /**
         * @param bindingComponent
         * @param root
         * @param localFieldCount
         * @hide
         */
        protected NoteViewBinding(android.databinding.DataBindingComponent bindingComponent, View root, int localFieldCount) {
            super(bindingComponent, root, localFieldCount);
        }

        @Override
        protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
            return false;
        }

        @Override
        public boolean setVariable(int variableId, Object value) {
            return false;
        }

        @Override
        protected void executeBindings() {
        }

        @Override
        public void invalidateAll() {

        }

        @Override
        public boolean hasPendingBindings() {
            return false;
        }

        public void setCallback(NoteClickCallback callback) {
            this.callback = callback;
        }

        public void setNote(Note note) {
            this.note = note;

        }
    }
}
