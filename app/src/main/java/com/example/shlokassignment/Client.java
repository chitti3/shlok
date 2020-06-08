package com.example.shlokassignment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    public static Retrofit retrofit;
    public static final String BASE_URL="http://www.json-generator.com/api/";

    public static Retrofit getRetrofit(){
        if(retrofit==null)
        {
            Gson gson=new GsonBuilder().setLenient().create();
            retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }


}
