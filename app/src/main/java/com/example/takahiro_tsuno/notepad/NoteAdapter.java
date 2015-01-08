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
    private List<Note> noteList;

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
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.adapter_note, parent, false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        // 現在時刻を挿入
        holder.currentDate.setText(note.getCurrentDate());

        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.current_date) TextView currentDate;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
