package com.example.takahiro_tsuno.notepad.ui.activity;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.takahiro_tsuno.notepad.R;

public class NoteCreateActivity extends NoteActivity {

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_create;
    }

    @Override
    protected int getDescriptionEditResourceId() {
        return R.id.edit_description;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                return homeAsUp();
            case R.id.note_save:
                return save();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean save() {
        if(saveNote()) {
            setResult(Activity.RESULT_OK);
            finish();
        } else {
            Toast.makeText(this, "作成に失敗しました", Toast.LENGTH_LONG).show();
        }
        return true;
    }
}
