package com.fvjulio.navegacion.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.fvjulio.navegacion.R;
import com.fvjulio.navegacion.modelo.MEstudiante;
import com.fvjulio.navegacion.modelo.MGrupo;

import java.util.ArrayList;

public class AdapterEstudiante extends RecyclerView.Adapter<AdapterEstudiante.ViewHolderEstudiante> {
    private ArrayList<MEstudiante> lista;


    public AdapterEstudiante(ArrayList<MEstudiante> lista) {
        this.lista = lista;
    }


    @NonNull
    @Override
        public AdapterEstudiante.ViewHolderEstudiante onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estudiantes,null,false);

        return new AdapterEstudiante.ViewHolderEstudiante(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEstudiante.ViewHolderEstudiante holder, int position) {
        MEstudiante estu=lista.get(position);
        holder.txtNombre.setText(estu.getApp() + " "+estu.getApm()+ " " + estu.getNombre());
        holder.txtcal1.setText(estu.getCal1()+ "");
        holder.txtcal2.setText(estu.getCal2()+ "");
        holder.txtcal3.setText(estu.getCal3()+ "");
        holder.txtpromedio.setText(estu.getPromedio()+"");
        holder.txtmat.setText(estu.getMatricula()+"");
        holder.btnPastel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController nav= Navigation.findNavController(v);
                nav.navigate(R.id.action_frg_Estudiante_to_frgGraficaPastel);
            }
        });
        holder.btnBarras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController nav= Navigation.findNavController(v);
                nav.navigate(R.id.action_frg_Estudiante_to_frgGraficaBarra);
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.lista.size();
    }

    public void filtro(ArrayList<MEstudiante> filtrados){
        this.lista=filtrados;
        notifyDataSetChanged();
    }

    public class ViewHolderEstudiante extends RecyclerView.ViewHolder {

        TextView txtNombre,txtcal1,txtcal2,txtcal3,txtpromedio,txtmat;
        ImageView btnBarras, btnPastel;

        public ViewHolderEstudiante(@NonNull View itemView) {
            super(itemView);
            txtNombre=itemView.findViewById(R.id.item_estu_txtnombre);
            txtcal1=itemView.findViewById(R.id.item_estud_txtparcial1);
            txtcal2=itemView.findViewById(R.id.item_estud_txtparcial2);
            txtcal3=itemView.findViewById(R.id.item_estud_txtparcial3);
            txtpromedio=itemView.findViewById(R.id.item_estud_txtpromedio);
            txtmat=itemView.findViewById(R.id.item_estud_txtmatricula);
            btnBarras=itemView.findViewById(R.id.item_estud_btnbarras);
            btnPastel=itemView.findViewById(R.id.item_estud_btnpastel);


        }
    }
}
