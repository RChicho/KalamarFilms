package com.example.invitaapp.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.R;


public class FragmentSinopsis extends Fragment {

    private TextView textViewSinopsis;
    private Pelicula pelicula;


    public FragmentSinopsis(Pelicula pelicula) {
        // Required empty public constructor
        this.pelicula = pelicula;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_sinopsis, container, false);

        textViewSinopsis = vista.findViewById(R.id.textViewFragmentSinopsis);

        textViewSinopsis.setText(pelicula.getResumen());


        return vista;
    }



}
