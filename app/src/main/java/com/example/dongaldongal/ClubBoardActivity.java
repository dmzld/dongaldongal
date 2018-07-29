package com.example.dongaldongal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ClubBoardActivity extends AppCompatActivity {


    ListView listView;
    clubListAdapter clubBoardListAdapter;
    ArrayList<list_clubPost> list_clubBoardArrayList;

    private String club_name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_board);

        Intent intent = getIntent();
        club_name = intent.getStringExtra("name");

        listView = (ListView) findViewById(R.id.clubBoard);
        list_clubBoardArrayList = new ArrayList<list_clubPost>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),ClubPostActivity.class);
                intent.putExtra("title",list_clubBoardArrayList.get(position).getText_title());
                intent.putExtra("content",list_clubBoardArrayList.get(position).getText_content());
                intent.putExtra("club",list_clubBoardArrayList.get(position).getText_club());
                intent.putExtra("date",list_clubBoardArrayList.get(position).getText_date());
                startActivity(intent);

            }
        });

        //php 코드 작성 필요

    }
}
