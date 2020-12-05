package com.test.koolkicks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class shipping extends AppCompatActivity {
    EditText txt_f_name,txt_l_name,txt_aprt_num,txt_street_address,txt_street_name,txt_pin,txt_state,txt_phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_shipping);
        txt_f_name=findViewById(R.id.fname);
        txt_l_name=findViewById(R.id.lname);
        txt_aprt_num=findViewById(R.id.appartment_num);
        txt_street_address=findViewById(R.id.street_address);
        txt_street_name=findViewById(R.id.street_name);
        txt_pin=findViewById(R.id.pincode);
        txt_state=findViewById(R.id.state);
        txt_phone=findViewById(R.id.phone_num);

    }
}