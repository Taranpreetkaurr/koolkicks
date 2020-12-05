package com.test.koolkicks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.util.HashMap;

public class login extends AppCompatActivity {
    EditText txt_email,txt_pass;
    Button btn_login;
    TextView txt_forget,txt_signup;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        auth=FirebaseAuth.getInstance();
        txt_email=findViewById(R.id.txt_email);
        txt_pass=findViewById(R.id.txt_password);
        btn_login=findViewById(R.id.btn_signin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txt_email.getText().toString();
                final String password = txt_pass.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter your mail address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }
                    //authenticate user
                new login.Async_Worker().execute("login",email,password);
            }




        });
        txt_signup=findViewById(R.id.sign_up);
        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,signup.class));
            }
        });
    }


    public class Async_Worker extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Helper.showLoader(login.this,"Please wait . . . ");
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                HashMap<String,String> hashMap_params = new HashMap<>();
                hashMap_params.put("email", txt_email.getText().toString());
                hashMap_params.put("password", txt_pass.getText().toString());
                return Helper.post_request(params[0],hashMap_params);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            Helper.stopLoader();
            try {

                //   Toast.makeText(activity_login.this,response,Toast.LENGTH_LONG).show();
                if(response.contains("Success")){
                    startActivity(new Intent(login.this,MainActivity.class));
                }


            } catch (Exception e ) {
                e.printStackTrace();
                Toast.makeText(login.this,e.toString(),Toast.LENGTH_LONG).show();
            }


        }
    }

}