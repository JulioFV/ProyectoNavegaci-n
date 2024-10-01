package com.fvjulio.navegacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.fvjulio.navegacion.adapter.AdapterEstudiante;
import com.fvjulio.navegacion.adapter.AdapterGrupo;
import com.fvjulio.navegacion.modelo.MEstudiante;
import com.fvjulio.navegacion.modelo.MGrupo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frg_Estudiante#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frg_Estudiante extends Fragment {
    private  Bundle paquete;
    private TextView txtNombre;
    private RecyclerView rec;
    private NavController navegador;

    private EditText txtFiltro;
    private AdapterEstudiante adapter;
    private ArrayList<MEstudiante> lista;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtNombre=view.findViewById(R.id.item_estu_txtnombre);
        txtFiltro=view.findViewById(R.id.frg_estudiantes_txtbuscador);
        txtFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buscador(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        rec=view.findViewById(R.id.frg_estu_recycler_view);
        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        lista=llenadoManual();
        adapter=new AdapterEstudiante(lista);
        rec.setAdapter(adapter);



    }

    private void buscador(String s) {

        ArrayList<MEstudiante> lista2=new ArrayList<MEstudiante>();

        for (MEstudiante est:lista){
            if(est.getNombre().contains(s.toString()) || est.getMatricula().contains(s.toString())){
                lista2.add(est);
            }
        }
        adapter.filtro(lista2);
    }

    private ArrayList<MEstudiante> llenadoManual() {
        ArrayList<MEstudiante> lista = new ArrayList<>();
        lista.add(new MEstudiante("21011178","Florentino","Vigueras","Julio Cesar",
                (byte) 100, (byte) 80, (byte) 70, (byte) 100, (byte) 1));
        lista.add(new MEstudiante("21011175","Martinez","Barrera","Guadalupe Monserrath",
                (byte) 100, (byte) 90, (byte) 100, (byte) 100, (byte) 2));
        lista.add(new MEstudiante("21011350","Hernandez","Aguilar","Marcelino",
                (byte) 100, (byte) 80, (byte) 100, (byte) 70, (byte) 2));
        lista.add(new MEstudiante("21011890","Diaz","Guerrero","Brenda Selene",
                (byte) 100, (byte) 80, (byte) 100, (byte) 80, (byte) 2));
        lista.add(new MEstudiante("21011878","Florentino","Vigueras","Jonathan Asael",
                (byte) 90, (byte) 100, (byte) 100, (byte) 100, (byte) 1));
        lista.add(new MEstudiante("19011150","Hernandez","Hernandez","Brian",
                (byte) 80, (byte) 70, (byte) 50, (byte) 80, (byte) 1));


        return lista;

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frg_Estudiante() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_Estudiante.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_Estudiante newInstance(String param1, String param2) {
        frg_Estudiante fragment = new frg_Estudiante();
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
        return inflater.inflate(R.layout.fragment_frg__estudiante, container, false);
    }
}