package com.example.invitaapp.View;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.invitaapp.Controller.PeliculaController;
import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.R;
import com.example.invitaapp.Utils.ResultListener;

import java.util.List;

public class FragmentListaPeliculas extends Fragment implements PeliculaAdapter.ListenerDelAdapter {

    private RecyclerView recyclerViewFragmentListaPeliculas;
    private RecyclerView recyclerViewFragmentListaPeliculas2;
    private RecyclerView recyclerViewFragmentListaPeliculas3;
    private RecyclerView recyclerViewFragmentListaPeliculas4;
    private TextView textViewNombreGenero1;
    private TextView textViewNombreGenero2;
    private TextView textViewNombreGenero3;
    private TextView textViewNombreGenero4;
    private ListenerDelFragment listenerDelFragment;
    private ViewPager viewPagerImagenPrevia;
    private AdapterViewPagerFotoPreviaPelicula adapterViewPagerFotoPreviaPelicula;
    private Handler handler;
    private int delay = 3000; //milliseconds
    private Runnable runnable;

    Integer page = 0;

   /* public FragmentListaPeliculas() {
        // Required empty public constructor
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vistaDelFragment = inflater.inflate(R.layout.fragment_lista_peliculas, container, false);

        inflarVistas(vistaDelFragment);


        final PeliculaAdapter peliculaAdapter1 = new PeliculaAdapter(this);
        final PeliculaAdapter peliculaAdapter2 = new PeliculaAdapter(this);
        final PeliculaAdapter peliculaAdapter3 = new PeliculaAdapter(this);
        final PeliculaAdapter peliculaAdapter4 = new PeliculaAdapter(this);

        final PeliculaController peliculaController = new PeliculaController();


   peliculaController.traerPeliculas(new ResultListener<List<Pelicula>>() {
       @Override
       public void finish(List<Pelicula> result) {
           peliculaAdapter1.setPeliculaList(result);
           textViewNombreGenero1.setText("TRENDING");

       }
   });

    peliculaController.traerPeliculas2(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> result) {
                peliculaAdapter2.setPeliculaList(result);
                textViewNombreGenero2.setText("NOW PLAYING");

            }
    });

    peliculaController.traerPeliculas3(new ResultListener<List<Pelicula>>() {
            @Override
            public void finish(List<Pelicula> result) {
                peliculaAdapter3.setPeliculaList(result);
                textViewNombreGenero3.setText("TOP RATED");
                crearViewPager(result);

            }
        });


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        recyclerViewFragmentListaPeliculas.setLayoutManager(linearLayoutManager);
        recyclerViewFragmentListaPeliculas.setAdapter(peliculaAdapter1);

        recyclerViewFragmentListaPeliculas2.setLayoutManager(linearLayoutManager2);
        recyclerViewFragmentListaPeliculas2.setAdapter(peliculaAdapter2);

        recyclerViewFragmentListaPeliculas3.setLayoutManager(linearLayoutManager3);
        recyclerViewFragmentListaPeliculas3.setAdapter(peliculaAdapter3);

        recyclerViewFragmentListaPeliculas4.setLayoutManager(linearLayoutManager4);
        recyclerViewFragmentListaPeliculas4.setAdapter(peliculaAdapter4);



        return vistaDelFragment;
    }

    private void crearViewPager(List<Pelicula> peliculaList) {
        adapterViewPagerFotoPreviaPelicula = new AdapterViewPagerFotoPreviaPelicula(getActivity().getSupportFragmentManager(),peliculaList);
        viewPagerImagenPrevia.setAdapter(adapterViewPagerFotoPreviaPelicula);
        Runnable runnable = new Runnable() {
            public void run() {
                if (adapterViewPagerFotoPreviaPelicula.getCount() == page) {
                    page = 0;
                } else {
                    page++;
                }
                viewPagerImagenPrevia.setCurrentItem(page, true);
                handler.postDelayed(this, delay);
            }
        };
        handler = new Handler();

        handler.postDelayed(runnable, delay);
        setListenerDelViewPager();

    }

    private  void setListenerDelViewPager(){

        viewPagerImagenPrevia.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                page = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void inflarVistas(View view) {
        recyclerViewFragmentListaPeliculas = view.findViewById(R.id.RecyclerViewFragmentListaPeliculasXML);
        recyclerViewFragmentListaPeliculas2= view.findViewById(R.id.RecyclerViewFragmentListaPeliculas2);
        recyclerViewFragmentListaPeliculas3= view.findViewById(R.id.RecyclerViewFragmentListaPeliculas3);
        recyclerViewFragmentListaPeliculas4=view.findViewById(R.id.RecyclerViewFragmentListaPeliculas4);
        textViewNombreGenero1 = view.findViewById(R.id.TextViewTipoDeGenero1);
        textViewNombreGenero2 = view.findViewById(R.id.TextViewTipoDeGenero2);
        textViewNombreGenero3 = view.findViewById(R.id.TextViewTipoDeGenero3);
        textViewNombreGenero4 = view.findViewById(R.id.TextViewTipoDeGenero4);
        viewPagerImagenPrevia = view.findViewById(R.id.ViewPagerSoloImagenes);
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listenerDelFragment = (ListenerDelFragment) context;
    }

    @Override
    public void informarPeliculaSeleccionada(Pelicula pelicula) {
        listenerDelFragment.recibirPelicula(pelicula);
    }

    public interface ListenerDelFragment{
        void recibirPelicula(Pelicula pelicula);
    }

}