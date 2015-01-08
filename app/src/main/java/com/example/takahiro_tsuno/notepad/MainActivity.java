package com.example.takahiro_tsuno.notepad;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.note_list) ListView noteList;
    @InjectView(R.id.save_button) Button saveButton;

    private NoteAdapter noteAdapter;
    private NoteModel noteModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        // note一覧を取得
        noteAdapter = new NoteAdapter(MainActivity.this);
        noteList.setAdapter(noteAdapter);

        refreshNoteList();

        // 追加ボタン押下時の処理
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date nowDate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH':'mm':'ss");
                String dateString = sdf.format(nowDate);

                noteModel.set(dateString);
                refreshNoteList();
            }
        });

        // リスト押下時の処理
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 押されたnoteオブジェクトを取得します
                Note note = noteAdapter.getItem(position);

                noteModel.delete(note);
                refreshNoteList();
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

    public void refreshNoteList(){
        noteAdapter.clear();

        noteModel = new NoteModel(MainActivity.this);
        List<Note> noteList = noteModel.getList();

        noteAdapter.addAll(noteList);
    }
}
