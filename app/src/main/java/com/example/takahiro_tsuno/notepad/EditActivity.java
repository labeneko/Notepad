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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class EditActivity extends ActionBarActivity {

    @InjectView(R.id.save_button) Button saveButton;
    @InjectView(R.id.delete_button) Button deleteButton;
    @InjectView(R.id.current_date) TextView currentDate;

    private static final String EXTRA_KEY_CONTENT = "extra_key_content";

    // このへんってactivityで新規に呼び出さないといけない？
    // んなことあるめー
    private NoteModel noteModel;

    private Note note;

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
        currentDate.setText(note.getCurrentDate());

        noteModel = new NoteModel(EditActivity.this);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date nowDate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH':'mm':'ss");
                String dateString = sdf.format(nowDate);

                noteModel.update(note.getId(), dateString);

                setResult(Activity.RESULT_OK); // これつけるといいのかな？
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 削除しますです
                noteModel.delete(note);

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
