package com.example.takahiro_tsuno.notepad;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NoteAdapter extends ArrayAdapter<Note> {

    private final Context context;

    public NoteAdapter(Context context) {
        super(context, 0);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        Note note = getItem(position);
        if (convertView == null) {
            // このへんやっぱり理解してない感あるので後で調べてね
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.adapter_note, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        // メモ情報を挿入
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.title) TextView title;
        @InjectView(R.id.description) TextView description;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
