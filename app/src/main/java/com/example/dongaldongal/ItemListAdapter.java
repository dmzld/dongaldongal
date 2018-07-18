package com.example.dongaldongal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemListAdapter extends BaseAdapter {

    Context context;
    ArrayList<list_item> list_itemArrayList;
    ViewHolder viewHolder;

    class ViewHolder{

        TextView text_writer;
        TextView text_title;
        TextView text_date;
        //TextView text_content;
        ImageView image_club;

    }


    public ItemListAdapter(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }

    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);

            viewHolder = new ViewHolder();

            viewHolder.text_writer = (TextView)convertView.findViewById(R.id.text_club);
            //viewHolder.text_content = (TextView)convertView.findViewById(R.id.text_content);
            viewHolder.text_date = (TextView)convertView.findViewById(R.id.text_phone);
            viewHolder.text_title  =(TextView)convertView.findViewById(R.id.text_name);
            viewHolder.image_club = (ImageView)convertView.findViewById(R.id.image_club);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.text_writer.setText(list_itemArrayList.get(position).getText_club());
        viewHolder.text_title.setText(list_itemArrayList.get(position).getText_title());
        //viewHolder.text_content.setText(list_itemArrayList.get(position).getText_content());
        viewHolder.text_date.setText(list_itemArrayList.get(position).getText_date().toString());
        viewHolder.image_club.setImageResource(list_itemArrayList.get(position).getImage_club());

        return convertView;
    }


}
