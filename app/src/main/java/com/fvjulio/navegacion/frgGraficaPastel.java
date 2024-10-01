package com.fvjulio.navegacion;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frgGraficaPastel#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frgGraficaPastel extends Fragment {

    private PieChart pieChart;
    private ArrayList<PieEntry> valoresY;
    private ArrayList<Integer> colores;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pieChart = view.findViewById(R.id.frg_grafica_pastel);
        pieChart.setUsePercentValues(true);
        graficarPastel();
    }

    public void graficarPastel(){
        //DECLARANDO LOS ARREGLOS
        valoresY=new ArrayList<>();
        colores=new ArrayList<>();
        //AGREGANDO DATOS
        valoresY.add(new PieEntry(30,"Asistencia"));
        valoresY.add(new PieEntry(20,"Faltas"));
        valoresY.add(new PieEntry(25,"Justificaciones"));
        valoresY.add(new PieEntry(5,"Retardos"));
        valoresY.add(new PieEntry(20,"otros"));
        //AGREGANDO COLORES
        //colores.add(ContextCompat.getColor(getContext(),R.color.amarillo));
        colores.add(ContextCompat.getColor(getContext(),R.color.azul));
        colores.add(ContextCompat.getColor(getContext(),R.color.rosa));
        colores.add(ContextCompat.getColor(getContext(),R.color.verde));

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getContext(), "Valor seleccionado: " + e.getY(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected() {

            }
        });
        //ASIGNANDO DATOS AL OBJETO SET Y CONFIGURANDO PROPIEDADES
        PieDataSet set=new PieDataSet(valoresY, "Porcentajes de asistencia");
        set.setColors(colores);//ASIGNA COLOR A LAS rebanadas;
        set.setValueTextSize(15f);//ASIGNA TAMAÑO AL TEXTO DE LOS VALORES
        PieData data=new PieData(set);// ASIGNA LA DATA AL OBJETO PIE
        set.setDrawValues(true);
        pieChart.setDrawHoleEnabled(true);//con forma de dona o no
        pieChart.animateY(2000);
        pieChart.setData(data);
        pieChart.invalidate();
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frgGraficaPastel() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frgGraficaPastel.
     */
    // TODO: Rename and change types and number of parameters
    public static frgGraficaPastel newInstance(String param1, String param2) {
        frgGraficaPastel fragment = new frgGraficaPastel();
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
        return inflater.inflate(R.layout.fragment_frg_grafica_pastel, container, false);
    }
}