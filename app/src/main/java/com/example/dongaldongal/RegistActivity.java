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
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class RegistActivity extends AppCompatActivity {

    private EditText etStudentID;
    private EditText etName;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private EditText etMajor;
    private EditText etSex;
    private EditText etPhonenum;

    private Button btnDone;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        etStudentID = (EditText) findViewById(R.id.student_id);
        etName= (EditText) findViewById(R.id.name);
        etName.setPrivateImeOptions("defaultInputmode=korea;");
        etPassword = (EditText) findViewById(R.id.password);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
        etMajor=(EditText) findViewById(R.id.major);
        etSex=(EditText) findViewById(R.id.sex);
        etPhonenum=(EditText) findViewById(R.id.phonenum);

        btnDone = (Button) findViewById(R.id.btnDone);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        // 비밀번호 일치 검사
        etPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = etPassword.getText().toString();
                String confirm = etPasswordConfirm.getText().toString();

                if( password.equals(confirm) ) {
                    etPassword.setBackgroundColor(Color.GREEN);
                    etPasswordConfirm.setBackgroundColor(Color.GREEN);
                } else {
                    etPassword.setBackgroundColor(Color.RED);
                    etPasswordConfirm.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 학번 입력 확인
                if(etStudentID.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "학번을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etStudentID.requestFocus();
                    return;
                }

                //이름
                if(etName.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etName.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if( etPassword.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                if( etPasswordConfirm.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "비밀번호 확인을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if( !etPassword.getText().toString().equals(etPasswordConfirm.getText().toString()) ) {
                    Toast.makeText(RegistActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    etPassword.requestFocus();
                    return;
                }

                //전공
                if(etMajor.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "전공을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etMajor.requestFocus();
                    return;
                }

                //성별
                if(etSex.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "성별을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etSex.requestFocus();
                    return;
                }

                //핸드폰 번호
                if(etPhonenum.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity.this, "핸드폰번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPhonenum.requestFocus();
                    return;
                }

                //함수로 전달
                showData(etStudentID.getText().toString(), etName.getText().toString(), etPassword.getText().toString(), etMajor.getText().toString(),etSex.getText().toString(), etPhonenum.getText().toString());

                Intent result = new Intent();
                result.putExtra("StudentID", etStudentID.getText().toString());

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

    public void showData(String student_id0, String name0, String pwd0, String major0, String sex0, String phonenum0){

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
                    String name= (String)params[1];
                    String pwd = (String)params[2];
                    String major = (String)params[3];
                    String sex = (String)params[4];
                    String phonenum = (String)params[5];

                    String link = "http://52.11.180.128/dbProject/2.php";
                    String data = URLEncoder.encode("student_id","UTF-8")+"="+URLEncoder.encode(student_id,"UTF-8");
                    data += "&" + URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");
                    data += "&" + URLEncoder.encode("pwd","UTF-8")+"="+URLEncoder.encode(pwd,"UTF-8");
                    data += "&" + URLEncoder.encode("major","UTF-8")+"="+URLEncoder.encode(major,"UTF-8");
                    data += "&" + URLEncoder.encode("sex","UTF-8")+"="+URLEncoder.encode(sex,"UTF-8");
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
        task.execute(student_id0,name0,pwd0,major0,sex0,phonenum0);
    }

}

