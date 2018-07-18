package com.example.dongaldongal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

public class ClubListActivity extends AppCompatActivity {

    Button btnAllClub;
    Button btnLeisure;
    Button btnReligion;
    Button btnSocial;
    Button btnStudy;
    Button btnScience;
    Button btnSport;
    Button btnArt;
    Button btnCreative;
    SearchView SearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_list);


        btnAllClub = (Button)findViewById(R.id.btnAllClub);
        btnLeisure = (Button)findViewById(R.id.btnLeisure);
        btnReligion = (Button)findViewById(R.id.btnReligion);
        btnSocial = (Button)findViewById(R.id.btnSocial);
        btnStudy = (Button)findViewById(R.id.btnStudy);
        btnScience = (Button)findViewById(R.id.btnScience);
        btnSport = (Button)findViewById(R.id.btnSport);
        btnArt = (Button)findViewById(R.id.btnArt);
        btnCreative = (Button)findViewById(R.id.btnCreative);
        SearchView = (SearchView)findViewById(R.id.SearchView);


        btnAllClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detailActivity.class);
                intent.putExtra("category","0");
                startActivity(intent);
            }
        }); // 모든 동아리

        btnLeisure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detailActivity.class);
                intent.putExtra("category","레져스포츠");
                startActivity(intent);
            }
        }); //레저 스포츠

        btnReligion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detailActivity.class);
                intent.putExtra("category","종교");
                startActivity(intent);
            }
        }); //종교

        btnSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detailActivity.class);
                intent.putExtra("category","사회활동");
                startActivity(intent);
            }
        }); //사회활동

        btnStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detailActivity.class);
                intent.putExtra("category","학술");
                startActivity(intent);
            }
        }); //학술

        btnScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detailActivity.class);
                intent.putExtra("category","과학기술");
                startActivity(intent);
            }
        }); //과학기술

        btnSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detailActivity.class);
                intent.putExtra("category","체육");
                startActivity(intent);
            }
        }); //체육

        btnArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detailActivity.class);
                intent.putExtra("category","연행예술");
                startActivity(intent);
            }
        }); //연행예술

        btnCreative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),detailActivity.class);
                intent.putExtra("category","창작전시");
                startActivity(intent);
            }
        }); //연행예술

        SearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(getApplicationContext(),ClubDetailActivity2.class);
                intent.putExtra("name",s);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });
    }
}
