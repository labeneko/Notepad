package com.example.takahiro_tsuno.notepad.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.example.takahiro_tsuno.notepad.R;
import com.example.takahiro_tsuno.notepad.domain.note.Note;

public class NoteAdapter extends AbstractArrayAdapter<Note> {

    public NoteAdapter(final Context context) {
        super(context);
    }

    @Override
    protected int getlayoutResourceId() {
        return R.layout.adapter_note;
    }

    @Override
    protected void setView(final View view, final ViewGroup parent, final int position, final Note item) {
        ButterKnife.<TextView>findById(view, R.id.title).setText(item.getTitle());
        ButterKnife.<TextView>findById(view, R.id.description).setText(item.getDescription());
    }
}
