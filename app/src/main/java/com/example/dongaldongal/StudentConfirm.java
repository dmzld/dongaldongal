package com.example.dongaldongal;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.net.HttpURLConnection;
import java.net.URL;

public class StudentConfirm extends AppCompatActivity {             //회원정보 수정시 비밀번호 입력을 통해 사용자 확인하는작업

    private String student_id;
    private String select;      //비밀번호 수정 or 휴대폰번호 수정 or 회원탈퇴 버튼을 눌러서 들어왔는지 구분짓기 위해
    private TextView textView;
    private EditText password;
    private Button btnOk;
    private Button btnBack;

    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_Student_id = "student_id";
    private static final String TAG_pwd = "pwd";
    JSONArray peoples = null;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        student_id = intent.getStringExtra("id");
        select = intent.getStringExtra("select");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_confirm);

        textView = (TextView) findViewById(R.id.textView);
        password = (EditText) findViewById(R.id.password);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 비밀번호 입력 확인
                if( password.getText().toString().length() == 0 ) {
                    Toast.makeText(StudentConfirm.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                    return;
                }

                getData("http://52.11.180.128/dbProject/1.php");
            }
        });
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_Student_id);
                String pwd = c.getString(TAG_pwd);

                if((id.equals(student_id))&&(pwd.equals(password.getText().toString())))
                {flag = 1; break;}
            }

            Log.i("finish","finish");

            if(flag == 1&& select.toString().equals("1"))
            {
                flag = 0;
                Intent intent = new Intent(getApplicationContext(), ChangePwd.class);
                //사용자 정보 넘김
                intent.putExtra("id",student_id);
                startActivityForResult(intent,2000);
                finish();
            }
            else if(flag == 1&& select.toString().equals("2"))
            {
                flag = 0;
                Intent intent = new Intent(getApplicationContext(), ChangePhonenum.class);                  //class수정필요(휴대폰번호 바꿀 class)
                //사용자 정보 넘김
                intent.putExtra("id",student_id);
                startActivityForResult(intent,2000);
                finish();
            }
            else if(flag == 1&& select.toString().equals("3"))
            {
                flag = 0;
                Intent intent = new Intent(getApplicationContext(), Unregister.class);                  //class수정필요(회원탈퇴 처리class)
                //사용자 정보 넘김
                intent.putExtra("id",student_id);
                startActivityForResult(intent,2000);
                finish();
            }
            else
            {
                Toast.makeText(StudentConfirm.this, "잘못입력했습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            return ;

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
