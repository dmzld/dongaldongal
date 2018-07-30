package com.example.dongaldongal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Date;

public class PostActivity extends AppCompatActivity {

    //Intent intent;
    String text_club;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        TextView post_title = (TextView)findViewById(R.id.club_number);
        TextView post_date = (TextView)findViewById(R.id.post_date);
        TextView post_club = (TextView)findViewById(R.id.post_club);
        TextView post_content = (TextView)findViewById(R.id.post_content);

        Intent intent = getIntent();

        post_title.setText(intent.getStringExtra("title"));
        post_date.setText(intent.getStringExtra("date"));
        post_club.setText(intent.getStringExtra("club"));
        post_content.setText(intent.getStringExtra("content"));

    }
}
