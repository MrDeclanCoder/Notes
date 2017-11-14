package com.dch.notes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：MrCoder on 2017/11/14 0014 16:32
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
public class EditNoteActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_add_note)
    TextInputEditText etAddNote;
    @BindView(R.id.fab_save_note)
    FloatingActionButton fabSaveNote;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        ButterKnife.bind(this);


    }
}
