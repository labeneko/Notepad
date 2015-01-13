package com.example.takahiro_tsuno.notepad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class CreateActivity extends ActionBarActivity {

    @InjectView(R.id.edit_title) EditText editTitle;
    @InjectView(R.id.edit_description) EditText editDescription;

    @InjectView(R.id.save_button) Button saveButton;

    // このへんってactivityで新規に呼び出さないといけない？
    // んなことあるめー
    private NoteModel noteModel;

    public static void startActivity(Context context){
        Intent intent = new Intent(context, CreateActivity.class);

        // MainActivityのonActivityResultを呼ぶにはこうするしか無かったんや
        ((Activity)context).startActivityForResult(intent, 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        ButterKnife.inject(this);

        noteModel = new NoteModel(CreateActivity.this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String description = editDescription.getText().toString();

                noteModel.add(title, description);

                setResult(Activity.RESULT_OK); // これつけるといいのかな？
                finish();
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
        if (id == R.id.note_create) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
