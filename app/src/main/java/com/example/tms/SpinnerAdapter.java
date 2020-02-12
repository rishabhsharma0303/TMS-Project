package com.example.tms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<Price> arrayList;
    private static LayoutInflater inflater = null;
    public SpinnerAdapter(Context context,ArrayList<Price> arrayList) {
        this.mContext=context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        //LayoutInflater inflater = null;
        if (vi == null)
            inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vi=inflater.inflate(R.layout.spinner_desigm,null);
        TextView classFees=vi.findViewById(R.id.class_fees);
        TextView classFees_rate=vi.findViewById(R.id.class_fees_rate);

        // classFees.setText(arrayList.get(position).getItem_name()+"("+arrayList.get(position).getRupees()+")");
    classFees.setText(arrayList.get(position).getItem_name());

    classFees_rate.setText(String.valueOf(arrayList.get(position).getRupees())+"/-");
        return vi;
    }
}
