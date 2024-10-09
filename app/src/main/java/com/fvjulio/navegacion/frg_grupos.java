package com.fvjulio.navegacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fvjulio.navegacion.adapter.AdapterGrupo;
import com.fvjulio.navegacion.modelo.MGrupo;
import com.fvjulio.navegacion.volley.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.fvjulio.navegacion.volley.API;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;


public class frg_grupos extends Fragment {
    private EditText txtFiltro;
    private AdapterGrupo adapter;
    private ArrayList<MGrupo> lista;
    private  Bundle paquete;
    private TextView txtNombre;
    private RecyclerView rec;
    private NavController navegador;

    private FloatingActionButton btnAdd;



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

            txtNombre = view.findViewById(R.id.grupos_txtnombre);
            txtFiltro=view.findViewById(R.id.frg_grupo_txtbuscar);
            btnAdd=view.findViewById(R.id.frggpo_btn_mas);
            navegador= Navigation.findNavController(view);
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clicAgregar();
                }
            });
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
            paquete=this.getArguments();
            if(paquete!=null){
                txtNombre.setText(paquete.getString("user"));
            }
            rec=view.findViewById(R.id.frg_grupo_recycler_view);

            lista=llenadoDesdeBD();

//            rec=view.findViewById(R.id.frg_grupo_recycler_view);
//            rec.setHasFixedSize(true);
//            rec.setLayoutManager(new LinearLayoutManager(getContext()));
//            lista=llenadoManual();
//            adapter=new AdapterGrupo(lista);
//            rec.setAdapter(adapter);

        paquete= this.getArguments();
        if(paquete!=null){
            txtNombre.setText(paquete.getString("user"));
        }
        navegador= Navigation.findNavController(view);//Es para que funcione el Navegador
    }

    private void clicAgregar() {
        //Aqui va el nav controller

        navegador.navigate(R.id.action_frg_grupos_to_frg_CRUD_gpo);

    }

    private void buscador(String s) {
       ArrayList<MGrupo> lista2=new ArrayList<MGrupo>();

        for (MGrupo gpo: lista){
            if(gpo.getClave().contains(s.toString()) || gpo.getNombreAsig().contains(s.toString())){
                lista2.add(gpo);
            }
        }
        adapter.filtro(lista2);
    }

    private ArrayList<MGrupo> llenadoDesdeBD() {
        ArrayList<MGrupo> lista=new ArrayList<MGrupo>();

        //Crea un AlertDialog
        AlertDialog.Builder msg = new AlertDialog.Builder(this.getContext());

        // Crear un ProgressBar
        ProgressBar progressBar = new ProgressBar(this.getContext());
        progressBar.setIndeterminate(true); // Estilo de carga indeterminada

        // Crear el AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle("Por favor, espera");
        builder.setMessage("Conectandose con el servidor...");
        builder.setView(progressBar);
        builder.setCancelable(false); // Evitar que se pueda cancelar
        AlertDialog dialog = builder.create();
        dialog.show();

        RequestQueue colaDeSolicitudes= VolleySingleton.getInstance(this.getContext()).getRequestQueue();
        StringRequest solicitud= new StringRequest(Request.Method.POST,API.LISTARGPO,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();//apaga el cuadro de dialogo
                        try {
                            //LEER AQUI EL CONTENIDO DE LA VARIABLE response

                            JSONObject contenido = new JSONObject(response);
                            JSONArray array=contenido.getJSONArray("contenido");
                            MGrupo obj=new MGrupo();
                            for (int i = 0; i < array.length(); i++) {
                                obj=new MGrupo();
                                JSONObject pos=new JSONObject(array.getString(i));
                                obj.setIdGrupo(pos.getInt("idGrupo"));
                                obj.setClave(pos.getString("clave"));
                                obj.setNombreAsig(pos.getString("nombreAsig"));
                                obj.setNombreDoc(pos.getString("nombreDoc")+" "+pos.getString("app") + " "+
                                        pos.getString("apm"));
                                obj.setNombrePer("nombrePer");
                                lista.add(obj);
                            }

                            rec.setHasFixedSize(true);
                            rec.setLayoutManager(new LinearLayoutManager(getContext()));
                            adapter=new AdapterGrupo(lista);
                            rec.setAdapter(adapter);



                    }catch (Exception ex){
                        //DETECTA ERRORES EN LA LECTURA DEL ARCHIVO JSON

                            msg.setTitle("Error");
                            msg.setMessage("La información no se pudo leer");
                            msg.setPositiveButton("Aceptar",null);
                            AlertDialog dialog=msg.create();
                            msg.show();

                    }

                }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            dialog.dismiss();
            // DETECTA ERRORES EN LA COMUNICACIÓN
            msg.setTitle("Error");
            msg.setMessage("No se pudo conectar con el servidor");
            msg.setPositiveButton("Aceptar",null);
            AlertDialog dialog=msg.create();
            msg.show();
        }
    }){
        @Override
        protected Map<String, String> getParams(){
            Map<String, String> param=new HashMap<String,String>();
            //PASA PARAMETROS A LA SOLICITUD
            param.put("id","1");
            return param;
        }
    };
    //ENVIA LA SOLICITUD
        colaDeSolicitudes.add(solicitud);


        return lista;
    }

   private ArrayList<MGrupo> llenadoManual() {
        ArrayList<MGrupo> lista=new ArrayList<MGrupo>();
        lista.add(new MGrupo("78BA","Aplicaciónes Móviles Nativas de Código Abierto",
                "Mario Perez Bautista",3,1));

        lista.add(new MGrupo("405A","Simulación","Guillermo Ortiz Castañeda",3,2));
        lista.add(new MGrupo("401B","Matematicas Discretas","Aline Pérez Martinez",1,3));

        lista.add(new MGrupo("1O7B","Lenguajes y Automatas","Aline Pérez Martinez",1,3));
        lista.add(new MGrupo("2O7B","Conmutacion y enrutamiento","German Rebolledo Avalos",1,3));
        lista.add(new MGrupo("5O7B","Sistemas programables","Josue Emmanuel Rodriguez Allende",1,3));

        return lista;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frg_grupos() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frg_grupos.
     */
    // TODO: Rename and change types and number of parameters
    public static frg_grupos newInstance(String param1, String param2) {
        frg_grupos fragment = new frg_grupos();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frg_grupos, container, false);
    }
}