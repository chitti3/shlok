package com.example.shlokassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
   RecyclerView recyclerView;
    Details details=new Details();
    Sqlhelper sqlhelper;
    String[] data ;
   Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         sqlhelper=new Sqlhelper(this);
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            apicall();
        }
        else
            connected = false;
        Cursor c = sqlhelper.fetch();

        if (c != null) {
            while(c.moveToNext()) {
                data = new String[4]; // Note this addition
                data[0] = Integer.toString(c.getInt(0));
                data[1] = c.getString(1);
                data[2] = Integer.toString(c.getInt(2));
                data[3] = Integer.toString(c.getInt(3));
                Log.e("ccvrrrfe", data[1]);
            }
            c.close();
        }




recyclerView=findViewById(R.id.recyler);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void apicall() {
ApiInterface apiInterface =Client.getRetrofit().create(ApiInterface.class);
Call<Fetch> call=apiInterface.fetch();
call.enqueue(new Callback<Fetch>() {
    @Override
    public void onResponse(Call<Fetch> call, Response<Fetch> response) {
      String data=response.body().getGetInspectionsResult();
      String value[]=data.split(",");
      List<String> al=new ArrayList<>();
    al= Arrays.asList(value);
    int count=response.body().getRecordCount();
  adapter=new Adapter(al,count);
      recyclerView.setAdapter(adapter);
        for (int i=7;i<al.size();i=i+27)
        {
            sqlhelper.add(al.get(i),al.get(i+1),al.get(i+2),al.get(i+4));
            Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(Call<Fetch> call, Throwable t) {

        Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
    }


});

    }


}
