package com.example.sauldelgado.klavadoapp.Data.Remoto.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkAdapter {
    private static NetworkService API_SERVICE;
    public static NetworkService getApiService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://192.168.7.156:80/Klavado/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        API_SERVICE = retrofit.create(NetworkService.class);
        return API_SERVICE;
    }
}
