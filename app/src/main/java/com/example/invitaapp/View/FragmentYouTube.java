package com.example.invitaapp.View;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.invitaapp.Model.Pelicula;
import com.example.invitaapp.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentYouTube extends Fragment {

    private YouTubePlayerView youTubePlayerView;
    public static final String CLAVE_PELICULA = "CLAVE_PELICULA";


    public FragmentYouTube() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragment_you_tube, container, false);

        YouTubePlayerView youTubePlayerView = vista.findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "S0Q4gqBUs7c";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        return vista;
    }

    public static FragmentYouTube dameUnFragment(Pelicula pelicula) {
        FragmentYouTube fragmentYouTube = new FragmentYouTube();
        Bundle bundle = new Bundle();
        bundle.putSerializable(CLAVE_PELICULA, pelicula);
       fragmentYouTube.setArguments(bundle);

        return fragmentYouTube;
    }

}
