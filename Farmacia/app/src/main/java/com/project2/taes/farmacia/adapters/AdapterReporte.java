package com.project2.taes.farmacia.adapters;

import java.util.ArrayList;
import java.util.Date;

import android.content.Context;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project2.taes.farmacia.R;
import com.project2.taes.farmacia.ReportesSerializables;

/**
 * Created by DanielAlejandro on 07/05/2015.
 */
public class AdapterReporte extends RecyclerView.Adapter<AdapterReporte.ViewHolder> {

    private ReportesSerializables reportes;
    private OnItemClickListener mItemClickListener;
    private Context context;
    public AdapterReporte(Context context) {
        leerListaReportes();
    }

    public void leerListaReportes() {
        reportes = new ReportesSerializables();
        reportes.guardarPlantilla(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reporte,parent,false);

        ViewHolder vh=new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.nombreMedico.setText(reportes.getReportes().get(position).nombreMedico);
        holder.areaVisita.setText(reportes.getReportes().get(position).areaVisita);
        holder.fecha.setText(reportes.getReportes().get(position).fecha.toString());
    }







    @Override
    public int getItemCount() {
        return reportes.getReportes().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nombreMedico;
        public TextView areaVisita;
        public TextView fecha;
        public ViewHolder(View itemView) {
            super(itemView);

           nombreMedico= (TextView) itemView.findViewById(R.id.txtNombreMedico);
           areaVisita= (TextView) itemView.findViewById(R.id.txtAreaVisita);
           fecha = (TextView) itemView.findViewById(R.id.txtFecha);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getPosition());
            }
        }
    }


    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
