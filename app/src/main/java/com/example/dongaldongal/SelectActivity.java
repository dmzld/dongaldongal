package com.example.dongaldongal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends AppCompatActivity {

    private String student_id;

    Button btnClubList;
    Button btnFest;
    Button btnBoard;
    Button btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Intent intent = getIntent();
        student_id = intent.getStringExtra("id");

        btnBoard = (Button)findViewById(R.id.btnBoard);
        btnClubList = (Button)findViewById(R.id.btnClubList);
        btnFest = (Button)findViewById(R.id.btnFest);
        btnSetting=(Button)findViewById(R.id.btnSetting);

        btnBoard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),BoardActivity.class);
                intent.putExtra("id",student_id);
                startActivity(intent);
            }

        });

        btnClubList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ClubListActivity.class);
                startActivity(intent);
            }
        });

        btnFest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FestivalActivity.class);
                intent.putExtra("id",student_id);
                startActivity(intent);
            }

        });

        btnSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
                intent.putExtra("id",student_id);
                startActivity(intent);
            }

        });

    }
}
