package com.example.invitaapp.View;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.R;
import java.util.List;

public class ImagenPreviaListaPeliculaFragment extends Fragment {

    public static final String CLAVE_PELICULA = "claveImagen";
    private List<Fragment> listaPeliculas;
    private ImageView imageViewPelicula;


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


        Bundle bundle = getArguments();

        Pelicula pelicula = (Pelicula) bundle.getSerializable(CLAVE_PELICULA);

        Glide.with(getContext()).load(pelicula.generarUrlImagenDetalle()).into(imageViewPelicula);


        return vista;
    }



}
