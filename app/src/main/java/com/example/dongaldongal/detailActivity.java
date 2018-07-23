package com.example.dongaldongal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class detailActivity extends AppCompatActivity {


    private String category;

    String myJSON;
    ListView listView;
    clubListAdapter myListAdapter;
    ArrayList<list_club> list_clubArrayList;


    private static final String TAG_RESULTS = "result";
    private static final String TAG_number = "club_number";
    private static final String TAG_name = "club_name";
    private static final String TAG_phone = "phonenum";
    private static final String TAG_loaction = "club_location";
    private static final String TAG_div = "category";
    private static final String TAG_level = "rank";

    JSONArray list = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        category = intent.getStringExtra("category");

        listView = (ListView) findViewById(R.id.totalBoard);
        list_clubArrayList = new ArrayList<list_club>();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(),PostActivity2.class);
                intent.putExtra("num",list_clubArrayList.get(position).getText_number());
                intent.putExtra("name",list_clubArrayList.get(position).getText_name());
                intent.putExtra("phone",list_clubArrayList.get(position).getText_phone());
                intent.putExtra("location",list_clubArrayList.get(position).getText_locaton());
                intent.putExtra("div",list_clubArrayList.get(position).getText_div());
                intent.putExtra("level",list_clubArrayList.get(position).getText_level());
                startActivity(intent);

            }
        });

        if(category.equals("0"))
        {getData("http://52.11.180.128/dbProject/6_1.php",category);}
        else
        {getData("http://52.11.180.128/dbProject/7_1.php",category);}

    }

    protected void showList() {
        try {
            Log.i("showlist","showlist");
            JSONObject jsonObj = new JSONObject(myJSON);
            list = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < list.length(); i++) {
                Log.i("showlist2","showlist2");

                JSONObject c = list.getJSONObject(i);

                String num = c.getString(TAG_number);
                String name = c.getString(TAG_name);
                String phone = c.getString(TAG_phone);
                String location = c.getString(TAG_loaction);
                String div = c.getString(TAG_div);
                String level = c.getString(TAG_level);

                list_club lists = new list_club("동아리번호 "+"\n"+num,"동아리명 "+"\n"+name,"연락처 "+"\n"+phone,"위치 "+"\n"+location,"분류 "+"\n"+div,"등급 "+"\n"+level);
                list_clubArrayList.add(lists);
            }
            myListAdapter = new clubListAdapter(detailActivity.this,list_clubArrayList);
            listView.setAdapter(myListAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url, String category) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {


                BufferedReader bufferedReader = null;
                try {
                    Log.i("back","backt");
                    String uri = params[0];
                    String category = params[1];
                    String data = URLEncoder.encode("category","UTF-8")+"="+URLEncoder.encode(category,"UTF-8");
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    con.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    Log.i("after","after");

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
        g.execute(url,category);
    }

}
