package com.learning.SimulMinim2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ProgressBar;

import com.learning.SimulMinim2.Retrofit.JsonAPI;
import com.learning.SimulMinim2.Retrofit.Models.Museums;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerList;
    private ProgressBar progressBar;
    private JsonAPI jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://do.diba.cat/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonData =  retrofit.create(JsonAPI.class);

        recyclerList = findViewById(R.id.myRecycler); /** Declarem la referència per al recyclerList View */
        progressBar = findViewById(R.id.circProgressBar); /** Declarem la referència per la progress bar */

        /** Ahora vamos a hacer que la lista tenga un tipo de layout lineal, poniendo solo el this, por defecto la lisa sería vertical. Aún así, lo vamos especificar. */
        recyclerList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        new CountDownTimer(1000,1000) { /** Timer de compte enrere per forçar la progress bar 1 segon */
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() {
                loadData();
            }
        }.start();
    }

    private void loadData(){
        Call<Museums> call = jsonData.getData("1", "15");
        call.enqueue(new Callback<Museums>() {
            @Override
            public void onResponse(Call<Museums> call, Response<Museums> response) {
                if (response.isSuccessful()) {
                    Museums museums = response.body();
                    recyclerList.setAdapter(new DataAdapter(museums.getElements()));
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<Museums> call, Throwable t) {

            }
        });
    }

}
