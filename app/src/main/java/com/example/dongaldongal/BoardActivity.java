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


public class    BoardActivity extends AppCompatActivity {

    private String student_id;

    String myJSON;
    ListView listView;
    ItemListAdapter myListAdapter;
    ArrayList<list_item> list_itemArrayList;


    Button searchCenter;
    Button searchMajor;
    Button writePost;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_title = "title";
    private static final String TAG_club_name = "club_name";
    private static final String TAG_post_date = "post_date";
    private static final String TAG_content = "content";

    JSONArray list = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        //id 받음
        Intent intent = getIntent();
        student_id = intent.getStringExtra("id");



        listView = (ListView) findViewById(R.id.totalBoard);
        list_itemArrayList = new ArrayList<list_item>();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),PostActivity.class);
                intent.putExtra("title",list_itemArrayList.get(position).getText_title());
                intent.putExtra("content",list_itemArrayList.get(position).getText_content());
                intent.putExtra("club",list_itemArrayList.get(position).getText_club());
                intent.putExtra("date",list_itemArrayList.get(position).getText_date());
                startActivity(intent);

            }
        });

        //글쓰기
        writePost=(Button)findViewById(R.id.btnWrite);
        writePost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WriteActivity.class);

                intent.putExtra("id",student_id);
                startActivity(intent);
            }

        });



       getData("http://52.11.180.128/dbProject/3.php");

    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            list = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < list.length(); i++) {
                JSONObject c = list.getJSONObject(i);
                String title = c.getString(TAG_title);
                String club_name = c.getString(TAG_club_name);
                String post_date = c.getString(TAG_post_date);
                String content = c.getString(TAG_content);
                list_item lists = new list_item(R.drawable.aj,"동아리명 "+"\n"+club_name,"제목 "+"\n"+title,"작성일자 "+"\n"+post_date,"내용 "+"\n"+content);
                list_itemArrayList.add(lists);
            }
            Collections.reverse(list_itemArrayList);
            myListAdapter = new ItemListAdapter(BoardActivity.this,list_itemArrayList);
            listView.setAdapter(myListAdapter);
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
