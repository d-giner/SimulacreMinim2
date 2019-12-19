package com.learning.SimulMinim2.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import com.learning.SimulMinim2.Retrofit.Models.*;

public interface JsonAPI {

    @GET("dataset/museus/format/json/pag-ini/{pagIni}/pag-fi/{pagFi}")
    Call<Museums> getData(@Path("pagIni") String pagIni,@Path("pagFi") String pagFi);
}
