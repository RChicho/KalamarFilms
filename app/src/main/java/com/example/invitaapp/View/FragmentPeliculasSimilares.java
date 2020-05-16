package com.example.invitaapp.View;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.invitaapp.Controller.PeliculaController;
import com.example.invitaapp.Model.Actor;
import com.example.invitaapp.Model.ActorAdapter;
import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.R;
import com.example.invitaapp.Utils.ResultListener;

import java.util.List;

public class FragmentPeliculasSimilares extends Fragment implements PeliculaAdapter.ListenerDelAdapter {

    private RecyclerView recyclerView;
    private ListenerDelFragment listenerDelFragment;

    public static final String CLAVE_PELICULA_SIMILARES = "CLAVE_PELICULA";


    public FragmentPeliculasSimilares() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_peliculas_similares, container, false);
        recyclerView = vista.findViewById(R.id.recyclerViewFragmentPeliculasSimilares);

        final PeliculaAdapter peliculaAdapter = new PeliculaAdapter(this);
        final PeliculaController peliculaController = new PeliculaController();

        Pelicula unaPelicula = recepcionarPelicula();
        Integer idMovie = unaPelicula.getId();


        peliculaController.traerListaPeliculasSimilares(idMovie, new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> result) {
                peliculaAdapter.setPeliculaList(result);
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(peliculaAdapter);



        return vista;
    }

    public static FragmentPeliculasSimilares dameUnFragment(Pelicula pelicula) {
        FragmentPeliculasSimilares fragmentPeliculasSimilares = new FragmentPeliculasSimilares();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CLAVE_PELICULA_SIMILARES, pelicula);
        fragmentPeliculasSimilares.setArguments(bundle);

        return fragmentPeliculasSimilares;
    }

    private Pelicula recepcionarPelicula() {
        Bundle bundle = getArguments();
        Pelicula peliculaSeleccionada = (Pelicula) bundle.getSerializable(CLAVE_PELICULA_SIMILARES);
        return peliculaSeleccionada;
    }

    @Override
    public void informarPeliculaSeleccionada(Pelicula pelicula) {
        listenerDelFragment.recibirPelicula(pelicula);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listenerDelFragment = (ListenerDelFragment) context;
    }

    public interface ListenerDelFragment {
        void recibirPelicula(Pelicula pelicula);
    }
}
