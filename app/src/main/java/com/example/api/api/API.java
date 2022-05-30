package com.example.api.api;

import com.example.api.Suser;

import io.reactivex.rxjava3.core.Observable;

import retrofit2.http.GET;

public interface API {
    @GET("countryInfoJSON?username=caoth")
    Observable<Suser> getdata();
}
