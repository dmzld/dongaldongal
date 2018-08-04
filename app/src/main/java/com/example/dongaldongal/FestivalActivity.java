package com.example.dongaldongal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class FestivalActivity extends AppCompatActivity {

    private String student_id;

    String myJSON;
    ListView listView;
    FestListAdapter festAdapter;
    ArrayList<list_item> list_festArrayList;


    Button writeFest;

    private static final String TAG_RESULTS = "result";
    private static final String TAG_fest_name = "fest_name";
    private static final String TAG_fest_club = "fest_club";
    private static final String TAG_fest_date = "fest_date";
    private static final String TAG_fest_describe = "fest_describe";

    JSONArray list = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festival);

        //id 받음
        Intent intent = getIntent();
        student_id = intent.getStringExtra("id");



        listView = (ListView) findViewById(R.id.festBoard);
        list_festArrayList = new ArrayList<list_item>();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //수정
                Intent intent = new Intent(getApplicationContext(),PostFestActivity.class);
                intent.putExtra("fest_name",list_festArrayList.get(position).getText1());
                intent.putExtra("fest_club",list_festArrayList.get(position).getText2());
                intent.putExtra("fest_date",list_festArrayList.get(position).getText3());
                intent.putExtra("fest_describe",list_festArrayList.get(position).getText4());
                startActivity(intent);

            }
        });

        //글쓰기
        writeFest=(Button)findViewById(R.id.btnWrite);
        writeFest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WriteFestActivity.class);
                intent.putExtra("id",student_id);
                startActivity(intent);
            }

        });
        getData("http://52.11.180.128/dbProject/6.php");
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            list = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < list.length(); i++) {
                JSONObject c = list.getJSONObject(i);
                String fest_name = c.getString(TAG_fest_name);
                String fest_club = c.getString(TAG_fest_club);
                String fest_date = c.getString(TAG_fest_date);
                String fest_describe = c.getString(TAG_fest_describe);

                list_item lists = new list_item(R.mipmap.ic_launcher,"행사명 "+"\n"+fest_name,"주최동아리 "+"" +
                        "\n"+fest_club, "행사날짜 "+"\n"+fest_date,"행사내용 "+"\n"+fest_describe);
                list_festArrayList.add(lists);
            }
            Collections.reverse(list_festArrayList);
            festAdapter = new FestListAdapter(FestivalActivity.this,list_festArrayList);
            listView.setAdapter(festAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }


            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}
