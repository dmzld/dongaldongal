package com.example.dongaldongal;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ClubDetailActivity2 extends AppCompatActivity {

    private String c_name;

    String myJSON;
    JSONArray list = null;
    private static final String TAG_RESULTS = "result";

    private static final String TAG_club_describe = "club_describe";
    private static final String TAG_category = "category";
    private static final String TAG_phonenum="phonenum";
    private static final String TAG_rank="rank";
    private static final String TAG_club_number="club_number";
    private static final String TAG_club_location="club_location";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_detail2);

        TextView club_name = (TextView)findViewById(R.id.club_name);

        Intent intent = getIntent();
        c_name = intent.getStringExtra("name");

        club_name.setText(intent.getStringExtra("name"));//name은 intent로 받아옴




        getData("http://52.11.180.128/dbProject/8.php",c_name);


    }

    protected void showList() {
        Log.i("log2","log2");
        try {
            Log.i("log3","log3");
            JSONObject jsonObj = new JSONObject(myJSON);
            list = jsonObj.getJSONArray(TAG_RESULTS);
            Log.i("log4","log4");

            String club_describe = null;
            String category = null;
            String phonenum = null;
            String rank = null;
            String club_number = null;
            String club_location = null;


            for (int i = 0; i < list.length(); i++) {

                Log.i("log5","log5");
                JSONObject c = list.getJSONObject(i);

                //php에서 정보받기
                club_describe = c.getString(TAG_club_describe);
                category=c.getString(TAG_category);
                phonenum=c.getString(TAG_phonenum);
                rank=c.getString(TAG_rank);
                club_number=c.getString(TAG_club_number);
                club_location=c.getString(TAG_club_location);

                Log.i("club_describe",club_describe);
            }

            /////텍스트에 넣기
            TextView club_describe_t = (TextView)findViewById(R.id.club_describe) ;
            TextView category_t = (TextView)findViewById(R.id.category);
            TextView phonenum_t = (TextView)findViewById(R.id.phonenum);
            TextView rank_t = (TextView)findViewById(R.id.rank);
            TextView club_number_t = (TextView)findViewById(R.id.club_number);
            TextView club_location_t = (TextView)findViewById(R.id.club_loaction);



            club_describe_t.setText("동아리 소개 "+"\n"+club_describe);
            category_t.setText("분류 "+"\n"+category);
            phonenum_t.setText("연락처 "+"\n"+phonenum);
            rank_t.setText("등급 "+"\n"+rank);
            club_number_t.setText("동아리번호 "+"\n"+club_number);
            club_location_t.setText("위치 "+"\n"+club_location);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("club_describe","club_describe");
        }
    }

    public void getData(String url, String c_name) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {


                BufferedReader bufferedReader = null;
                try {
                    Log.i("logi","logi");
                    String uri = params[0];
                    String c_name = params[1];
                    Log.i("club_name",c_name);
                    String data = URLEncoder.encode("club_name","UTF-8")+"="+URLEncoder.encode(c_name,"UTF-8");

                    Log.i("data",data);
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
                        Log.i("after5",json);
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
        g.execute(url,c_name);
    }

}
