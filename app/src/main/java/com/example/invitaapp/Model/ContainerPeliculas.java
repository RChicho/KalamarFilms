package com.example.invitaapp.Model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ContainerPeliculas {

    @SerializedName("results")
    private List<Pelicula> peliculaList;

    public List<Pelicula> getPeliculaList(){
        return peliculaList;}

    public void setPeliculaList (List<Pelicula> peliculaList) {
        this.peliculaList = peliculaList;}

    @SerializedName("poster_path")
    private String urlImagen;


    public String getUrlImagen() {return urlImagen;}

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public ContainerPeliculas() {
        peliculaList = new ArrayList<>();
    }


    public void agregarPelicula(Pelicula pelicula){
        peliculaList.add(pelicula);
    }
    public void removerPelicula(Pelicula pelicula){
        peliculaList.remove(pelicula);
    }

    public Boolean contieneLaPelicula(Pelicula pelicula){
        return peliculaList.contains(pelicula);
    }
}
