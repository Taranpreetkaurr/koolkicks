package com.test.koolkicks;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class dashboard_viewholder extends RecyclerView.Adapter<dashboard_viewholder.contacts>{

    Context context;
    ArrayList <Shoes> _books;
    public  static  String ph;
    public  static  String id;

    public dashboard_viewholder(Context c , ArrayList<Shoes> b)
    {
        context = c;
        _books = b;
    }


    @NonNull
    @Override
    public contacts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new contacts(LayoutInflater.from(context).inflate(R.layout.homepage_card_layout,parent, false));
    }



    @Override
    public void onBindViewHolder(@NonNull final contacts holder, final int position) {
        holder.Id.setText(_books.get(position).get_id());
        holder.Name.setText(_books.get(position).get_shoe_name());
        holder.Price.setText(_books.get(position).get_price()+"$");
        Picasso.get()
                .load(_books.get(position).get_link())
                .into(holder.Pic);
        Log.d("Pic Url",_books.get(position).get_link());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent intent = new Intent(context, activity_detail.class);
                intent.putExtra("Id",_books.get(position).get_id().toString());
                intent.putExtra("Name",_books.get(position).get_shoe_name().toString());
                intent.putExtra("Price",_books.get(position).get_price().toString());
                intent.putExtra("Pic",_books.get(position).get_link().toString());
                context.startActivity(intent);



            }
        });






    }

    @Override
    public int getItemCount() {
        return _books.size();
    }







    class contacts extends RecyclerView.ViewHolder{
        TextView Id;
        TextView Name;
        TextView Price;
        ImageView Pic;

        CardView cardView;

        public contacts(View itemView){
            super(itemView);
            Id  = (TextView) itemView.findViewById(R.id._id);
            Name  = (TextView) itemView.findViewById(R.id._name);
            Price  = (TextView) itemView.findViewById(R.id._price);
            Pic = (ImageView) itemView.findViewById(R.id._img);
            cardView = (CardView)itemView.findViewById(R.id.cardview);
        }
    }


    public long Daybetween(String date1,String date2,String pattern)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date Date1 = null,Date2 = null;
        try{
            Date1 = sdf.parse(date1);
            Date2 = sdf.parse(date2);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return (Date2.getTime() - Date1.getTime())/(24*60*60*1000);
    }





}
