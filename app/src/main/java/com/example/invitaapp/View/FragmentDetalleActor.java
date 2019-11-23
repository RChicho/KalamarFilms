package com.example.invitaapp.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.invitaapp.Controller.PeliculaController;
import com.example.invitaapp.Model.Actor;
import com.example.invitaapp.Model.ActorAdapter;
import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.R;
import com.example.invitaapp.Utils.ResultListener;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetalleActor extends Fragment implements ActorAdapter.ListenerDelAdapter {

    private RecyclerView recyclerViewListaDeActores;
    private ListenerDelFragment listenerDelFragment;
    private TextView textViewNombreActor;
    private TextView textViewNombrePersonaje;
    private TextView textViewEdadActor;

    public static final String CLAVE_PELICULA = "CLAVE_PELICULA";


    public FragmentDetalleActor() {
        // Required empty public constructor
    }

    public static FragmentDetalleActor dameUnFragment(Pelicula pelicula) {
        FragmentDetalleActor fragmentDetalleActor = new FragmentDetalleActor();

        Bundle bundle = new Bundle();

        bundle.putSerializable(CLAVE_PELICULA, pelicula);

        fragmentDetalleActor.setArguments(bundle);


        return fragmentDetalleActor;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vistaDelFragment = inflater.inflate(R.layout.fragment_fragment_detalle_actor, container, false);
        inflarVistas(vistaDelFragment);
        Pelicula unaPelicula = recepcionarPelicula();

        //Pelicula pelicula = (Pelicula) getArguments().getSerializable("a");
        //Integer numeroId=  unaPelicula.getId();
        int idPelicular= unaPelicula.getId();




        final ActorAdapter actorAdapter = new ActorAdapter(this);
        final PeliculaController peliculaController = new PeliculaController();
        peliculaController.traerActores(idPelicular, new ResultListener<List<Actor>>() {
            @Override
            public void finish(List<Actor> result) {
                actorAdapter.setListaDeActores(result);
            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewListaDeActores.setLayoutManager(linearLayoutManager);
        recyclerViewListaDeActores.setAdapter(actorAdapter);

        return vistaDelFragment;
    }

   /* private Pelicula recepcionarPelicula() {
        Bundle bundle = getArguments();
       Pelicula peliculaSeleccionada = (Pelicula) bundle.getSerializable(CLAVE_PELICULA);

        return peliculaSeleccionada;
    }*/

    private void inflarVistas(View vistaDelFragment) {

        recyclerViewListaDeActores = vistaDelFragment.findViewById(R.id.RecyclerViewFragmentActores);
        textViewNombrePersonaje = vistaDelFragment.findViewById(R.id.textViewNombrePersonaje);

    }

    @Override
    public void informarActorSeleccionado(Actor actor) {
        listenerDelFragment.recibirActor(actor);

    }

    public interface ListenerDelFragment {
        void recibirActor(Actor actor);
    }
    private Pelicula recepcionarPelicula() {

        Bundle bundle = getArguments();
        Pelicula peliculaSeleccionada = (Pelicula) bundle.getSerializable(CLAVE_PELICULA);

        return peliculaSeleccionada;
    }
}
