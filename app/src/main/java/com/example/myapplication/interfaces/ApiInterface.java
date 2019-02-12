package com.example.myapplication.interfaces;

import com.example.myapplication.models.OrderDetailsObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("myOrders")
    Call<OrderDetailsObject> getorderDetails();

}
