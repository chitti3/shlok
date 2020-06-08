package com.example.shlokassignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("json/get/bVZYphlszm?indent=2")
    Call<Fetch> fetch();
}
