package com.example.takahiro_tsuno.notepad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class EditActivity extends ActionBarActivity {

    @InjectView(R.id.switcher_title) ViewSwitcher switcherTitle;
    @InjectView(R.id.switcher_description) ViewSwitcher switcherDescription;

    @InjectView(R.id.title) TextView title;
    @InjectView(R.id.edit_title) EditText editTitle;

    @InjectView(R.id.description) TextView description;
    @InjectView(R.id.edit_description) EditText editDescription;

    private static final String EXTRA_KEY_CONTENT = "extra_key_content";

    // このへんってactivityで新規に呼び出さないといけない？
    // んなことあるめー
    private NoteModel noteModel;

    private Note note;
    private boolean isEditMode = false;

    public static void startActivity(Context context, Note note){
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(EXTRA_KEY_CONTENT, note);

        // MainActivityのonActivityResultを呼ぶにはこうするしか無かったんや
        ((Activity)context).startActivityForResult(intent, 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ButterKnife.inject(this);

        note = (Note)getIntent().getSerializableExtra(EXTRA_KEY_CONTENT);

        title.setText(note.getTitle());
        editTitle.setText(note.getTitle());

        description.setText(note.getDescription());
        editDescription.setText(note.getDescription());

        noteModel = new NoteModel(EditActivity.this);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(isEditMode){
            menu.findItem(R.id.note_edit).setVisible(false);
            menu.findItem(R.id.note_save).setVisible(true);
        }else{
            menu.findItem(R.id.note_edit).setVisible(true);
            menu.findItem(R.id.note_save).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.note_edit:
                editNote();
                break;

            case R.id.note_save:
                saveNote();
                break;

            case R.id.note_delete:
                deleteNote();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void editNote() {
        // 編集モードにする
        isEditMode = true;

        switcherTitle.showNext();
        switcherDescription.showNext();

        // メニューバーを編集モードに切り替える
        invalidateOptionsMenu();
    }

    public void saveNote() {
        String title = editTitle.getText().toString();
        String description = editDescription.getText().toString();
        noteModel.update(note.getId(), title, description);

        setResult(Activity.RESULT_OK); // これつけるといいのかな？
        finish();
    }

    public void deleteNote() {
        // 削除しますです
        noteModel.delete(note);

        setResult(Activity.RESULT_OK); // これつけるといいのかな？
        finish();
    }
}
