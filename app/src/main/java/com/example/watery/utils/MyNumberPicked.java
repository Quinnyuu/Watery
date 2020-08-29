package com.example.watery.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.watery.R;

public class MyNumberPicked extends NumberPicker {
    public MyNumberPicked(Context context) {
        super(context);
    }

    public MyNumberPicked(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNumberPicked(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        setNumberPicker(child);
    }

    public void setNumberPicker(View view) {
        if (view instanceof EditText) {
            ((EditText) view).setTextColor(ContextCompat.getColor(getContext(), R.color.darkgray));
            ((EditText) view).setTextSize(20);
        }
    }

}
