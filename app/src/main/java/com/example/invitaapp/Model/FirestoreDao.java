package com.example.invitaapp.Model;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.invitaapp.Utils.ResultListener;
import com.example.invitaapp.View.FragmentListaPeliculas;
import com.example.invitaapp.View.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class FirestoreDao {

    public static final String PELICULAS_FAVORITAS = "peliculasFavoritas";
    private FirebaseFirestore firestore;
    private FirebaseUser currentUser;
    private ContainerPeliculas containerPeliculas;

    public FirestoreDao() {
        firestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        traerPeliculasFavoritas();
    }


    public void agregarPeliculaAFav(Pelicula pelicula) {
        if (containerPeliculas.contieneLaPelicula(pelicula)) {
            containerPeliculas.removerPelicula(pelicula);
        } else {
            containerPeliculas.agregarPelicula(pelicula);
        }
        firestore.collection(PELICULAS_FAVORITAS)
                .document(currentUser.getUid())
                .set(containerPeliculas);
    }

    private void traerPeliculasFavoritas() {
        if (currentUser != null) {
            firestore.collection(PELICULAS_FAVORITAS)
                    .document(currentUser.getUid())
                    .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                //en el listener del on succes intento tranfomar el documento a un container
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    containerPeliculas = documentSnapshot.toObject(ContainerPeliculas.class);
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        //en el on failure del listener inicializo un container vacio
                        public void onFailure(@NonNull Exception e) {
                            containerPeliculas = new ContainerPeliculas();
                        }
                    });
        }else{
           return;
        }

    }

    public void traerPeliculasFavoritas(final ResultListener<List<Pelicula>> listenerDelController) {
        if (currentUser != null){
            firestore.collection(PELICULAS_FAVORITAS)
                    .document(currentUser.getUid())
                    .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                //en el listener del on succes intento tranfomar el documento a un container
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    containerPeliculas = documentSnapshot.toObject(ContainerPeliculas.class);
                    if (containerPeliculas == null) {
                        containerPeliculas = new ContainerPeliculas();
                    }
                    listenerDelController.finish(containerPeliculas.getPeliculaList());
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        //en el on failure del listener inicializo un container vacio
                        public void onFailure(@NonNull Exception e) {
                            containerPeliculas = new ContainerPeliculas();
                            listenerDelController.finish(containerPeliculas.getPeliculaList());
                        }
                    });
        }else{
            return;
        }

    }

    public FirebaseUser getCurrentUser() {
        return currentUser;
    }
}
