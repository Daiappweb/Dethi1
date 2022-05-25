package com.example.chuade1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class personAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<person>data;
    private LayoutInflater inflater;

    public personAdapter(Activity activity, ArrayList<person> data) {
        this.activity = activity;
        this.data = data;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if(v==null){
            v = inflater.inflate(R.layout.list_item,null);
            TextView id = v.findViewById(R.id.txtId);
            String s = String.valueOf(position+1);
            id.setText(s);
            TextView name = v.findViewById(R.id.txtName);
            name.setText(data.get(position).getName());
            TextView phone = v.findViewById(R.id.txtPhone);
            phone.setText(data.get(position).getPhone());
        }
        return v;
    }
}
