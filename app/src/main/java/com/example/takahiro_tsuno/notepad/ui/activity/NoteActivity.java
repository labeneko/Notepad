package com.example.takahiro_tsuno.notepad.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.example.takahiro_tsuno.notepad.R;
import com.example.takahiro_tsuno.notepad.domain.note.*;

public abstract class NoteActivity extends ActionBarActivity {

    View customActionBarView;
    EditText titleEdit;
    EditText descriptionEdit;
    private NoteRepository noteRepository = new DefaultNoteRepository();

    protected abstract int getLayoutResourceId();

    protected abstract int getDescriptionEditResourceId();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        descriptionEdit = ButterKnife.findById(this, getDescriptionEditResourceId());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowHomeEnabled(false);
        customActionBarView = getLayoutInflater().inflate(R.layout.custom_action_bar, null);
        titleEdit = ButterKnife.findById(customActionBarView, R.id.action_bar_edit_title);
        actionBar.setCustomView(customActionBarView);
        actionBar.setDisplayShowCustomEnabled(true);
    }

    protected boolean homeAsUp() {
        finish();
        return true;
    }

    protected boolean saveNote() {
        String title = titleEdit.getText().toString();
        String description = descriptionEdit.getText().toString();
        Note note = new Note(NoteIdentity.EMPTY);
        note.setMetaInfo(new NoteMetaInfo(title, description));
        return noteRepository.add(note) != null;
    }

    protected boolean updateNote(Note note) {
        String title = titleEdit.getText().toString();
        String description = descriptionEdit.getText().toString();
        note.setMetaInfo(new NoteMetaInfo(title, description));
        return noteRepository.update(note) != null;
    }

    protected boolean deleteNote(Note note) {
        return noteRepository.remove(note);
    }

    protected View getCustomActionBarView() {
        return customActionBarView;
    }

    protected EditText getTitleEdit() {
        return titleEdit;
    }

    protected EditText getDescriptionEdit() {
        return descriptionEdit;
    }
}
