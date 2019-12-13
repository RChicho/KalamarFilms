package com.example.invitaapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContainerDeActores {

    @SerializedName("cast")
    private List<Actor>actoresList;


    public List<Actor> getActoresList() {
        return actoresList;
    }
}
