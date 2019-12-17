package com.learning.SimulMinim2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.learning.SimulMinim2.Retrofit.JsonAPI;
import com.learning.SimulMinim2.Retrofit.Models.Museums;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerList;
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

        /** Ahora vamos a hacer que la lista tenga un tipo de layout lineal, poniendo solo el this, por defecto la lisa sería vertical. Aún así, lo vamos especificar. */
        recyclerList.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        loadData();
    }

    private void loadData(){
        Call<Museums> call = jsonData.getData("1", "15");
        call.enqueue(new Callback<Museums>() {
            @Override
            public void onResponse(Call<Museums> call, Response<Museums> response) {
                if (response.isSuccessful()) {
                    Museums museums = response.body();
                    recyclerList.setAdapter(new DataAdapter(museums.getElements()));
                }
            }

            @Override
            public void onFailure(Call<Museums> call, Throwable t) {

            }
        });


    }

}
