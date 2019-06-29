package com.nue.cekfilmbagus.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("3/movie/{category}")
    Call<MovieResult> ListOfMovie(
            @Path("category")String category,
            @Query("api_key")String api_key,
            @Query("language")String language,
            @Query("page")int page
    );





}
