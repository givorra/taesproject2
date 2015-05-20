package com.project2.taes.farmacia.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.project2.taes.farmacia.R;
import com.project2.taes.farmacia.ReporteActivity;
import com.project2.taes.farmacia.ReporteGastos;
import com.project2.taes.farmacia.adapters.AdapterReporte;
import com.project2.taes.farmacia.adapters.AdapterReporteGastos;

/**
 * Created by Matt on 20/05/2015.
 */
public class ReporteGastoFragment extends Fragment {
    private AdapterReporteGastos adapter;
    private RecyclerView listaReportes;
    private RecyclerView.LayoutManager manager;

    public ReporteGastoFragment() {
        // Required empty public constructor
    }

    public void add(ReporteGastos reporte) {
        adapter.add(reporte);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_reporte,container,false);
        listaReportes= (RecyclerView) v.findViewById(R.id.listaReportes);
        manager=new LinearLayoutManager(getActivity());
        listaReportes.setLayoutManager(manager);
        adapter=new AdapterReporteGastos(getActivity());
        listaReportes.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterReporteGastos.OnItemClickListener() {


            @Override
            public void onItemClick(View v, int position) {
                final Dialog dialog = new Dialog(v.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.image_popup);
                ImageView image = (ImageView) dialog.findViewById(R.id.popupImage);
                image.setImageBitmap(adapter.getReportes().get(position).image);
                dialog.show();
            }
        });
        return v;
    }
}
