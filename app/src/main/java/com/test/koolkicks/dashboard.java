package com.test.koolkicks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Shoes> list;
    dashboard_viewholder adapter;
    Shoes _shoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        list=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(dashboard.this, 2));
        new dashboard.Async_Worker().execute("list");
    }




    public class Async_Worker extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Helper.showLoader(dashboard.this,"Please wait . . . ");
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                return Helper.get_request(params[0]);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);

            list=new ArrayList<>();
            Helper.stopLoader();
            list.clear();


            if(response!=null)
            {
                try
                {

                    JSONObject object = new JSONObject(response);
                    JSONArray data  = object.getJSONArray("results");
                    for(int index = 0; index < data.length(); index++)
                    {
                        JSONObject obj = data.getJSONObject(index);
                        Shoes info = new Shoes(obj);

                        list.add(info);
                    }

                    if(list.size() > 0) {
                        adapter = new dashboard_viewholder(dashboard.this, list);
                        recyclerView.setAdapter(adapter);




                    }
                    else {

                    }

                }
                catch (Exception e){
                    Toast.makeText(dashboard.this, e.toString(), Toast.LENGTH_LONG).show();

                }
            }
            else{

            }


        }
    }


}