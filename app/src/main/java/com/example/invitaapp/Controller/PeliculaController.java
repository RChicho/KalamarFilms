package com.example.invitaapp.Controller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.invitaapp.Model.Actor;
import com.example.invitaapp.Model.ActorAdapter;
import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.Model.PeliculaDao;
import com.example.invitaapp.Utils.ResultListener;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PeliculaController {


    public void traerPeliculas(final ResultListener<List<Pelicula>> listenerDeLaVista ){

        PeliculaDao peliculaDao = new PeliculaDao();

        peliculaDao.traerListaTrending(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> result) {

                listenerDeLaVista.finish(result);
            }
        });
    }

    public void traerPeliculas2(final ResultListener<List<Pelicula>> listenerDeLaVista ){

        PeliculaDao peliculaDao = new PeliculaDao();

        peliculaDao.traerListaNowPlaying(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> result) {

                listenerDeLaVista.finish(result);
            }
        });
    }

    public void traerPeliculas3(final ResultListener<List<Pelicula>> listenerDeLaVista ){

        PeliculaDao peliculaDao = new PeliculaDao();

        peliculaDao.traerListaTopRated(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> result) {

                listenerDeLaVista.finish(result);
            }
        });
    }
    public void traerActores(Integer idMovie, final ResultListener<List<Actor>> listenerDeLaVista){
        PeliculaDao peliculaDao = new PeliculaDao();
        peliculaDao.traerActores(idMovie, new ResultListener<List<Actor>>() {
            @Override
            public void finish(List<Actor> result) {
                listenerDeLaVista.finish(result); {

                    }

            }
        });


    }




}
