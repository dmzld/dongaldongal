package com.example.dongaldongal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity{

    private String student_id;

    private Button btnPwd;
    private Button btnPhonenum;
    private Button btnLeave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        student_id = intent.getStringExtra("id");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info);

        btnPwd = (Button)findViewById(R.id.btnPwd);
        btnPhonenum = (Button)findViewById(R.id.btnPhonenum);
        btnLeave = (Button)findViewById(R.id.btnLeave);

        btnPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StudentConfirm.class);
                intent.putExtra("id",student_id);
                intent.putExtra("select","1");
                startActivity(intent);
            }
        }); // 비밀번호 수정

        btnPhonenum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StudentConfirm.class);
                intent.putExtra("id",student_id);
                intent.putExtra("select","2");
                startActivity(intent);
            }
        }); //핸드폰 번호 수정

        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StudentConfirm.class);
                intent.putExtra("id",student_id);
                intent.putExtra("select","3");
                startActivity(intent);
            }
        }); //회원탈퇴


    }

}
