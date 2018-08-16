package com.example.dongaldongal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import org.w3c.dom.Text;

public class SelectActivity extends AppCompatActivity {

    private String student_id;

    Button btnClubList;
    Button btnFest;
    Button btnBoard;
    ImageButton btnLogout;
    TextView textInfo;
    Button btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Intent intent = getIntent();
        student_id = intent.getStringExtra("id");

        btnBoard = (Button) findViewById(R.id.btnBoard);
        btnClubList = (Button) findViewById(R.id.btnClubList);
        btnFest = (Button) findViewById(R.id.btnFest);
        btnLogout = (ImageButton) findViewById(R.id.btnLogout);
        textInfo = (TextView) findViewById(R.id.textInfo);
        textInfo.setText(student_id + "님");
        btnSetting = (Button) findViewById(R.id.btnSetting);

        btnBoard.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BoardActivity.class);
                intent.putExtra("id", student_id);
                startActivity(intent);
            }

        });

        btnClubList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ClubListActivity.class);
                startActivity(intent);
            }
        });

        btnFest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FestivalActivity.class);
                intent.putExtra("id", student_id);
                startActivity(intent);
            }

        });

        btnSetting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                intent.putExtra("id", student_id);
                startActivity(intent);
            }

        });

        btnLogout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);//현재 TASK를 비우고 새로운 TASK 생성
                startActivity(intent);
            }
            //로그인 할때 얻어온 데이터 처리를 따로 해야하는가?
        });
    }
}
