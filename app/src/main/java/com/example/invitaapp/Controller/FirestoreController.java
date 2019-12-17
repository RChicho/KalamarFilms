package com.example.invitaapp.Controller;

import com.example.invitaapp.Model.FirestoreDao;
import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.Utils.ResultListener;

import java.util.List;

public class FirestoreController {


    private FirestoreDao firestoreDao;

    public FirestoreController() {
        firestoreDao = new FirestoreDao();
    }

    public void agregarPeliculaAFav(Pelicula pelicula){
        firestoreDao.agregarPeliculaAFav(pelicula);
    }

    //traigo las peliculas y se las paso a la vista
    public void traerListaDeFavorito(final ResultListener<List<Pelicula>> listenerDeLaVista){
        firestoreDao.traerPeliculasFavoritas(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> result) {
                listenerDeLaVista.finish(result);
            }
        });
    }




}
