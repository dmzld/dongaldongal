package com.example.dongaldongal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class PostFestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_fest);

        TextView fest_name = (TextView)findViewById(R.id.fest_name);
        TextView fest_date = (TextView)findViewById(R.id.fest_date);
        TextView fest_club = (TextView)findViewById(R.id.fest_club);
        TextView fest_describe = (TextView)findViewById(R.id.fest_describe);

        Intent intent = getIntent();

        fest_name.setText(intent.getStringExtra("fest_name"));
        fest_date.setText(intent.getStringExtra("fest_date"));
        fest_club.setText(intent.getStringExtra("fest_club"));
        fest_describe.setText(intent.getStringExtra("fest_describe"));
    }
}
