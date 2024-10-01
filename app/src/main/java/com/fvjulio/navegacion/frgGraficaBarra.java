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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frgGraficaBarra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frgGraficaBarra extends Fragment {

    private BarChart grafica;
    private BarChart barChart ;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        barChart = view.findViewById(R.id.frg_barras_grafico);
        this.graficaBarras(new int[]{10,20,30,40,50,60,70,80,90,100});
    }

    private void graficaBarras(int[] valores){
        //DECLARANDO LOS ARREGLOS
       ArrayList<BarEntry> valoresY=new ArrayList<>();
        ArrayList<Integer>valoresX=new ArrayList<>();
        ArrayList<Integer> colores=new ArrayList<>();

        //AGREGANDO DATOS
        for(int i=0;i<valores.length;i++)
            valoresY.add(new BarEntry(i,valores[i]));
        //AGREGANDO COLORES
        colores.add(ContextCompat.getColor(getContext(),R.color.coral));
        colores.add(ContextCompat.getColor(getContext(),R.color.melocoton));
        colores.add(ContextCompat.getColor(getContext(),R.color.rosa));
        colores.add(ContextCompat.getColor(getContext(),R.color.verde));

        //ASIGNANDO DATOS AL OBJETO SET Y CONFIGUNADO PROPIEDADES
        BarDataSet set=new BarDataSet(valoresY, "Temas");
        set.setColors(colores);//ASIGNA COLOR A LAS BARRAS
        set.setValueTextSize(15f);//ASIGAN TAMAÑO AL TEXTO DE LOS VALORES
        set.setValueTextColors(colores);//ASIGNA COLOR AL TEXTO DE LOS VALORES
        set.setDrawValues(true);//

        BarData data=new BarData(set);// ASIGNA
        data.setBarWidth(0.8f);//ASIGNA EL ANCHO DE LAS BARRAS

        barChart.setData(data);//ASIGNA LOS DATOS AL OBJETO BARCHART
        //DETECTA EL CLIC SOBRE LA BARRA Y MUESTRA EL DATO
        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Toast.makeText(getContext(), "Valor seleccionado: " + e.getY(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });


        //ANIMA EL INICIO DE LA BARRA
        barChart.animateY(2000);
        //ASIGNA EL COLOR DE FONDO DE LA GRAFICA
        //barChart.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.black));
        //AUTOAJUSTA EL ANCHO DE LAS BARRAS
        barChart.setFitBars(true);
        //DIBUJA
        barChart.setDrawValueAboveBar(true);

        barChart.setDescription(new Description());
        barChart.invalidate();
        // Habilitar el desplazamiento y la escala
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.setScaleXEnabled(true); // Habilitar la escala en el eje X
        barChart.setScaleYEnabled(false); // Deshabilitar la escala en el eje Y (opcional)


        /* Configurar el rango del eje X
        XAxis xAxis = barChart.getXAxis();
        xAxis.setLabelRotationAngle(90f);
        xAxis.setAxisMinimum(0f); // Valor mínimo del eje X
        xAxis.setAxisMaximum(3f); // Valor máximo del eje X
        xAxis.setGranularity(1f); */ //Intervalo entre valores del eje X, ajustado a 1 para que cada valor sea una barra

        // Configurar el rango del eje Y
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0f); // Valor mínimo del eje Y
        yAxis.setAxisMaximum(100f); // Valor máximo del eje Y

        // También puedes configurar el eje Y derecho si lo necesitas
        YAxis rightYAxis = barChart.getAxisRight();
        rightYAxis.setAxisMinimum(0f); // Valor mínimo del eje Y derecho
        rightYAxis.setAxisMaximum(100f); // Valor máximo del eje Y derecho

    }




    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public frgGraficaBarra() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frgGraficaBarra.
     */
    // TODO: Rename and change types and number of parameters
    public static frgGraficaBarra newInstance(String param1, String param2) {
        frgGraficaBarra fragment = new frgGraficaBarra();
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
        return inflater.inflate(R.layout.fragment_frg_grafica_barra, container, false);
    }
}