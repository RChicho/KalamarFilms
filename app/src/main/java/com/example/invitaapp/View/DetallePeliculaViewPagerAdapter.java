package com.example.invitaapp.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.invitaapp.Model.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class DetallePeliculaViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> listaFragmentDetallePelicula;


    public DetallePeliculaViewPagerAdapter(FragmentManager fm, Pelicula pelicula) {
        super(fm);
        listaFragmentDetallePelicula = new ArrayList<>();
        listaFragmentDetallePelicula.add(FragmentSinopsis.dameUnFragment(pelicula));
        listaFragmentDetallePelicula.add(FragmentDetalleActor.dameUnFragment(pelicula));
        listaFragmentDetallePelicula.add(FragmentPeliculasSimilares.dameUnFragment(pelicula));
        listaFragmentDetallePelicula.add(new FragmentRojo());

    }

    @Override
    public Fragment getItem(int position) {
        return listaFragmentDetallePelicula.get(position);
    }

    @Override
    public int getCount() {
        return listaFragmentDetallePelicula.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String titulo = "Trailer";
        switch (position) {
            case 0:
                titulo = "Sinopsis";
                break;
            case 1:
                titulo = "Actores";
                break;
            case 2:
                titulo = "Similares";
                break;
        }
        return titulo;
    }
}
