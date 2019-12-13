package com.example.invitaapp.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.invitaapp.Model.Pelicula;
import java.util.ArrayList;
import java.util.List;

public class AdapterViewPagerFotoPreviaPelicula extends FragmentStatePagerAdapter {

    private List<ImagenPreviaListaPeliculaFragment> listaDeFragment;


    public AdapterViewPagerFotoPreviaPelicula(FragmentManager fm, List<Pelicula> peliculaList) {
        super(fm);
        this.listaDeFragment = new ArrayList<>();

        for (Pelicula unaPelicula : peliculaList) {

            ImagenPreviaListaPeliculaFragment fragment = ImagenPreviaListaPeliculaFragment.dameUnFragment(unaPelicula);

            //lo agrego a la lista del fragments
            listaDeFragment.add(fragment);
        }
    }




    @Override
    public Fragment getItem(int position) {
        return listaDeFragment.get(position);
    }

    @Override
    public int getCount() {
        return listaDeFragment.size();
    }


}
