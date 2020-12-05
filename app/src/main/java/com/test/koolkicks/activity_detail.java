package com.test.koolkicks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class activity_detail extends AppCompatActivity {
        TextView txt_name,txt_price;
        ImageView imag_shoe;
        Button btn_add_cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        txt_name=findViewById(R.id.itemname);
        txt_price=findViewById(R.id.itemprice);
        imag_shoe=findViewById(R.id.itemImage);
       txt_name.setText(getIntent().getExtras().getString("Name","defaultKey"));
        txt_price.setText(getIntent().getExtras().getString("Price","defaultKey") +"$");
        Picasso.get().load(getIntent().getExtras().getString("Pic","defaultKey")).into(imag_shoe);
        btn_add_cart=findViewById(R.id.btn_add_cart);
        btn_add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity_detail.this, activity_my_cart.class);
                intent.putExtra("Id",getIntent().getExtras().getString("Id","defaultKey"));
                intent.putExtra("Name",getIntent().getExtras().getString("Name","defaultKey"));
                intent.putExtra("Price",getIntent().getExtras().getString("Price","defaultKey"));
                intent.putExtra("Pic",getIntent().getExtras().getString("Pic","defaultKey"));
              startActivity(intent);

            }
        });

    }
}