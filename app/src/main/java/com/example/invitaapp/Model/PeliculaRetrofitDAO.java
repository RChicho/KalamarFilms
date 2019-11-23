package com.example.invitaapp.Model;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculaRetrofitDAO {

    private Retrofit retrofit;
    protected PeliculaService peliculaService;

    public PeliculaRetrofitDAO (String baseUrl){
        retrofit= new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        peliculaService= retrofit.create(PeliculaService.class);
    }

}