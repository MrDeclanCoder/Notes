package com.dch.notes.ui;

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
import com.dch.notes.databinding.NoteItemBinding;
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

    public void setNoteList(final List<? extends Note> notes) {
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
        NoteItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.note_item, parent, false);
        binding.setCallback(mClickCallback);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        holder.binding.setNote(mNoteList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mNoteList == null ? 0 : mNoteList.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        final NoteItemBinding binding;

        public NoteViewHolder(NoteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
