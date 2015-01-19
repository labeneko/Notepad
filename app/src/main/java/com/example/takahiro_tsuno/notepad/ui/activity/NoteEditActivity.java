package com.example.takahiro_tsuno.notepad.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.example.takahiro_tsuno.notepad.R;
import com.example.takahiro_tsuno.notepad.domain.note.Note;

public class NoteEditActivity extends NoteActivity {

    private static final String EXTRA_KEY_NOTE = "extra_key_note";
    @InjectView(R.id.switcher_description)
    ViewSwitcher switcherDescription;
    @InjectView(R.id.description)
    TextView description;
    private boolean editMode;
    private Note note;

    public static void startActivity(Activity activity, int requestCode, Note note) {
        Intent intent = new Intent(activity, NoteEditActivity.class);
        intent.putExtra(EXTRA_KEY_NOTE, note);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_edit;
    }

    @Override
    protected int getDescriptionEditResourceId() {
        return R.id.edit_description;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        note = (Note) getIntent().getSerializableExtra(EXTRA_KEY_NOTE);
        description.setText(note.getDescription());
        description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit();
            }
        });
        getTitleEdit().setText(note.getTitle());
        getDescriptionEdit().setText(note.getDescription());
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        ActionBar actionBar = getSupportActionBar();
        if(editMode) {
            showEditMode(actionBar, menu);
        } else {
            showNormalMode(actionBar, menu);
        }
        return true;
    }

    private void showEditMode(ActionBar actionBar, Menu menu) {
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        menu.findItem(R.id.note_edit).setVisible(false);
        menu.findItem(R.id.note_save).setVisible(true);
    }

    private void showNormalMode(ActionBar actionBar, Menu menu) {
        actionBar.setDisplayShowCustomEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);
        menu.findItem(R.id.note_edit).setVisible(true);
        menu.findItem(R.id.note_save).setVisible(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                return homeAsUp();
            case R.id.note_edit:
                return edit();
            case R.id.note_save:
                return update();
            case R.id.note_delete:
                return delete();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean edit() {
        editMode = true;
        switcherDescription.showNext();
        invalidateOptionsMenu();
        return true;
    }

    private boolean update() {
        if(updateNote(note)) {
            setResult(Activity.RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "更新に失敗しました", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    private boolean delete() {
        if(deleteNote(note)) {
            setResult(Activity.RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "削除に失敗しました", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
