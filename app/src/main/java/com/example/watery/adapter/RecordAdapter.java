package com.example.watery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.watery.R;
import com.example.watery.utils.RecordBean;

import java.util.List;

public class RecordAdapter extends BaseAdapter {
    public List<RecordBean> mList;
    public Context mContext;
    public LayoutInflater mLayoutInflater;

    public RecordAdapter(List<RecordBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = mLayoutInflater.inflate(R.layout.list_item, null);
            viewHolder.mImageView = (ImageView) view.findViewById(R.id.icon);
            viewHolder.drinkTypeView = (TextView) view.findViewById(R.id.record_drink_type);
            viewHolder.recordIntakeView = (TextView) view.findViewById(R.id.record_intake);
            viewHolder.dateView = (TextView) view.findViewById(R.id.record_date);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        RecordBean recordBean = mList.get(i);
        viewHolder.mImageView.setImageResource(recordBean.getWaterTypeImage());
        viewHolder.drinkTypeView.setText(recordBean.getTypeName());
        viewHolder.recordIntakeView.setText(recordBean.getRecordIntake());
        viewHolder.dateView.setText(recordBean.getDate());
        return view;
    }
    private static class ViewHolder{
        public ImageView mImageView;
        public TextView drinkTypeView;
        public TextView recordIntakeView;
        public TextView dateView;
    }
}


