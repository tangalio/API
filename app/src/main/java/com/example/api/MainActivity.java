package com.example.api;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.api.adapter.GeonamesAdapter;
import com.example.api.api.API;
import com.example.api.api.RetrofitClient;
import com.example.api.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    API api;
    ArrayList<Geonames> geonamesList;
    GeonamesAdapter geonamesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        api = RetrofitClient.getInstance(Utils.BASE_URL).create(API.class);
        Anhxa();
        getdata();
    }

    private void getdata() {
        compositeDisposable.add(api.getdata()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        suser ->{
                            String g = new Gson().toJson(suser);
                            Suser suser1 = new Gson().fromJson(g,Suser.class);
                            ArrayList<Geonames> geonames = (ArrayList<Geonames>) suser1.getGeonames();
                            geonamesAdapter = new GeonamesAdapter(getApplicationContext(),geonames);
                            recyclerView.setAdapter(geonamesAdapter);
                        }, throwable -> {
//                            Log.d("AAA",throwable.getMessage());
                            Toast.makeText(getApplicationContext(),"Không kết nối được với sever" + throwable.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void Anhxa() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_users);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setAdapter(geonamesAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}