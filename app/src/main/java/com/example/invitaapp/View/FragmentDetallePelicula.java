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
import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.R;
import com.google.android.material.tabs.TabLayout;

public class FragmentDetallePelicula extends Fragment {

    private TextView textViewTitulo;
    private ImageView imageViewFotoPeli;
    private TabLayout tabLayoutDetallePelicula;
    private ViewPager viewPagerDetallePelicula;

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
    }

    public void CargarImagen(Pelicula pelicula){
        Glide.with(imageViewFotoPeli.getContext()).load(pelicula.generarUrlImagenDetalle()).placeholder(R.drawable.cargando).into(imageViewFotoPeli);
    }





}
