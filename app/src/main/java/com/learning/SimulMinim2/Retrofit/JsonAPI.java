package com.learning.SimulMinim2.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import com.learning.SimulMinim2.Retrofit.Models.*;

public interface JsonAPI {

    @GET("dataset/museus/format/json/pag-ini/1/pag-fi/1")
    Call<Museums> getData();
}
