package com.test.koolkicks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

import java.util.HashMap;

public class signup extends AppCompatActivity {
    EditText txt_email,txt_pass,txt_confirm_pass;
    Button btn_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        txt_email=findViewById(R.id.email);
        txt_pass=findViewById(R.id.pass);
        txt_confirm_pass=findViewById(R.id.cpass);
        btn_signup=findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txt_email.getText().toString();
                String pass = txt_pass.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please enter your E-mail address",Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    Toast.makeText(getApplicationContext(),"Please enter your Password",Toast.LENGTH_LONG).show();
                }
                if (pass.length() == 0){
                    Toast.makeText(getApplicationContext(),"Please enter your Password",Toast.LENGTH_LONG).show();
                }
                if (pass.length()<5){
                    Toast.makeText(getApplicationContext(),"Password must be more than 5 digit",Toast.LENGTH_LONG).show();
                }
                else {
                    new signup.Async_Worker().execute("signup",email,pass);

                }
            }


        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public class Async_Worker extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Helper.showLoader(signup.this,"Please wait . . . ");
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
                    startActivity(new Intent(signup.this,MainActivity.class));
                }


            } catch (Exception e ) {
                e.printStackTrace();
                Toast.makeText(signup.this,e.toString(),Toast.LENGTH_LONG).show();
            }


        }
    }
    public  void refresh(){
        txt_email.setText("");
        txt_pass.setText("");
        txt_confirm_pass.setText("");


    }
}