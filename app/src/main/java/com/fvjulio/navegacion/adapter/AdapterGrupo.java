package com.fvjulio.navegacion.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.fvjulio.navegacion.R;
import com.fvjulio.navegacion.modelo.MGrupo;

import java.util.ArrayList;

public class AdapterGrupo extends RecyclerView.Adapter<AdapterGrupo.viewHolderGrupo> {

    private ArrayList <MGrupo> lista;
    private Bundle paquete;

    private TextView txtUsuario;



    public AdapterGrupo(ArrayList<MGrupo> lista){

        this.lista=lista;
    }

    @NonNull
    @Override
    public AdapterGrupo.viewHolderGrupo onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grupo,null,false);

        return new viewHolderGrupo(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGrupo.viewHolderGrupo holder, int position) {

        holder.gpo=lista.get(position);
        holder.txtNombreAsignatura.setText(holder.gpo.getNombreAsig());
        holder.txtClaveGrupo.setText(holder.gpo.getClave());
        holder.txtNombreDocente.setText(holder.gpo.getNombreDoc());
        holder.txtPeriodo.setText(holder.gpo.getNombrePer());
        paquete = new Bundle();





        holder.btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paquete.putSerializable("objeto" , holder.gpo);
                clicEditar(v);
            }
        });
        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paquete.putSerializable("objeto" , holder.gpo);
                clicEliminar(v);
            }
        });
        //holder.txtOp.setText(gpo.getOp()==1?"Ordinario":gpo.getOp()==2?"Recursamiento":"Especial");

        holder.btnver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clicVer(v,holder.gpo);
            }
        });

    }

    private void clicEliminar(View v) {
        NavController nav = Navigation.findNavController(v);
        paquete.putInt("op",1);
        nav.navigate(R.id.action_frg_grupos_to_frg_CRUD_gpo,paquete);
    }

    private void clicEditar(View v) {
        NavController nav = Navigation.findNavController(v);
        paquete.putInt("op",2);
        nav.navigate(R.id.action_frg_grupos_to_frg_CRUD_gpo,paquete);

    }

    private void clicVer(View v, MGrupo gpo) {
        NavController nav = Navigation.findNavController(v);
        nav.navigate(R.id.action_frg_grupos_to_frg_Estudiante);



    }

    @Override
    public int getItemCount() {return lista.size();
    }

    public void filtro(ArrayList<MGrupo> filtrados){
        this.lista=filtrados;
        notifyDataSetChanged();
    }

    public class viewHolderGrupo extends RecyclerView.ViewHolder {

        Button btnver;
        ImageView btnEditar,btnEliminar;
         TextView txtNombreAsignatura, txtNombreDocente,txtClaveGrupo,txtPeriodo,txtOp;
         MGrupo gpo;

        public viewHolderGrupo(@NonNull View itemView) {
            super(itemView);
            txtNombreAsignatura = itemView.findViewById(R.id.item_gpo_txtNombre);
            txtClaveGrupo = itemView.findViewById(R.id.item_gpo_clave);
            txtNombreDocente = itemView.findViewById(R.id.item_gpo_docente);
            txtPeriodo = itemView.findViewById(R.id.item_gpo_periodo);
            txtOp= itemView.findViewById(R.id.item_gpo_op2);
            btnver = itemView.findViewById(R.id.item_gpo_btnver);
            txtUsuario=itemView.findViewById(R.id.grupos_txtnombre);
            btnEditar=itemView.findViewById(R.id.it_btn_editar);
            btnEliminar=itemView.findViewById(R.id.it_btn_eliminar);

        }
    }


}
