package com.example.dongaldongal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class WriteFestActivity extends AppCompatActivity {

    private String student_id;

    private EditText fest_name;
    private EditText fest_date;
    private EditText fest_club;
    private EditText fest_describe;
    private Button btnWrite;
    private Button btnCancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_fest);

        Intent intent = getIntent();
        student_id = intent.getStringExtra("id");

        fest_name = (EditText) findViewById(R.id.fest_name);
        fest_date = (EditText) findViewById(R.id.fest_date);
        fest_club = (EditText) findViewById(R.id.fest_club);
        fest_describe = (EditText) findViewById(R.id.fest_describe);
        btnWrite = (Button) findViewById(R.id.btnWrite);
        btnCancel = (Button) findViewById(R.id.btnCancel);


        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //제목 입력 확인
                if(fest_name.getText().toString().length() == 0 ) {
                    Toast.makeText(WriteFestActivity.this, "행사이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    fest_name.requestFocus();
                    return;
                }

                //행사날짜
                if(fest_date.getText().toString().length() == 0 ) {
                    Toast.makeText(WriteFestActivity.this, "행사날짜를 입력하세요!", Toast.LENGTH_SHORT).show();
                    fest_date.requestFocus();
                    return;
                }

                //행사주최동아리
                if(fest_club.getText().toString().length() == 0 ) {
                    Toast.makeText(WriteFestActivity.this, "주최동아리를 입력하세요!", Toast.LENGTH_SHORT).show();
                    fest_club.requestFocus();
                    return;
                }

                Log.i("log24",fest_club.getText().toString());
                //행사내용
                if(fest_describe.getText().toString().length() == 0 ) {
                    Toast.makeText(WriteFestActivity.this, "행사내용를 입력하세요!", Toast.LENGTH_SHORT).show();
                    fest_describe.requestFocus();
                    return;
                }
                Log.i("log23", fest_describe.getText().toString());

                //함수로 전달
                showData(student_id.toString(),fest_name.getText().toString(),fest_date.getText().toString(), fest_club.getText().toString(), fest_describe.getText().toString());

                Intent result = new Intent();
                result.putExtra("StudentID", student_id);

                // 자신을 호출한 Activity로 데이터를 보낸다.
                setResult(RESULT_OK, result);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void showData(String student_id, String fest_name, String fest_date, String fest_club, String fest_describe){

        class InsetData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected  String doInBackground(String... params)
            {
                try{
                    String student_id = (String)params[0];
                    String fest_name = (String)params[1];
                    String fest_date = (String)params[2];
                    String fest_club = (String)params[3];
                    String fest_describe = (String)params[4];


                    String link = "http://52.11.180.128/dbProject/7.php";
                    String data = URLEncoder.encode("student_id","UTF-8")+"="+URLEncoder.encode(student_id,"UTF-8");
                    data += "&" + URLEncoder.encode("fest_name","UTF-8")+"="+URLEncoder.encode(fest_name,"UTF-8");
                    data += "&" + URLEncoder.encode("fest_date","UTF-8")+"="+URLEncoder.encode(fest_date,"UTF-8");
                    data += "&" + URLEncoder.encode("fest_club","UTF-8")+"="+URLEncoder.encode(fest_club,"UTF-8");
                    data += "&" + URLEncoder.encode("fest_describe","UTF-8")+"="+URLEncoder.encode(fest_describe,"UTF-8");


                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                    Log.i("log22",data);

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e)
                {
                    return new String("Exception: "+e.getMessage());
                }

            }
        }

        InsetData task = new InsetData();
        task.execute(student_id,fest_name,fest_date,fest_club,fest_describe);
    }

}

