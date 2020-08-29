package com.example.watery.user;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.watery.R;
import com.example.watery.service.DatabaseHelper;
import com.example.watery.service.UserService;
import com.example.watery.utils.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment implements View.OnClickListener{
    private Spinner sex_spinner,status_spinner,weather_spinner;
    private String[] sex_arr, status_arr,weather_arr;
    private TextView weight,result;
    private Button measure_btn,aim_btn;
    private double measure;
    private String measureResault = null,weatherValue,statusValue,sexValue;
    private static OnDataTransmissionListener mListener;
    private TextView name;
    private int weightValue;
    private UserService userService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        initView(view);
        return view;
    }

    private void initView(View view){
        measure_btn= (Button) view.findViewById(R.id.measure_btn);
        aim_btn = (Button) view.findViewById(R.id.aim_btn);
        sex_spinner = (Spinner) view.findViewById(R.id.sex_spinner);
        status_spinner = (Spinner) view.findViewById(R.id.status_spinner);
        weather_spinner = (Spinner) view.findViewById(R.id.weather_spinner);
        result = (TextView) view.findViewById(R.id.result);
        name = (TextView) view.findViewById(R.id.name);

        //获取数据库中的用户名

        //获取体重
        /*userService = new UserService(view.getContext());
        weightValue = userService.getUserName("quinn");
        if(weightValue != 0){
            weight.setText(weightValue + ' ');
        }*/

        sex_arr = getResources().getStringArray(R.array.sex);
        status_arr = getResources().getStringArray(R.array.activity_statue);
        weather_arr = getResources().getStringArray(R.array.weather);
        weight = (TextView) view.findViewById(R.id.weight);
        weight.setOnClickListener(this);
        measure_btn.setOnClickListener(this);
        aim_btn.setOnClickListener(this);
        sex_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sexValue = (String) sex_spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        status_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                statusValue = (String) status_spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        weather_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                weatherValue = (String) weather_spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.weight:
                final WeightNumderPickDialog weightNumderPickDialog = new WeightNumderPickDialog(this.getActivity(),R.style.mdialog,
                        new WeightNumderPickDialog.OncloseListener() {
                            @Override
                            public void onClick(boolean confirm, int a) {
                                if(confirm){

                                }else{

                                }
                                weight.setText(a + " ");
                                //修改数据库中数据
                                /*userService.changeWeight("quinn",a);*/
                            }
                        }, (String) weight.getText());

                weightNumderPickDialog.show();
                break;
            case R.id.measure_btn:
                measure = Integer.parseInt(weight.getText().toString().trim()) * 22.5;
                switch (statusValue){
                    case "久坐" :
                        measure *= 1.1;
                        break;
                    case "偶尔运动" :
                        measure *= 1.2;
                        break;
                    case "经常运动" :
                        measure *= 1.3;
                        break;
                }
                switch (weatherValue){
                    case "适度" :
                        measure *= 1.0;
                        break;
                    case "寒冷" :
                        measure *= 0.9;
                        break;
                    case "炎热" :
                        measure *= 1.2;
                        break;
                }
                if(sexValue == "男"){
                    measure *= 1.2;
                }
                try {
                    measureResault =String.valueOf(Math.round(measure)) ;
                    result.setText(measureResault.toString() + "ml");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                break;
            case R.id.aim_btn:
                if(mListener != null){
                    if(measureResault == null){
                        Toast.makeText(view.getContext(),"还没有计算结果呐",Toast.LENGTH_SHORT).show();
                    }else{
                        mListener.dataTransmission(measureResault);
                        Toast.makeText(view.getContext(),"设置成功！",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }


    //fragment参数的传递(接口方式) 修改目标的值
    public interface OnDataTransmissionListener{
        public void dataTransmission(String data);
    }
    public static void setOnDataTransmissionListener(OnDataTransmissionListener Listener){
        mListener = Listener;
    }


}

