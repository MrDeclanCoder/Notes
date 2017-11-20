package com.dch.notes.ui;

import android.databinding.DataBindingComponent;
import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * 作者：MrCoder on 2017/11/20 0020 12:14
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
public class NoteItemBinding extends ViewDataBinding {
    /**
     * @param bindingComponent
     * @param root
     * @param localFieldCount
     * @hide
     */
    protected NoteItemBinding(android.databinding.DataBindingComponent bindingComponent, View root, int localFieldCount) {
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
}
