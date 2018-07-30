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

public class ChangePhonenum extends AppCompatActivity {

    private TextView textView;      //안내문
    private EditText phonenum_confirm;
    private EditText phonenum_confirm2;      //pwd_confirm과 비교해서 비밀번호 확인
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
        setContentView(R.layout.change_phonenum);

        textView = (TextView) findViewById(R.id.textView);
        phonenum_confirm = (EditText) findViewById(R.id.phonenum_confirm);
        phonenum_confirm2 = (EditText) findViewById(R.id.phonenum_confirm2);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnBack = (Button) findViewById(R.id.btnBack);

        // 비밀번호 일치 검사하는 함수
        phonenum_confirm2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String pnum = phonenum_confirm.getText().toString();
                String confirm = phonenum_confirm2.getText().toString();

                if (pnum.equals(confirm)) {
                    phonenum_confirm.setBackgroundColor(Color.GREEN);
                    phonenum_confirm2.setBackgroundColor(Color.GREEN);
                } else {
                    phonenum_confirm.setBackgroundColor(Color.RED);
                    phonenum_confirm2.setBackgroundColor(Color.RED);
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
                if( phonenum_confirm.getText().toString().length() == 0 ) {
                    Toast.makeText(ChangePhonenum.this, "전화번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    phonenum_confirm.requestFocus();
                    return;
                }
                if( phonenum_confirm2.getText().toString().length() == 0 ) {
                    Toast.makeText(ChangePhonenum.this, "전화번호 재확인란을 입력하세요!", Toast.LENGTH_SHORT).show();
                    phonenum_confirm2.requestFocus();
                    return;
                }
                if( !phonenum_confirm.getText().toString().equals(phonenum_confirm2.getText().toString()) ) {
                    Toast.makeText(ChangePhonenum.this, "전화번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    phonenum_confirm.setText("");
                    phonenum_confirm2.setText("");
                    phonenum_confirm.requestFocus();
                    return;
                }
                //함수로 전달
                showData(phonenum_confirm.getText().toString());
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

    public void showData(String phonenum){

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
                    String phonenum= (String)params[1];

                    String link = "http://52.11.180.128/dbProject/change_phonenum.php";                  //수정필요
                    String data = URLEncoder.encode("student_id","UTF-8")+"="+URLEncoder.encode(student_id,"UTF-8");
                    data += "&" + URLEncoder.encode("phonenum","UTF-8")+"="+URLEncoder.encode(phonenum,"UTF-8");


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
        task.execute(student_id,phonenum);
    }



}
