package com.project2.taes.farmacia.adapters;

import java.util.ArrayList;
import java.util.Date;

import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project2.taes.farmacia.R;

/**
 * Created by DanielAlejandro on 07/05/2015.
 */
public class AdapterReporte extends RecyclerView.Adapter<AdapterReporte.ViewHolder> {

    private ArrayList<Reporte> reportes;
    private OnItemClickListener mItemClickListener;

    public AdapterReporte() {
        getListaReportes();
    }

    public void getListaReportes() {
        ArrayList<String> promocionales1 = new ArrayList<String>();
        promocionales1.add("boligrafo BIC especial");
        promocionales1.add("camiseta promocional");
        ArrayList<String> promocionales2 = new ArrayList<String>();
        promocionales2.add("Llavero promocional");
        promocionales2.add("Agenda promocional");
        Producto p = new Producto(1, "Producto1", promocionales1);
        Producto p2 = new Producto(2, "Producto2", promocionales2);
        Producto p3 = new Producto(3, "Producto3", null);
        ArrayList<Producto> listaProd1 = new ArrayList<Producto>();
        ArrayList<Producto> listaProd2 = new ArrayList<Producto>();
        listaProd1.add(p);
        listaProd1.add(p2);
        listaProd2.add(p3);
        reportes = new ArrayList<Reporte>();
        Reporte r = new Reporte(false, new Date(2015,1,4), listaProd1,"Pepito", "Alicante");
        Reporte r2 = new Reporte(true, new Date(2014,4,3), listaProd2, "Manuel", "Valencia" );
        reportes.add(r);
        reportes.add(r2);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reporte,parent,false);

        ViewHolder vh=new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.nombreMedico.setText(reportes.get(position).nombreMedico);
        holder.areaVisita.setText(reportes.get(position).areaVisita);
        holder.fecha.setText(reportes.get(position).fecha.toString());
    }



    public class Producto {
        private int codigo;
        private String nombre;
        private ArrayList<String> promocionales;

        public Producto() {


        }

        public Producto(int cod, String nombre, ArrayList<String> promocionales) {
            this.codigo = cod;
            this.nombre = nombre;
            this.promocionales = promocionales;
        }

        public void init(int cod, String nombre, ArrayList<String> promocionales) {
            this.codigo = cod;
            this.nombre = nombre;
            this.promocionales = promocionales;
        }
    }

    public class Reporte {
        private boolean conAcompanyante;
        private Date fecha;
        private ArrayList<Producto> productos;
        private String nombreMedico; //pendiente
        private String areaVisita; //pendiente

        public Reporte() {


        }

        public Reporte(boolean acompanyante, Date fecha, ArrayList<Producto> prod, String medico, String area) {
            this.conAcompanyante = acompanyante;
            this.fecha = fecha;
            this.productos = prod;
            this.nombreMedico = medico;
            this.areaVisita = area;
        }

        public void init(boolean acompanyante, Date fecha, ArrayList<Producto> prod, String medico, String area) {
            this.conAcompanyante = acompanyante;
            this.fecha = fecha;
            this.productos = prod;
            this.nombreMedico = medico;
            this.areaVisita = area;

        }
    }



    @Override
    public int getItemCount() {
        return reportes.size();
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
