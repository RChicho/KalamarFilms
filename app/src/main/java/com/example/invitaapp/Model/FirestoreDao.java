package com.example.invitaapp.Model;

import androidx.annotation.NonNull;

import com.example.invitaapp.Utils.ResultListener;
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

    //pasos a seguir para que esto funcione
    //1 el container tiene que tener metodos para agregar sacar chequear si exite (no obligatorio pero mas comodo
    //2 peliculas tien que tenes equals para compara y contructor vacio


    //inicializo mis servicion en el contructor del DAO
    public FirestoreDao() {
        firestore = FirebaseFirestore.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        //tambien traigo la lista de favoritos del usuario
        traerPeliculasFavoritas();
    }



    public void agregarPeliculaAFav(Pelicula pelicula){
        //le pregunto si la pelicula que quiero agregar ya se encuentra en favs
        if (containerPeliculas.contieneLaPelicula(pelicula)){
            //si esta la saco
            containerPeliculas.removerPelicula(pelicula);
        }
        else {
            //si no esta la agrego
            containerPeliculas.agregarPelicula(pelicula);
        }
        //updateo la lista en firebase
        firestore.collection(PELICULAS_FAVORITAS)
                .document(currentUser.getUid())
                .set(containerPeliculas);
    }

    private void traerPeliculasFavoritas() {
        //traigo la referencia y le intento traer la lista
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
    }

    public void traerPeliculasFavoritas(final ResultListener<List<Pelicula>> listenerDelController){
        //traigo la referencia y le intento traer la lista
        firestore.collection(PELICULAS_FAVORITAS)
                .document(currentUser.getUid())
                .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            //en el listener del on succes intento tranfomar el documento a un container
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                containerPeliculas = documentSnapshot.toObject(ContainerPeliculas.class);
                if (containerPeliculas == null){
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
                        //ademas de actualizar la lista se lo doy a la vista que lo va a necesitar
                        listenerDelController.finish(containerPeliculas.getPeliculaList());
                    }
                });
    }

}
