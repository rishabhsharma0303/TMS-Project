package com.example.tms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    private static LayoutInflater inflater = null;
    public GridAdapter(Context context, ArrayList<String> img_string) {
        this.context = context;
        this.img_string = img_string;
    }

    ArrayList<String> img_string;
    @Override
    public int getCount() {
        return img_string.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*ImageView imag_grid=new ImageView(context);
       // imag_grid.setImageResource(img_string.get(position));
        imag_grid.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imag_grid.setLayoutParams(new GridView.LayoutParams(340,350));
        Glide.with(context).load(img_string.get(position)).into(imag_grid);
        return imag_grid;*/
        View vi = convertView;
        if (vi == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        vi=inflater.inflate(R.layout.recycler_row_item,null);
ImageView img=(ImageView)vi.findViewById(R.id.img_item);
        Glide.with(context).load(img_string.get(position)).into(img);
        return vi;





    }
}
