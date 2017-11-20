package com.dch.notes.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.dch.notes.BaseApplication;
import com.dch.notes.R;
import com.dch.notes.model.Note;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：MrCoder on 2017/11/14 0014 16:32
 * 描述：
 * 邮箱：daichuanhao@caijinquan.com
 */
public class EditNoteActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_edit)
    Toolbar toolbar;
    @BindView(R.id.et_add_note)
    TextInputEditText etAddNote;
    @BindView(R.id.fab_save_note)
    FloatingActionButton fabSaveNote;

    public static Handler handler;

    private String text;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        ButterKnife.bind(this);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                Snackbar.make(toolbar,"已存储", BaseTransientBottomBar.LENGTH_SHORT).show();
                etAddNote.setText("");
            }
        };
        fabSaveNote.setOnClickListener(view->{
            text = etAddNote.getText().toString().trim();
            if (TextUtils.isEmpty(text)){
                Snackbar.make(view,"请输入内容",Snackbar.LENGTH_SHORT).show();
                return;
            }
            new Thread(){
                @Override
                public void run() {
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String format = simpleDateFormat.format(date);
                    Note note = new Note(text, format);
                    BaseApplication.getInstance().getNote_db().noteDao().addNote(note);
                    handler.sendEmptyMessage(0);
                }
            }.start();
        });
    }
}
