package com.stylingandroid.prism.sample.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ColourFragment extends Fragment {

    private static final String KEY_COLOUR = "KEY_COLOUR";
    private static final String KEY_TEXT = "KEY_TEXT";

    public static Fragment newInstance(Context context, CharSequence name, int colour) {
        Bundle args = new Bundle();
        args.putCharSequence(KEY_TEXT, name);
        args.putInt(KEY_COLOUR, colour);
        return Fragment.instantiate(context, ColourFragment.class.getName(), args);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CharSequence name = getArguments().getCharSequence(KEY_TEXT);
        int colour = getArguments().getInt(KEY_COLOUR);
        TextView view = (TextView) inflater.inflate(R.layout.fragment_colour, container, false);
        view.setTextColor(colour);
        view.setText(name);
        return view;
    }
}
