package com.example.invitaapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContainerDeTrailers {

    @SerializedName("results")
    private List<Trailer> peliculaList;

    public List<Trailer> getPeliculaList(){
        return peliculaList;}

    public void setPeliculaList (List<Trailer> peliculaList) {
        this.peliculaList = peliculaList;}


}
