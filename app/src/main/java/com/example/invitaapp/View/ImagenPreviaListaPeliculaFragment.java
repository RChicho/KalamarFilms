package com.example.invitaapp.View;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.invitaapp.Controller.PeliculaController;
import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.Model.Trailer;
import com.example.invitaapp.R;
import com.example.invitaapp.Utils.ResultListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ImagenPreviaListaPeliculaFragment extends Fragment  {

    public static final String CLAVE_PELICULA = "claveImagen";
    private List<Fragment> listaPeliculas;
    private ImageView imageViewPelicula;
    private FloatingActionButton floatingActionButton;


    public ImagenPreviaListaPeliculaFragment() {
        // Required empty public constructor
    }


    public static ImagenPreviaListaPeliculaFragment dameUnFragment(Pelicula pelicula){

        ImagenPreviaListaPeliculaFragment imagenPreviaListaPeliculaFragment = new ImagenPreviaListaPeliculaFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable(CLAVE_PELICULA,pelicula);
        imagenPreviaListaPeliculaFragment.setArguments(bundle);

        return imagenPreviaListaPeliculaFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment__imagen_previa, container, false);

        imageViewPelicula = vista.findViewById(R.id.imageViewImagenPreviaFragment);
        floatingActionButton = vista.findViewById(R.id.floatingActionButtonImagenPreviaFragment);

        Bundle bundle = getArguments();

        final Pelicula pelicula = (Pelicula) bundle.getSerializable(CLAVE_PELICULA);

        Glide.with(getContext()).load(pelicula.generarUrlImagenDetalle()).into(imageViewPelicula);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(getContext(), "PROXIMAMENTE", Toast.LENGTH_SHORT).show();
            }
        });


        return vista;
    }






}
