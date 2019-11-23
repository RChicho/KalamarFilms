package com.example.invitaapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Actor implements Serializable {
    public static final String BASE_URL_IMAGEN_DETALLE = "https://image.tmdb.org/t/p/w300/";

    @SerializedName("character")
    private String nombreDelPersonaje;
    @SerializedName("name")
    private String nombreDelActor;
    @SerializedName("profile_path")
    private String urlImagenActor;


    public Actor(String nombreDelPersonaje, String nombreDelActor, String urlImagenActor) {
        this.nombreDelPersonaje = nombreDelPersonaje;
        this.nombreDelActor = nombreDelActor;
        this.urlImagenActor = urlImagenActor;
    }

    public static String getBaseUrlImagenDetalle() {
        return BASE_URL_IMAGEN_DETALLE;
    }

    public String getNombreDelPersonaje() {
        return nombreDelPersonaje;
    }

    public String getNombreDelActor() {
        return nombreDelActor;
    }

    public String getUrlImagenActor() {
        return BASE_URL_IMAGEN_DETALLE + urlImagenActor;
    }


}
