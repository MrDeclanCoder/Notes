package com.dch.notes.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dch.notes.BaseApplication;
import com.dch.notes.R;
import com.dch.notes.db.NoteDatabase;
import com.dch.notes.model.Note;
import com.dch.notes.viewmodel.NotesListViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native");
    }

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rv_home)
    RecyclerView rvHome;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private NoteDatabase note_db;
    private NoteListAdapter noteListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(view ->
                Snackbar.make(view, "添加新的记录", Snackbar.LENGTH_LONG)
                        .setAction("添加", vieww ->
                                startActivity(new Intent(MainActivity.this, EditNoteActivity.class))
                        ).show()
        );

        initData();
    }

    private void initData() {
        NotesListViewModel notesListViewModel = ViewModelProviders.of(this).get(NotesListViewModel.class);
        subscribeUi(notesListViewModel);

        noteListAdapter = new NoteListAdapter(note -> {
            Snackbar.make(fab,note.id+": "+note.content,Snackbar.LENGTH_SHORT).show();
        });
        rvHome.setAdapter(noteListAdapter);
    }

    private void subscribeUi(NotesListViewModel viewModel) {
        viewModel.getNotes().observe(this,
                notes->{
                    if (notes != null){
                        noteListAdapter.notifyDataSetChanged();
                    }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
