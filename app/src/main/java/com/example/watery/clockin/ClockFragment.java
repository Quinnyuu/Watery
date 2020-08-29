package com.example.watery.clockin;

import android.net.sip.SipSession;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.watery.R;
import com.example.watery.adapter.RecordAdapter;
import com.example.watery.plan.PlanFragment;
import com.example.watery.utils.RecordBean;

import java.util.ArrayList;
import java.util.List;

import hlq.com.slidedeletelistview.BtnDeleteListern;
import hlq.com.slidedeletelistview.SlideDeleteListView;


import static com.example.watery.plan.PlanFragment.setInsertRecordListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClockFragment extends Fragment {
    private List<RecordBean> mRecordBeanList;
    private SlideDeleteListView mRecordList;
    private RecordAdapter adapter;
    private static OnDeleteRecordListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clock, container, false);
        mRecordList = (SlideDeleteListView) view.findViewById(R.id.record_list);
        setInsertRecordListener(new PlanFragment.InsertRecordListener() {
            @Override
            public void insertRecord(int image_id, String type_name, String date_txt, String intake_txt) {
                RecordBean record = new RecordBean(image_id,intake_txt,type_name,date_txt);
                mRecordBeanList.add(record);
            }
        });
        mRecordBeanList = new ArrayList<>();
        adapter = new RecordAdapter(mRecordBeanList,getContext());
        mRecordList.setAdapter(adapter);
        mRecordList.setBtnDelClickListener(new BtnDeleteListern() {
            @Override
            public void deleteOnCliclListern(int positon) {
                if(mListener != null){
                    RecordBean recordBean = (RecordBean) adapter.getItem(positon);
                    mListener.getDeleteData(Integer.parseInt(recordBean.getRecordIntake().trim()));

                }
                mRecordBeanList.remove(positon);
                adapter.notifyDataSetChanged();

            }
        });
        return view;
    }
    //传回删除的数据的摄入量
    public interface OnDeleteRecordListener{
        public void getDeleteData(int data);
    }
    public static void setOnDeleteRecordListener(OnDeleteRecordListener listener){
        mListener = listener;
    }

}
