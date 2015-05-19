package com.project2.taes.farmacia.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project2.taes.farmacia.R;
import com.project2.taes.farmacia.ReporteActivity;
import com.project2.taes.farmacia.adapters.AdapterReporte;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ReporteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ReporteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReporteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private AdapterReporte adapter;
    private RecyclerView listaReportes;
    private RecyclerView.LayoutManager manager;
    //private Intent intent;

    //private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReporteFragment.
     */


    public ReporteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_reporte,container,false);
        listaReportes= (RecyclerView) v.findViewById(R.id.listaReportes);
        manager=new LinearLayoutManager(getActivity());
        listaReportes.setLayoutManager(manager);
        adapter=new AdapterReporte(getActivity());
        listaReportes.setAdapter(adapter);
        adapter.SetOnItemClickListener(new AdapterReporte.OnItemClickListener() {


            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(v.getContext(), ReporteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idPosicion", Integer.toString(position));
                intent.putExtras(bundle);
                //Log.d("myTag", Integer.toString(position) + "*****************************************************************");
                startActivity(intent);
            }
        });


        return v;
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */



}
