 package com.test.koolkicks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class activity_my_cart extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView txt_name;
    ImageView imag_shoe;
    Button btn_checkout;
    String[] quantity = { "1", "2", "3", "4", "5"};
    String[] size = { "9", "10", "11"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        btn_checkout=findViewById(R.id.btn_checkout);
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(activity_my_cart.this,shipping.class));
            }
        });
        txt_name=findViewById(R.id.itemname);
        imag_shoe=findViewById(R.id.itemImage);
        txt_name.setText(getIntent().getExtras().getString("Name","defaultKey"));
        Picasso.get().load(getIntent().getExtras().getString("Pic","defaultKey")).into(imag_shoe);
        Spinner spin_quantity = (Spinner) findViewById(R.id.spinner_quantity);
        spin_quantity.setOnItemSelectedListener(this);
        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,quantity);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_quantity.setAdapter(aa1);

        Spinner spin_size = (Spinner) findViewById(R.id.spinner_size);
        spin_size.setOnItemSelectedListener(this);
        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,size);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_size.setAdapter(aa2);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}