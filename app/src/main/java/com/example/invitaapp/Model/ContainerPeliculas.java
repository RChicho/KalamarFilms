package com.example.invitaapp.Model;
import com.google.gson.annotations.SerializedName;

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


}
