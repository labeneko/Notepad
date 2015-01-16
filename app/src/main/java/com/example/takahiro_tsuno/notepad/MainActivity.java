package com.example.takahiro_tsuno.notepad;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.note_list) ListView noteList;

    private NoteAdapter noteAdapter;
    private NoteModel noteModel;

    private final static int REQUEST_CODE_CREATE = 1;
    private final static int REQUEST_CODE_EDIT = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteModel = ((NoteApplication) getApplication()).getNoteModel();
        ButterKnife.inject(this);

        // note一覧を取得
        noteAdapter = new NoteAdapter(MainActivity.this);
        noteList.setAdapter(noteAdapter);

        // リストを取得
        refreshNoteList();

        // リスト押下時の処理
        //ここもButterKnifeで簡略化できるはずなんだけどやり方が分からない
        noteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 押されたnoteオブジェクトを取得します
                Note note = noteAdapter.getItem(position);

                // 編集画面に遷移します
                EditActivity.startActivity(MainActivity.this, REQUEST_CODE_EDIT, note);
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

        // 追加ボタン押下時の処理
        if (id == R.id.note_create) {
            CreateActivity.startActivity(MainActivity.this, REQUEST_CODE_CREATE);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        switch(requestCode){
            case REQUEST_CODE_CREATE:
                // 保存に成功していたらリストを更新
                if(resultCode == RESULT_OK){
                    refreshNoteList();
                }
                break;
            case REQUEST_CODE_EDIT:
                // 保存に成功していたらリストを更新
                if(resultCode == RESULT_OK){
                    refreshNoteList();
                }
                break;
        }

    }

    /**
     * noteListを取得
     */
    public void refreshNoteList(){
        noteAdapter.clear();
        List<Note> noteList = noteModel.getList();

        noteAdapter.addAll(noteList);
    }
}
