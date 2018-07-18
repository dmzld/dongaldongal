package com.example.dongaldongal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FestListAdapter extends BaseAdapter {

    Context context;
    ArrayList<list_fest> list_festArrayList;
    ViewHolder viewHolder;

    class ViewHolder{

        TextView fest_name;
        TextView fest_date;
        TextView fest_club;
        //TextView fest_decribe;
        ImageView image_fest;

    }

    public FestListAdapter(Context context, ArrayList<list_fest> list_festArrayList) {
        this.context = context;
        this.list_festArrayList = list_festArrayList;
    }

    @Override
    public int getCount() {
        return this.list_festArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_festArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.fest,null);

            viewHolder = new ViewHolder();

            viewHolder.fest_name = (TextView)convertView.findViewById(R.id.text_fest_name);
            //viewHolder.fest_describe = (TextView)convertView.findViewById(R.id.text_fest_describe);
            viewHolder.fest_club = (TextView)convertView.findViewById(R.id.text_fest_club);
            viewHolder.fest_date  =(TextView)convertView.findViewById(R.id.text_fest_date);
            viewHolder.image_fest = (ImageView)convertView.findViewById(R.id.image_fest);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.fest_name.setText(list_festArrayList.get(position).getFest_name());
        viewHolder.fest_club.setText(list_festArrayList.get(position).getFest_club());
        //viewHolder.text_describe.setText(list_itemArrayList.get(position).getText_content());
        viewHolder.fest_date.setText(list_festArrayList.get(position).getFest_date().toString());
        viewHolder.image_fest.setImageResource(list_festArrayList.get(position).getImage_fest());

        return convertView;
    }


}
