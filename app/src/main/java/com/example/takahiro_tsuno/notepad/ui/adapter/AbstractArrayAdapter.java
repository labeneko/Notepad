package com.example.takahiro_tsuno.notepad.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public abstract class AbstractArrayAdapter<T> extends ArrayAdapter<T> {

    public AbstractArrayAdapter(final Context context) {
        super(context, 0);
    }

    protected abstract int getlayoutResourceId();

    protected abstract void setView(final View view, final ViewGroup parent, int position, T item);

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView != null ? convertView : LayoutInflater.from(getContext()).inflate(getlayoutResourceId(), parent, false);
        setView(view, parent, position, getItem(position));
        return view;
    }
}
