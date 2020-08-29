package com.example.watery.plan;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.watery.R;
import com.example.watery.utils.MyNumberPicked;


public class AimNumderPickDialog extends Dialog implements View.OnClickListener{
    private TextView confirm,cancel,title;
    private View context;
    private MyNumberPicked aim_number;
    private Context mContext;
    private OncloseListener listener;
    private int currentValue,cancelValue;
    private String str;

    /*private OncloseListener listener;*/

    public AimNumderPickDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public AimNumderPickDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }
    public AimNumderPickDialog(@NonNull Context context, int themeResId, OncloseListener listener, String str) {
        super(context, themeResId);
        this.mContext = context;
        this.listener = listener;
        this.str = str.trim();
    }



    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aim_number_picked);
        setCanceledOnTouchOutside(false);
        title = (TextView) findViewById(R.id.aim_title);
        confirm = (TextView) findViewById(R.id.aim_confirm);
        cancel = (TextView) findViewById(R.id.aim_cancel);
        context = findViewById(R.id.dialog_content);
        aim_number = (MyNumberPicked) findViewById(R.id.aim_number);
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
        initNumberPicked();

    }

    private void initNumberPicked() {
        aim_number.setMinValue(1500);
        aim_number.setMaxValue(2500);
        cancelValue = Integer.parseInt(str);
        aim_number.setValue(cancelValue);
        aim_number.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                currentValue = i1;
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.aim_confirm:
                if (listener != null) {
                    listener.onClick(true,currentValue);
                }
                this.dismiss();
                break;
            case R.id.aim_cancel:
                if (listener != null) {
                    listener.onClick(false,cancelValue);
                }
                this.dismiss();
                break;
        }
    }

    public interface OncloseListener {
        void onClick(boolean confirm, int a);
    }

}
