package com.example.invitaapp.View;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.R;

import java.util.ArrayList;
import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> {

    protected List<Pelicula> listaDePeliculas;
    private ListenerDelAdapter listenerDelAdapter;

    public PeliculaAdapter(List<Pelicula> listaDePeliculas) {
        this.listaDePeliculas = listaDePeliculas;
    }

    public PeliculaAdapter(ListenerDelAdapter listenerDelAdapter) {
        this.listaDePeliculas = new ArrayList<>();
        this.listenerDelAdapter = listenerDelAdapter;
    }

    @NonNull
    @Override
    public PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflador = LayoutInflater.from(parent.getContext());
        View vista = inflador.inflate(R.layout.celda_pelicula, parent, false);

        PeliculaViewHolder peliculaViewHolder = new PeliculaViewHolder(vista);

        return peliculaViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula unaPeliculaDeLaLista = listaDePeliculas.get(position);
        holder.CargarImagen(unaPeliculaDeLaLista);
    }

    @Override
    public int getItemCount() {
        return listaDePeliculas.size();
    }

    public void setPeliculaList(List<Pelicula> peliculaList) {
        this.listaDePeliculas = peliculaList;
        notifyDataSetChanged();
    }


    public interface ListenerDelAdapter {
        void informarPeliculaSeleccionada(Pelicula pelicula);
    }

    public class PeliculaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewPelicula;

        public PeliculaViewHolder(@NonNull final View itemView) {
            super(itemView);
            imageViewPelicula = itemView.findViewById(R.id.imageViewImagenPelicula);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Pelicula peliculaSeleccionada = listaDePeliculas.get(getAdapterPosition());
                    listenerDelAdapter.informarPeliculaSeleccionada(peliculaSeleccionada);
                }
            });
        }

        public void CargarImagen(Pelicula pelicula) {
            Glide.with(imageViewPelicula.getContext()).load(pelicula.generarUrlImagen()).placeholder(R.drawable.cargando).into(imageViewPelicula);
        }

    }


}
