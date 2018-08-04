package com.example.dongaldongal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class clubListAdapter extends BaseAdapter {

    Context context;
    ArrayList<list_club> list_clubArrayList;
    ViewHolder viewHolder;

    class ViewHolder{

        TextView text_name;
        TextView text_div;
        TextView text_phone;
        TextView text_level;

    }


    public clubListAdapter(Context context, ArrayList<list_club> list_clubArrayList) {
        this.context = context;
        this.list_clubArrayList = list_clubArrayList;
    }

    @Override
    public int getCount() {
        return this.list_clubArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return list_clubArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.club,null);

            viewHolder = new ViewHolder();

            viewHolder.text_name = (TextView)convertView.findViewById(R.id.title);
            viewHolder.text_div = (TextView)convertView.findViewById(R.id.text_div);
            viewHolder.text_phone = (TextView)convertView.findViewById(R.id.date);
            viewHolder.text_level  =(TextView)convertView.findViewById(R.id.text_level);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.text_name.setText(list_clubArrayList.get(position).getText_name());
        viewHolder.text_div.setText(list_clubArrayList.get(position).getText_div());
        viewHolder.text_phone.setText(list_clubArrayList.get(position).getText_phone());
        viewHolder.text_level.setText(list_clubArrayList.get(position).getText_level());


        return convertView;
    }


}
