package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.myapplication.adapters.OrderDetailsAdapter;
import com.example.myapplication.interfaces.ApiInterface;
import com.example.myapplication.models.Datum;
import com.example.myapplication.models.OrderDetailsObject;
import com.example.myapplication.models.OrderProduct;
import com.example.myapplication.utils.ApiClient;
import com.example.myapplication.utils.CustomItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private RecyclerView rvOrderDetails;
private List<Datum> datumList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvOrderDetails = findViewById(R.id.rv_order_details);
        rvOrderDetails.setNestedScrollingEnabled(false);
        rvOrderDetails.setHasFixedSize(true);

        ApiInterface apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
        Call<OrderDetailsObject> detailsObjectCall = apiInterface.getorderDetails();
        detailsObjectCall.enqueue(new Callback<OrderDetailsObject>() {
            @Override
            public void onResponse(Call<OrderDetailsObject> call, Response<OrderDetailsObject> response) {
                if (response.code()==200){
                    datumList = new ArrayList<>();
                    datumList = response.body().getData();

                    rvOrderDetails.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
                   OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(MainActivity.this,datumList);
                    rvOrderDetails.setAdapter(orderDetailsAdapter);
                    Log.i("responxsecje","hdkfjkd"+response.body().getData().get(0).getGrandtotal());
                }
            }

            @Override
            public void onFailure(Call<OrderDetailsObject> call, Throwable t) {

            }
        });
    }
}
