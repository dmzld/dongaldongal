package com.example.dongaldongal;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ChangePwd extends AppCompatActivity {

    private TextView textView;      //안내문
    private EditText pwd_confirm;
    private EditText pwd_confirm2;      //pwd_confirm과 비교해서 비밀번호 확인
    private String student_id;
    private Button btnOk;
    private Button btnBack;

    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_Student_id = "student_id";
    JSONArray peoples = null;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        student_id = intent.getStringExtra("id");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pwd);

        textView = (TextView) findViewById(R.id.textView);
        pwd_confirm = (EditText) findViewById(R.id.pwd_confirm);
        pwd_confirm2 = (EditText) findViewById(R.id.pwd_confirm2);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnBack = (Button) findViewById(R.id.btnBack);

        // 비밀번호 일치 검사하는 함수
        pwd_confirm2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = pwd_confirm.getText().toString();
                String confirm = pwd_confirm2.getText().toString();

                if (password.equals(confirm)) {
                    pwd_confirm.setBackgroundColor(Color.GREEN);
                    pwd_confirm2.setBackgroundColor(Color.GREEN);
                } else {
                    pwd_confirm.setBackgroundColor(Color.RED);
                    pwd_confirm2.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 비밀번호 입력 확인
                if( pwd_confirm.getText().toString().length() == 0 ) {
                    Toast.makeText(ChangePwd.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    pwd_confirm.requestFocus();
                    return;
                }
                if( pwd_confirm2.getText().toString().length() == 0 ) {
                    Toast.makeText(ChangePwd.this, "비밀번호 재확인란을 입력하세요!", Toast.LENGTH_SHORT).show();
                    pwd_confirm2.requestFocus();
                    return;
                }
                if( !pwd_confirm.getText().toString().equals(pwd_confirm2.getText().toString()) ) {
                    Toast.makeText(ChangePwd.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    pwd_confirm.setText("");
                    pwd_confirm2.setText("");
                    pwd_confirm.requestFocus();
                    return;
                }
                //함수로 전달
                showData(pwd_confirm.getText().toString());
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showData(String pwd){

        class InsetData extends AsyncTask<String, Void, String>{

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
                    String pwd= (String)params[1];

                    String link = "http://52.11.180.128/dbProject/change_pwd.php";
                    String data = URLEncoder.encode("student_id","UTF-8")+"="+URLEncoder.encode(student_id,"UTF-8");
                    data += "&" + URLEncoder.encode("pwd","UTF-8")+"="+URLEncoder.encode(pwd,"UTF-8");


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
        task.execute(student_id,pwd);
    }



}
