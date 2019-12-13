package com.example.invitaapp.Model;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.example.invitaapp.Utils.ResultListener;

import java.util.ArrayList;
import java.util.List;

public class PeliculaDao extends PeliculaRetrofitDAO {
    private static final String BASE_URL_API = "https://api.themoviedb.org";
    private static final String BASE_URL_IMG = "https://image.tmdb.org/t/p/w200/";
    private static final String API_KEY = "25ac4313f0c6a0579d228b6da85f955f";

    public PeliculaDao() {
        super(BASE_URL_API);
    }


    public void traerListaTrending(final ResultListener<List<Pelicula>> listenerDelControler) {

        Call<ContainerPeliculas> call = peliculaService.traerListaDePeliculasTrending(API_KEY);

        call.enqueue(new Callback<ContainerPeliculas>() {
            @Override
            public void onResponse(Call<ContainerPeliculas> call, Response<ContainerPeliculas> response) {
                ContainerPeliculas containerPeliculas = response.body();
                listenerDelControler.finish(containerPeliculas.getPeliculaList());
            }

            @Override
            public void onFailure(Call<ContainerPeliculas> call, Throwable t) {

            }
        });
    }


    public void traerListaNowPlaying(final ResultListener<List<Pelicula>> listenerDelControler) {

        Call<ContainerPeliculas> call = peliculaService.traerListaDePeliculasNowPlaying();

        call.enqueue(new Callback<ContainerPeliculas>() {
            @Override
            public void onResponse(Call<ContainerPeliculas> call, Response<ContainerPeliculas> response) {
                ContainerPeliculas containerPeliculas = response.body();
                listenerDelControler.finish(containerPeliculas.getPeliculaList());
            }

            @Override
            public void onFailure(Call<ContainerPeliculas> call, Throwable t) {

            }
        });
    }

    public void traerActores(Integer idPelicula, final ResultListener<List<Actor>> listenerDelControler) {
        Call<ContainerDeActores> call = peliculaService.traerListaDeActores(idPelicula);
        call.enqueue(new Callback<ContainerDeActores>() {
            @Override
            public void onResponse(Call<ContainerDeActores> call, Response<ContainerDeActores> response) {
                ContainerDeActores containerDeActores = response.body();
                listenerDelControler.finish(containerDeActores.getActoresList());
            }

            @Override
            public void onFailure(Call<ContainerDeActores> call, Throwable t) {
                Log.d("ddd", "");

            }
        });
    }

    public void traerListaTopRated(final ResultListener<List<Pelicula>> listenerDelControler) {

        Call<ContainerPeliculas> call = peliculaService.traerListaDeTopRated();

        call.enqueue(new Callback<ContainerPeliculas>() {
            @Override
            public void onResponse(Call<ContainerPeliculas> call, Response<ContainerPeliculas> response) {
                ContainerPeliculas containerPeliculas = response.body();
                listenerDelControler.finish(containerPeliculas.getPeliculaList());

            }

            @Override
            public void onFailure(Call<ContainerPeliculas> call, Throwable t) {

            }
        });
    }

    public void traerListaPeliculasSimilares(Integer idPelicula, final ResultListener<List<Pelicula>> listenerDelControler) {
        Call<ContainerPeliculas> call = peliculaService.traerListaPeliculasSimilares(idPelicula);
        call.enqueue(new Callback<ContainerPeliculas>() {
            @Override
            public void onResponse(Call<ContainerPeliculas> call, Response<ContainerPeliculas> response) {
                ContainerPeliculas containerPeliculas = response.body();
                listenerDelControler.finish(containerPeliculas.getPeliculaList());
            }

            @Override
            public void onFailure(Call<ContainerPeliculas> call, Throwable t) {
                Log.d("ddd", "");
            }
        });
    }

public void traerPeliculasTrailer(Integer idPelicula, final ResultListener<List<Trailer>> listenerDelControler){
         Call<ContainerDeTrailers> call = peliculaService.traerTrailerPelicula(idPelicula);
         call.enqueue(new Callback<ContainerDeTrailers>() {
             @Override
             public void onResponse(Call<ContainerDeTrailers> call, Response<ContainerDeTrailers> response) {
                 ContainerDeTrailers containerDeTrailers = response.body();
                 listenerDelControler.finish(containerDeTrailers.getPeliculaList());
             }

             @Override
             public void onFailure(Call<ContainerDeTrailers> call, Throwable t) {

             }
         });
}
}
