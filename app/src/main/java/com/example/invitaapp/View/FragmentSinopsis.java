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
    private static final String CLAVE_PELICULA = "Clave_Pelicula";


    public FragmentSinopsis() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_sinopsis, container, false);

        textViewSinopsis = vista.findViewById(R.id.textViewFragmentSinopsis);

        Pelicula unaPelicula = recepcionarPelicula();
        textViewSinopsis.setText(unaPelicula.getResumen());


        return vista;
    }

    public static FragmentSinopsis dameUnFragment(Pelicula pelicula) {
        FragmentSinopsis fragmentSinopsis = new FragmentSinopsis();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CLAVE_PELICULA, pelicula);
        fragmentSinopsis.setArguments(bundle);

        return fragmentSinopsis;
    }

    private Pelicula recepcionarPelicula() {
        Bundle bundle = getArguments();
        Pelicula peliculaSeleccionada = (Pelicula) bundle.getSerializable(CLAVE_PELICULA);

        return peliculaSeleccionada;
    }


}
