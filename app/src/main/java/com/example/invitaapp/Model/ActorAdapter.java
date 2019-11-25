package com.example.invitaapp.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.invitaapp.R;
import com.example.invitaapp.View.PeliculaAdapter;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {
    protected List<Actor> listaDeActores;
    private ListenerDelAdapter listenerDelAdapter;

    public ActorAdapter(List<Actor> listaDeActores) {
        this.listaDeActores = listaDeActores;
    }

    public ActorAdapter(ListenerDelAdapter listenerDelAdapter) {
        this.listaDeActores = new ArrayList<>();
        this.listenerDelAdapter = listenerDelAdapter;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View vista = inflador.inflate(R.layout.celda_actor, parent, false);

        ActorViewHolder actorViewHolder = new ActorViewHolder(vista);

        return actorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
        Actor unActorDeLaLista = listaDeActores.get(position);
        holder.CargarDatos(unActorDeLaLista);
    }

    @Override
    public int getItemCount() {
        return listaDeActores.size();
    }

   /* public List<Actor> getListaDeActores() {
        return listaDeActores;
    }*/

    public void setListaDeActores(List<Actor> listaDeActores) {
        this.listaDeActores = listaDeActores;
        notifyDataSetChanged();
    }

    public class ActorViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewActorNombre;
        private ImageView imageViewActor;
        private TextView textViewActorPersonaje;
        private TextView textViewEdadActor;


        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewActorNombre = itemView.findViewById(R.id.textViewNombreActor);
            imageViewActor = itemView.findViewById(R.id.imageViewImagenActor);
            textViewActorPersonaje = itemView.findViewById(R.id.textViewNombrePersonaje);

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Actor actorSeleccionado = listaDeActores.get(getAdapterPosition());

                }
            });*/

        }

        public void CargarDatos(Actor actor) {
            Glide.with(imageViewActor.getContext()).load(actor.getUrlImagenActor()).placeholder(R.drawable.cargando).into(imageViewActor);
            textViewActorNombre.setText(actor.getNombreDelActor());
            textViewActorPersonaje.setText(actor.getNombreDelPersonaje());
            //textViewEdadActor.setText("Edad: " );
        }

    }

    public interface ListenerDelAdapter {
        public void informarActorSeleccionado(Actor actor);
    }
}
