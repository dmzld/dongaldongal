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
        TextView text_content;
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
        if(convertView==null){ // 이전에 생성된 것이 없으면 생성 아니면 기존의 것 사용
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);

            viewHolder = new ViewHolder();

            viewHolder.text_writer = (TextView)convertView.findViewById(R.id.writer);
            viewHolder.text_content = (TextView)convertView.findViewById(R.id.content);
            viewHolder.text_date = (TextView)convertView.findViewById(R.id.date);
            viewHolder.text_title  =(TextView)convertView.findViewById(R.id.title);
            viewHolder.image_club = (ImageView)convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.text_writer.setText(list_itemArrayList.get(position).getText1());
        viewHolder.text_title.setText(list_itemArrayList.get(position).getText2());
        viewHolder.text_content.setText(list_itemArrayList.get(position).getText3());
        viewHolder.text_date.setText(list_itemArrayList.get(position).getText4().toString());
        viewHolder.image_club.setImageResource(list_itemArrayList.get(position).getImage());

        return convertView;
    }


}
