package com.example.dongaldongal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {

    private String student_id;
    private EditText title;
    private EditText content;
    private EditText club_name;
    private Button btnwrite;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        Intent intent = getIntent();
        student_id = intent.getStringExtra("id");


        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);
        club_name = (EditText) findViewById(R.id.club);
        btnwrite = (Button) findViewById(R.id.btnWrite);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnwrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //제목 입력 확인
                if(title.getText().toString().length() == 0 ) {
                    Toast.makeText(WriteActivity.this, "제목을 입력하세요!", Toast.LENGTH_SHORT).show();
                    title.requestFocus();
                    return;
                }

                //동아리이름
                if(club_name.getText().toString().length() == 0 ) {
                    Toast.makeText(WriteActivity.this, "동아리명을 입력하세요!", Toast.LENGTH_SHORT).show();
                    club_name.requestFocus();
                    return;
                }

                //내용
                if(content.getText().toString().length() == 0 ) {
                    Toast.makeText(WriteActivity.this, "내용을 입력하세요!", Toast.LENGTH_SHORT).show();
                    content.requestFocus();
                    return;
                }


                //현재시각저장
                long now= System.currentTimeMillis();
                Date date= new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String getDate= sdf.format(date);

                //함수로 전달
                showData(student_id.toString(),title.getText().toString(),club_name.getText().toString(), content.getText().toString(), getDate);

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

    public void showData(String student_id, String title, String club_name, String content, String date){

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
                    String title = (String)params[1];
                    String club_name = (String)params[2];
                    String content = (String)params[3];
                    String date = (String)params[4];


                    String link = "http://52.11.180.128/dbProject/2.php";
                    String data = URLEncoder.encode("student_id","UTF-8")+"="+URLEncoder.encode(student_id,"UTF-8");
                    data += "&" + URLEncoder.encode("title","UTF-8")+"="+URLEncoder.encode(title,"UTF-8");
                    data += "&" + URLEncoder.encode("club_name","UTF-8")+"="+URLEncoder.encode(club_name,"UTF-8");
                    data += "&" + URLEncoder.encode("content","UTF-8")+"="+URLEncoder.encode(content,"UTF-8");
                    data += "&" + URLEncoder.encode("date","UTF-8")+"="+URLEncoder.encode(date,"UTF-8");


                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

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
        task.execute(student_id,title,club_name,content,date);
    }

}
