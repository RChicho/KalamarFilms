package com.example.invitaapp.View;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.invitaapp.Controller.FirestoreController;
import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.R;
import com.example.invitaapp.Utils.ResultListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class FragmentDetallePelicula extends Fragment {

    private TextView textViewTitulo;
    private ImageView imageViewFotoPeli;
    private TabLayout tabLayoutDetallePelicula;
    private ViewPager viewPagerDetallePelicula;
    private FloatingActionButton botonFav;
    private FirestoreController firestoreController;
    private Boolean esFavorita;
    private Pelicula peliculaSeleccionada;


    public static final String CLAVE_PELICULA = "CLAVE_PELICULA";


    public static FragmentDetallePelicula dameUnFragmentDetallePelicula(Pelicula unaPelicula){

        FragmentDetallePelicula unFragmentDetallePelicula = new FragmentDetallePelicula();

        Bundle bundle = new Bundle();
        bundle.putSerializable(CLAVE_PELICULA, unaPelicula);
        unFragmentDetallePelicula.setArguments(bundle);

        return unFragmentDetallePelicula;
    }


    public FragmentDetallePelicula() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vistaFragment = inflater.inflate(R.layout.fragment_detalle_pelicula, container, false);

        inflarVistas(vistaFragment);

        Pelicula unaPelicula = recepcionarPelicula();


        textViewTitulo.setText(unaPelicula.getTitulo());
        CargarImagen(unaPelicula);

        DetallePeliculaViewPagerAdapter detallePeliculaViewPagerAdapter = new DetallePeliculaViewPagerAdapter(getActivity().getSupportFragmentManager(),unaPelicula);
        viewPagerDetallePelicula.setAdapter(detallePeliculaViewPagerAdapter);
        tabLayoutDetallePelicula.setupWithViewPager(viewPagerDetallePelicula);


       /*if(quiereTrailer){
            viewPagerDetallePelicula.setCurrentItem(3);
        }*/

        botonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firestoreController.agregarPeliculaAFav(peliculaSeleccionada);
                esFavorita = !esFavorita;
                actualizarFav();
            }
        });

        firestoreController.traerListaDeFavorito(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> result) {
                esFavorita = result.contains(peliculaSeleccionada);
                actualizarFav();
                habilitarOnClickDeFav();
            }
        });
        botonFav.setClickable(false);


        return vistaFragment;
    }


    private Pelicula recepcionarPelicula() {

        Bundle bundle = getArguments();
        Pelicula peliculaSeleccionada = (Pelicula) bundle.getSerializable(CLAVE_PELICULA);

        return peliculaSeleccionada;
    }

    private void inflarVistas(View vistaFragment) {

        textViewTitulo = vistaFragment.findViewById(R.id.DetallePeliculaTextViewTitulo);
        imageViewFotoPeli = vistaFragment.findViewById(R.id.ImageViewDetallePeliculaFoto);
        tabLayoutDetallePelicula = vistaFragment.findViewById(R.id.tabLayoutFragmentDetallePelicula);
        viewPagerDetallePelicula = vistaFragment.findViewById(R.id.viewPagerFragmentDetallePelicula);
        botonFav = vistaFragment.findViewById(R.id.)
    }

    public void CargarImagen(Pelicula pelicula){
        Glide.with(imageViewFotoPeli.getContext()).load(pelicula.generarUrlImagenDetalle()).placeholder(R.drawable.cargando).into(imageViewFotoPeli);
    }

    private void habilitarOnClickDeFav() {
        botonFav.setClickable(true);
    }

    private void actualizarFav(){
        if (esFavorita){
            botonFav.setImageResource(R.drawable.ic_favorite_black_24dp_full);
        }
        else
        {
            botonFav.setImageResource(R.drawable.ic_favorite_border_black_24dp_vacio);
        }
    }

}
