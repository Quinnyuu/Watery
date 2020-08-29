package com.example.watery.user;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import com.example.watery.R;
import com.example.watery.utils.MyNumberPicked;


public class WeightNumderPickDialog extends Dialog implements View.OnClickListener{
    private TextView confirm,cancel,title;
    private View context;
    private MyNumberPicked weight_number;
    private Context mContext;
    private OncloseListener listener;
    private int currentValue,cancelValue;
    private String str;

    /*private OncloseListener listener;*/

    public WeightNumderPickDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public WeightNumderPickDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.mContext = context;
    }
    public WeightNumderPickDialog(@NonNull Context context, int themeResId, OncloseListener listener, String str) {
        super(context, themeResId);
        this.mContext = context;
        this.listener = listener;
        this.str = str.trim();
    }



    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_number_picked);
        setCanceledOnTouchOutside(false);
        title = (TextView) findViewById(R.id.weight_title);
        confirm = (TextView) findViewById(R.id.weight_confirm);
        cancel = (TextView) findViewById(R.id.weight_cancel);
        context = findViewById(R.id.dialog_content);
        weight_number = (MyNumberPicked) findViewById(R.id.weight_number);
        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
        initNumberPicked();

    }

    private void initNumberPicked() {
        weight_number.setMinValue(0);
        weight_number.setMaxValue(200);
        cancelValue = Integer.parseInt(str);
        weight_number.setValue(cancelValue);
        weight_number.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                currentValue = i1;
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.weight_confirm:
                if (listener != null) {
                    listener.onClick(true,currentValue);
                }
                this.dismiss();
                break;
            case R.id.weight_cancel:
                if (listener != null) {
                    listener.onClick(false,cancelValue);
                }
                this.dismiss();
                break;
        }
    }

    public interface OncloseListener {
        void onClick(boolean confirm,int a);
    }

}
