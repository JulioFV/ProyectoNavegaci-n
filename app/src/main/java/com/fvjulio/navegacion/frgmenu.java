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
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frgmenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frgmenu extends Fragment {
    private  Bundle paquete;
    private TextView txtNombre;

    private CardView btnGrupos;

    private NavController navegador;




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtNombre = view.findViewById(R.id.menu_txtnombre);
        btnGrupos =  view.findViewById(R.id.menu_btn_grupos);

        btnGrupos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicGrupos();
            }
        });

        paquete= this.getArguments();
        if(paquete!=null){
            txtNombre.setText(paquete.getString("user"));
        }
        navegador= Navigation.findNavController(view);//Es para que funcione el Navegador
    }

    private void clicGrupos() {

        String nombre = this.txtNombre.getText().toString();
        if(!nombre.equals("")){
            paquete.putString("user",nombre);
        }

        navegador.navigate(R.id.action_frgmenu_to_frg_grupos,paquete);
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frgmenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frgmenu.
     */
    // TODO: Rename and change types and number of parameters
    public static frgmenu newInstance(String param1, String param2) {
        frgmenu fragment = new frgmenu();
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
        return inflater.inflate(R.layout.fragment_frgmenu, container, false);
    }
}