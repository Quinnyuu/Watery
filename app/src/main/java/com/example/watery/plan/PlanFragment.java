package com.example.watery.plan;


import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.watery.R;
import com.example.watery.clockin.ClockFragment;
import com.example.watery.user.UserFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends Fragment implements View.OnClickListener{
    private static InsertRecordListener mListener;
    private RelativeLayout drinkPanel,largePanel;
    private FloatingActionButton add;
    private ImageButton tea,water,naicha,cola;
    private boolean isAdd = false;
    private ObjectAnimator teaMove,waterMove,naichaMove,colaMove;
    private View contextView;
    private PopupWindow popWindow;
    private EditText water_intake;
    private NumberProgressBar progressBar;
    private int lastest_aim,currentValue;
    private TextView aim_txt,aim_tip;
    private Button change_plan;
    private Button submit;
    private String type_name,date_txt,intake_txt;
    private int image_id;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        initView(view);
        setDefaultValues();
        return view;
    }

    @SuppressLint("WrongViewCast")
    public void initView(View view){
        drinkPanel = (RelativeLayout) view.findViewById(R.id.drinks_panel);
        largePanel = (RelativeLayout) view.findViewById(R.id.largeLabel);
        add = (FloatingActionButton) view.findViewById(R.id.add);
        tea = (ImageButton) view.findViewById(R.id.tea);
        cola = (ImageButton) view.findViewById(R.id.cola);
        naicha = (ImageButton) view.findViewById(R.id.naicha);
        water = (ImageButton) view.findViewById(R.id.water);
        progressBar = (NumberProgressBar) view.findViewById(R.id.progressBar);
        aim_txt = (TextView) view.findViewById(R.id.aim_txt);
        change_plan = (Button) view.findViewById(R.id.change_plan);
        submit = (Button) view.findViewById(R.id.submit);
        aim_tip = (TextView) view.findViewById(R.id.aim_tip);

        lastest_aim = Integer.parseInt(aim_txt.getText().toString());
        currentValue = 0;

        add.setOnClickListener(this);
        tea.setOnClickListener(this);
        naicha.setOnClickListener(this);
        cola.setOnClickListener(this);
        water.setOnClickListener(this);
        change_plan.setOnClickListener(this);
        initPopWindow();

        // fragment参数的传递(接口方式)
        UserFragment.setOnDataTransmissionListener(new UserFragment.OnDataTransmissionListener() {
            @Override
            public void dataTransmission(String data) {
                aim_txt.setText(data);
                lastest_aim = Integer.parseInt(data);
                changeAimTip();
            }
        });
        progressBar.setMax(lastest_aim);
        progressBar.setProgress(currentValue);

        //删除的数据的摄入量
        ClockFragment.setOnDeleteRecordListener(new ClockFragment.OnDeleteRecordListener() {
            @Override
            public void getDeleteData(int data) {
                currentValue -= data;
                progressBar.setProgress(currentValue);
                changeAimTip();
            }
        });
    }

    private void setDefaultValues(){
        colaMove = ObjectAnimator.ofFloat(cola,"translationX",0,-400);
        waterMove = ObjectAnimator.ofFloat(water,"translationX",0,-150);
        naichaMove = ObjectAnimator.ofFloat(naicha,"translationX",0,400);
        teaMove = ObjectAnimator.ofFloat(tea,"translationX",0,150);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add :
                add.setImageResource(isAdd ? R.mipmap.add : R.mipmap.cha);
                isAdd = !isAdd;
                drinkPanel.setVisibility(isAdd ? View.VISIBLE : View.INVISIBLE);
                if(isAdd){
                    teaMove.start();
                    waterMove.start();
                    naichaMove.start();
                    colaMove.start();
                }
                break;
            case R.id.tea:
                image_id = R.mipmap.tea;
                type_name = "摄入茶";
                popWindow.showAtLocation(this.contextView, Gravity.BOTTOM,0,0);
                hideMenu();
                break;
            case R.id.naicha:
                image_id = R.mipmap.naicha;
                type_name = "摄入奶茶";
                popWindow.showAtLocation(this.contextView, Gravity.BOTTOM,0,0);
                hideMenu();
                break;
            case R.id.water:
                image_id = R.mipmap.water;
                type_name = "摄入水";
                popWindow.showAtLocation(this.contextView, Gravity.BOTTOM,0,0);
                hideMenu();
                break;
            case R.id.cola:
                image_id = R.mipmap.cola;
                type_name = "摄入可乐";
                popWindow.showAtLocation(this.contextView, Gravity.BOTTOM,0,0);
                hideMenu();
                break;
            case R.id.change_plan://弹出对话框
                final AimNumderPickDialog aimNumderPickDialog = new AimNumderPickDialog(this.getActivity(),R.style.mdialog,
                        new AimNumderPickDialog.OncloseListener() {
                            @Override
                            public void onClick(boolean confirm, int a) {
                                if(confirm){

                                }else{

                                }
                                aim_txt.setText(a + " ");
                                lastest_aim = a;
                                changeAimTip();
                            }
                        }, (String) aim_txt.getText());
                aimNumderPickDialog.show();
                break;
        }
    }

    private void changeAimTip(){
        if(currentValue >= lastest_aim){
            aim_tip.setText("超出任务摄入" + (currentValue - lastest_aim) + "ml");
        }else{
            aim_tip.setText("还需摄入" + (lastest_aim - currentValue) + "ml");
        }
        progressBar.setMax(lastest_aim);
    }

    private void hideMenu(){
        drinkPanel.setVisibility(View.INVISIBLE);
        add.setImageResource(R.mipmap.add);
        isAdd = false;
    }


    private void initPopWindow(){
        contextView = getActivity().getLayoutInflater().inflate(R.layout.pop_keyboard,null);
        popWindow = new PopupWindow(contextView, ViewGroup.LayoutParams.MATCH_PARENT, 190, true);
        popWindow.setFocusable(true);
        popWindow.setTouchable(true);
        popWindow.setOutsideTouchable(true);
        popWindow.setAnimationStyle(R.style.mypopwindow_anim_style);//设置弹出效果

        water_intake = (EditText) contextView.findViewById(R.id.water_intake);
        water_intake.setFocusable(true);
        water_intake.setFocusableInTouchMode(true);
        water_intake.requestFocus();
        water_intake.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        submit = (Button) contextView.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mListener != null){
                    if(water_intake.getText() == null){
                        Toast.makeText(view.getContext(),"请输入摄水量",Toast.LENGTH_SHORT).show();
                    }else{
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日   HH:mm");
                        Date date = new Date(System.currentTimeMillis());
                        date_txt = simpleDateFormat.format(date);
                        intake_txt = water_intake.getText().toString();
                        mListener.insertRecord(image_id,type_name,date_txt,intake_txt);
                        Toast.makeText(view.getContext(),"添加成功！",Toast.LENGTH_SHORT).show();
                        currentValue += Integer.parseInt(water_intake.getText().toString());
                        water_intake.setText("");
                        progressBar.setProgress(currentValue);
                        if(currentValue == lastest_aim){
                            Toast.makeText(view.getContext(),"完成任务！",Toast.LENGTH_SHORT).show();
                            progressBar.setProgress(lastest_aim);
                        }else if(currentValue > lastest_aim){
                            aim_tip.setText("超出任务摄入" + (currentValue - lastest_aim) + "ml");
                            progressBar.setProgress(lastest_aim);
                        }else{
                            aim_tip.setText("还需摄入" + (lastest_aim - currentValue) + "ml");
                        }
                    }
                }
            }
        });
    }


    //接口方式 插入记录
    public interface InsertRecordListener{
        public void insertRecord(int image_id,String type_name,String date_txt,String intake_txt);
    }
    public static void setInsertRecordListener(InsertRecordListener Listener){
        mListener = Listener;
    }

}
