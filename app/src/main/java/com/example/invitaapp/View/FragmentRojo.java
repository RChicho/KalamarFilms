package com.example.invitaapp.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.invitaapp.R;


public class FragmentRojo extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fragment_rojo, container, false);

        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_fragment_detalle_actor, container, false);



        return vista;

        }
    }





