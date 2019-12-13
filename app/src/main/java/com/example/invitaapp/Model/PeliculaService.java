package com.example.invitaapp.Model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PeliculaService {
    @GET ("/3/movie/{id}?api_key=25ac4313f0c6a0579d228b6da85f955f")
    Call<Pelicula>traerPelicula(@Path("id")int idPelicula);

    @GET("3/trending/movie/week")
    Call<ContainerPeliculas>traerListaDePeliculasTrending(@Query("api_key") String apiKey);

    @GET ("/3/movie/226?api_key=25ac4313f0c6a0579d228b6da85f955f")
    Call<Pelicula>traerUnaPelicula();

    @GET ("https://image.tmdb.org/t/p/w200/{String}")
    Call<Pelicula> traerImagen(@Path("String")String urlImagen);

    @GET ("/3/movie/{id}/credits?api_key=25ac4313f0c6a0579d228b6da85f955f")
    Call<ContainerDeActores>traerListaDeActores(@Path("id") int idPelicula);


    @GET("/3/movie/now_playing?api_key=25ac4313f0c6a0579d228b6da85f955f&language=es-SP")
    Call<ContainerPeliculas>traerListaDePeliculasNowPlaying();

    @GET("/3/movie/top_rated?api_key=25ac4313f0c6a0579d228b6da85f955f&language=es-SP")
    Call<ContainerPeliculas>traerListaDeTopRated();

    @GET("3/movie/{id}/similar?api_key=25ac4313f0c6a0579d228b6da85f955f&language=es-SP")
    Call<ContainerPeliculas>traerListaPeliculasSimilares(@Path("id") int idPelicula);

    @GET("3/movie/{id}/videos?api_key=25ac4313f0c6a0579d228b6da85f955f&language=en-US")
    Call<ContainerDeTrailers>traerTrailerPelicula(@Path("id") int idPelicula);


  /*  @GET()
    Call<ContainerDeActores> traerListaDeActores();*/
}
