package com.fvjulio.navegacion;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frg_login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_login extends Fragment {

        private EditText txtUsuario;

        private CardView btnEntrar,btnRegistro,btnRecuperar;
        private NavController navegador;
        private MediaPlayer sonido;

        private Bundle paquete;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frg_login() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_login.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_login newInstance(String param1, String param2) {
        frg_login fragment = new frg_login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frg_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnEntrar=view.findViewById(R.id.login_btn_entrar);
        btnRecuperar=view.findViewById(R.id.login_btn_recupera);
        btnRegistro=view.findViewById(R.id.login_btn_registro);

        navegador = Navigation.findNavController(view);

        txtUsuario= view.findViewById(R.id.login_txtusuario);
        sonido = MediaPlayer.create(this.getContext(),R.raw.sonido);
        paquete = new Bundle();
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicEntrar();
            }
        });
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicRecuperar();
            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicRegistro();
            }
        });

    }

    private void clicRegistro() {
        if(sonido!=null)
            sonido.start();
        navegador.navigate(R.id.action_frg_login_to_rfg_registro);
    }

    private void clicRecuperar() {
        navegador.navigate(R.id.action_frg_login_to_frg_recuperacontra);
    }

    private void clicEntrar() {
        if(sonido!=null)
            sonido.start();

        String nombre = this.txtUsuario.getText().toString();
        if(!nombre.equals("")){
            paquete.putString("user",nombre);
        }
        navegador.navigate(R.id.action_frg_login_to_frgmenu,paquete);

    }
}