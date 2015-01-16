package com.example.takahiro_tsuno.notepad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;


// editのコピペ…
public class CreateActivity extends ActionBarActivity {

    @InjectView(R.id.edit_description) EditText editDescription;

    ActionBar actionBar;
    View customActionBarView;

    private NoteModel noteModel;

    public static void startActivity(Activity activity, int requestCode){
        Intent intent = new Intent(activity, CreateActivity.class);

        // MainActivityのonActivityResultを呼ぶにはこうするしか無かったんや
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        ButterKnife.inject(this);

        noteModel = ((NoteApplication) getApplication()).getNoteModel();

        // actionbar
        actionBar = this.getSupportActionBar();
        // 戻るボタンを表示するかどうか
        actionBar.setDisplayHomeAsUpEnabled(true);

        // タイトルを表示するか
        actionBar.setDisplayShowTitleEnabled(false);

        // iconを表示するか
        actionBar.setDisplayShowHomeEnabled(false);

        // customActionBarの取得
        customActionBarView = this.getActionBarView();

        // 設定
        actionBar.setCustomView(customActionBarView);

        // CustomViewを表示するか
        actionBar.setDisplayShowCustomEnabled(true);
    }

    private View getActionBarView(){
        // 表示するlayoutファイルの取得
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.custom_action_bar, null);

        return view;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.note_save:
                saveNote();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveNote() {
        // タイトルを取得
        EditText editTitle = (EditText)customActionBarView.findViewById(R.id.action_bar_edit_title);
        String title = editTitle.getText().toString();

        String description = editDescription.getText().toString();
        boolean result = noteModel.add(title, description);

        if (result) {
            setResult(Activity.RESULT_OK);
            finish();
        } else {
            // 保存失敗時はエラーメッセージを出す
            Toast.makeText(this, "作成に失敗しました", Toast.LENGTH_LONG).show();
        }

    }
}
