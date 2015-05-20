package com.project2.taes.farmacia.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project2.taes.farmacia.R;
import com.project2.taes.farmacia.ReporteGastos;
import com.project2.taes.farmacia.utils.CircleImageView;

import java.util.ArrayList;

/**
 * Created by Matt on 20/05/2015.
 */
public class AdapterReporteGastos extends RecyclerView.Adapter<AdapterReporteGastos.ViewHolder> {

    private OnItemClickListener mItemClickListener;
    private Context context;
    private ArrayList<ReporteGastos> reportes;

    public AdapterReporteGastos(Context context) {
        reportes = new ArrayList<>();
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.recibo);
        ReporteGastos reporte = new ReporteGastos("Comida", 10.0f, bm);
        ReporteGastos reporte2 = new ReporteGastos("Cena", 5.0f, bm);
        reportes.add(reporte);
        reportes.add(reporte2);
        this.context = context;
    }

    public ArrayList<ReporteGastos> getReportes() {
        return reportes;
    }

    public void add(ReporteGastos reporte) {
        reportes.add(reporte);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reporte_gastos,parent,false);

        ViewHolder vh=new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.titulo.setText(reportes.get(position).titulo);
        holder.importe.setText(reportes.get(position).importe.toString());
        holder.image.setImageBitmap(reportes.get(position).image);
    }







    @Override
    public int getItemCount() {
        return reportes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView titulo;
        public TextView importe;
        public CircleImageView image;
        public ViewHolder(View itemView) {
            super(itemView);

            titulo = (TextView) itemView.findViewById(R.id.txtReporteGasto);
            importe = (TextView) itemView.findViewById(R.id.importeReporteGasto);
            image = (CircleImageView) itemView.findViewById(R.id.circleImage);
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
