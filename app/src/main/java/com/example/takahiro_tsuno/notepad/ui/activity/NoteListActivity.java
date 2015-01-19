package com.example.takahiro_tsuno.notepad.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.takahiro_tsuno.notepad.R;
import com.example.takahiro_tsuno.notepad.domain.note.DefaultNoteRepository;
import com.example.takahiro_tsuno.notepad.domain.note.NoteRepository;
import com.example.takahiro_tsuno.notepad.ui.adapter.NoteAdapter;

public class NoteListActivity extends ActionBarActivity {

    public static final int REQUEST_CODE_CREATE = 0;
    public static final int REQUEST_CODE_EDIT = 1;
    @InjectView(R.id.note_list)
    ListView noteList;
    private NoteAdapter adapter;
    private NoteRepository noteRepository = new DefaultNoteRepository();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        adapter = new NoteAdapter(this);
        noteList.setAdapter(adapter);
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                NoteEditActivity.startActivity(NoteListActivity.this, REQUEST_CODE_EDIT, adapter.getItem(position));
            }
        });
        refresh();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch(item.getItemId()) {
            case R.id.note_create:
                startActivityForResult(new Intent(NoteListActivity.this, NoteCreateActivity.class), REQUEST_CODE_CREATE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        boolean activityResult = requestCode == REQUEST_CODE_CREATE || requestCode == REQUEST_CODE_EDIT;
        if(activityResult && resultCode == Activity.RESULT_OK) {
            refresh();
        }
    }

    private void refresh() {
        adapter.clear();
        adapter.addAll(noteRepository.findAll());
    }
}
