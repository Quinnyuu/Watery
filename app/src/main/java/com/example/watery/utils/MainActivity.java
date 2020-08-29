package com.example.watery.utils;

import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.example.watery.R;
import com.example.watery.clockin.ClockFragment;
import com.example.watery.plan.PlanFragment;
import com.example.watery.user.UserFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    RadioGroup radioGroup;
    Fragment planFrag,userFrag,clockFrag;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏显示*/
        setContentView(R.layout.activity_main);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(this);

        planFrag = new PlanFragment();
        clockFrag = new ClockFragment();
        userFrag = new UserFragment();
        addFragment();
        hideBottomUIMenu();
    }

    private void addFragment() {
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        //将fragment加到布局中，并隐藏未选择的
        transaction.add(R.id.center_contain,userFrag);
        transaction.add(R.id.center_contain,planFrag);
        transaction.add(R.id.center_contain,clockFrag);

        transaction.hide(clockFrag);
        transaction.hide(userFrag);
        transaction.commit();//提交事务
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        transaction = manager.beginTransaction();
        switch (checkedId){
            case R.id.plan_btn:
                transaction.show(planFrag);
                transaction.hide(clockFrag);
                transaction.hide(userFrag);
                break;
            case R.id.clockin_btn:
                transaction.hide(planFrag);
                transaction.show(clockFrag);
                transaction.hide(userFrag);
                break;
            case R.id.user_btn:
                transaction.hide(planFrag);
                transaction.hide(clockFrag);
                transaction.show(userFrag);
                break;
        }
        transaction.commit();
    }

    public void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
