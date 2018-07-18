package com.example.dongaldongal;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class PostActivity2 extends AppCompatActivity {

    //Intent intent;
    String text_club;
    String myJSON;
    JSONArray list = null;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_describe = "describe";
    private static final String TAG_name = "club_name";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postclub);

        TextView category = (TextView)findViewById(R.id.category);
        TextView club_phonenum = (TextView)findViewById(R.id.club_phonenum);
        TextView rank = (TextView)findViewById(R.id.rank);
        TextView club_number = (TextView)findViewById(R.id.club_number);
        TextView club_name = (TextView)findViewById(R.id.club_name);
        TextView club_location = (TextView)findViewById(R.id.club_loaction);
        //TextView club_describe = (TextView)findViewById(R.id.club_describe) ;


        Intent intent = getIntent();

        club_number.setText(intent.getStringExtra("num"));
        club_name.setText(intent.getStringExtra("name"));
        club_phonenum.setText(intent.getStringExtra("phone"));
        club_location.setText(intent.getStringExtra("location"));
        category.setText(intent.getStringExtra("div"));
        rank.setText(intent.getStringExtra("level"));

        String number = club_number.getText().toString().substring(7);
        Log.i("club_number",number);

        getData("http://ec2-18-221-90-55.us-east-2.compute.amazonaws.com/9.php",number);


    }


    protected void showList() {
        Log.i("log2","log2");
        try {
            Log.i("log3","log3");
            JSONObject jsonObj = new JSONObject(myJSON);
            list = jsonObj.getJSONArray(TAG_RESULTS);
            Log.i("log4","log4");
            String describe =null;

            for (int i = 0; i < list.length(); i++) {

                JSONObject c = list.getJSONObject(i);

                describe = c.getString(TAG_describe);
                Log.i("describe",describe);
            }
            TextView club_describe = (TextView)findViewById(R.id.club_describe) ;
            club_describe.setText("동아리 소개"+"\n"+describe);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.i("describe2","describe2");
        }
    }

    public void getData(String url, String club_number) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {


                BufferedReader bufferedReader = null;
                try {
                    Log.i("logi","logi");
                    String uri = params[0];
                    String club_number = params[1];
                    Log.i("club_name",club_number);
                    String data = URLEncoder.encode("club_number","UTF-8")+"="+URLEncoder.encode(club_number,"UTF-8");

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
        g.execute(url,club_number);
    }

}