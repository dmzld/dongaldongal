package com.example.dongaldongal;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Unregister extends AppCompatActivity {

    private TextView textView;      //안내문
    private String student_id;
    private Button btnOk;
    private Button btnBack;

    private static final String TAG_Student_id = "student_id";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        student_id = intent.getStringExtra("id");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.unregister);

        textView = (TextView) findViewById(R.id.textView);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showData();
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);               //새로운 intent생성
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);       //이전에 만들어진 intent들 삭제
                startActivityForResult(intent2,2000);

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showData(){

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

                    String link = "http://ec2-13-209-186-123.ap-northeast-2.compute.amazonaws.com/dongaldongal/unregister.php";                  //수정필요
                    String data = URLEncoder.encode("student_id","UTF-8")+"="+URLEncoder.encode(student_id,"UTF-8");


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
        task.execute(student_id);
    }

}
