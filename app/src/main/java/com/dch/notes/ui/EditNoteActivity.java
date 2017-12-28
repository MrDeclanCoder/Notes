package com.dch.notes.ui;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.dch.notes.AppExecutors;
import com.dch.notes.BaseApplication;
import com.dch.notes.R;
import com.dch.notes.db.NoteDatabase;
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
    @BindView(R.id.edit_container)
    CoordinatorLayout mEditContainer;

    private String text;
    // 软键盘的显示状态
    private boolean showKeyboard;

    @TargetApi(16)
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);
        ButterKnife.bind(this);
        mEditContainer.getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Snackbar.make(toolbar, "已存储", Snackbar.LENGTH_SHORT).show();
                etAddNote.setText("");
            }
        };

        etAddNote.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager inputMethodManager = (InputMethodManager) EditNoteActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(mEditContainer.getWindowToken(), 0);
            }
        });

        fabSaveNote.setOnClickListener(view -> {
            text = etAddNote.getText().toString().trim();
            if (TextUtils.isEmpty(text)) {
                Snackbar.make(view, "请输入内容", Snackbar.LENGTH_SHORT).show();
                return;
            }
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(date);
            Note note = new Note(text, format);
            NoteDatabase dataBase = BaseApplication.getInstance().getDataBase();
            new AppExecutors().diskIO().execute(() -> {
                dataBase.runInTransaction(() -> {
//                    List<Note> value = dataBase.noteDao().getAllNotes().getValue();
//                    note.id = value.size();
                    dataBase.noteDao().addNote(note);
                    handler.sendEmptyMessage(0);
                });
            });
        });
    }


    private ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            Rect rect = new Rect();
            mEditContainer.getWindowVisibleDisplayFrame(rect);
            int minKeyboardHeight = 150;
            int statusBarHeight = 44;
            int screenHeight = mEditContainer.getRootView().getHeight();
            int height = screenHeight - (rect.bottom - rect.top);

            if (showKeyboard) {
                if (height - statusBarHeight < minKeyboardHeight) {
                    showKeyboard = false;
                    Toast.makeText(getApplicationContext(),"键盘隐藏了",Toast.LENGTH_SHORT).show();
                    ObjectAnimator translationY = ObjectAnimator.ofFloat(fabSaveNote, "translationY", -(fabSaveNote.getTop()/3*2),0);
                    translationY.setDuration(200);
                    translationY.start();
                }
            } else {
                if (height - statusBarHeight>minKeyboardHeight){
                    showKeyboard = true;
                    Toast.makeText(getApplicationContext(),"键盘显示了",Toast.LENGTH_SHORT).show();
                    ObjectAnimator translationY = ObjectAnimator.ofFloat(fabSaveNote, "translationY", 0, -(fabSaveNote.getTop()/3*2));
                    translationY.setDuration(200);
                    translationY.start();
                }
            }
        }
    };
}
