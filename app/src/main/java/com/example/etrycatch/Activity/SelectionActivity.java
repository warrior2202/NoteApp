package com.example.etrycatch.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.etrycatch.R;
import com.example.etrycatch.Rest.ApiClient;
import com.example.etrycatch.Rest.ApiInterface;
import com.example.etrycatch.models.StreamPogo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectionActivity extends AppCompatActivity {
    public static List<StreamPogo> mainPgoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        CardView scince = findViewById(R.id.Science);
        CardView commerce  = findViewById(R.id.Commerce);
        scince.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<List<StreamPogo>> call = apiService.getstream("1");
                call.enqueue(new Callback<List<StreamPogo>>() {
                    @Override
                    public void onResponse(Call<List<StreamPogo>> call, Response<List<StreamPogo>> response) {
                        mainPgoList = response.body();
                        mainPgoList = response.body();
                        Intent in = new Intent(SelectionActivity.this,MainActivity.class);

                        startActivity(in);

                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    }

                    @Override
                    public void onFailure(Call<List<StreamPogo>> call, Throwable t) {

                    }
                });


            }
        });


        commerce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<List<StreamPogo>> call = apiService.getstream("0");
                call.enqueue(new Callback<List<StreamPogo>>() {
                    @Override
                    public void onResponse(Call<List<StreamPogo>> call, Response<List<StreamPogo>> response) {
                        mainPgoList = response.body();
                        mainPgoList = response.body();
                        Intent in = new Intent(SelectionActivity.this,MainActivity.class);

                        startActivity(in);

                        overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                    }

                    @Override
                    public void onFailure(Call<List<StreamPogo>> call, Throwable t) {

                    }
                });

            }
        });
    }
}