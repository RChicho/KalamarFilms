package com.example.invitaapp.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Pelicula implements Serializable {

    public static final String BASE_URL_IMAGEN = "https://image.tmdb.org/t/p/w154/";
    public static final String BASE_URL_IMAGEN_DETALLE = "https://image.tmdb.org/t/p/w300/";

    @SerializedName("title")
    private String titulo;
    @SerializedName("poster_path")
    private String urlImagen;
    @SerializedName("backdrop_path")
    private String urlImagenDetalle;
    @SerializedName("overview")
    private String resumen;
    @SerializedName("release_date")
    private String fechaDeLanzamiento;
    @SerializedName("genres")
    private String genero;
    @SerializedName("id")
    private Integer id;
    

    public Pelicula(String titulo, String urlImagen, String resumen, String fechaDeLanzamiento, String genero, Integer id, String urlImagenDetalle) {

        this.titulo = titulo;
        this.urlImagen = urlImagen;
        this.resumen = resumen;
        this.fechaDeLanzamiento = fechaDeLanzamiento;
        this.genero = genero;
        this.id = id;
        this.urlImagenDetalle = urlImagenDetalle;

    }

    public Pelicula() {
    }

    public String getResumen() {
        return resumen;
    }

    public String getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public String getTitulo() {

        return titulo;
    }

    public Integer getId() {
        return id;
    }

    public String getUrlImagenDetalle() {
        return urlImagenDetalle;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public String generarUrlImagen(){
        return BASE_URL_IMAGEN + urlImagen;
    }

    public String generarUrlImagenDetalle(){
        return BASE_URL_IMAGEN_DETALLE + urlImagenDetalle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(titulo, pelicula.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo);
    }
}