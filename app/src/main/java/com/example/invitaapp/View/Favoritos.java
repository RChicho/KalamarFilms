package com.example.invitaapp.View;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.invitaapp.Controller.FirestoreController;
import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.R;
import com.example.invitaapp.Utils.ResultListener;

import java.util.List;

public class Favoritos extends Fragment implements PeliculaAdapter.ListenerDelAdapter {

    private RecyclerView recyclerViewFavoritos;
    private ListenerDelFragment listenerDelFragment;
    private PeliculaAdapter peliculaAdapter;
    private FirestoreController firestoreController;


    public Favoritos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favoritos, container, false);

        recyclerViewFavoritos = view.findViewById(R.id.recyclerViewFragmentFavoritos);

        peliculaAdapter = new PeliculaAdapter(this);
        firestoreController = new FirestoreController();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false);
        recyclerViewFavoritos.setLayoutManager(gridLayoutManager);
        recyclerViewFavoritos.setAdapter(peliculaAdapter);


        firestoreController.traerListaDeFavorito( new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> result) {
                peliculaAdapter.setPeliculaList(result);
            }
        });

        return view;
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


    public interface ListenerDelFragment{
        void recibirPelicula(Pelicula pelicula);
    }

}
