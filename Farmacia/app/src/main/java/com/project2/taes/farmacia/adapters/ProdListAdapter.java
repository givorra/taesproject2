package com.project2.taes.farmacia.adapters;

import android.app.Activity;
import android.app.LauncherActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.project2.taes.farmacia.classes.ProdComer;

import java.util.List;

/**
 * Created by FranciscoJavier on 13/05/2015.
 */
public class ProdListAdapter extends BaseAdapter {

    private Activity activity;
    private List<ProdComer> listProd;


    public ProdListAdapter(Activity activity, List<ProdComer> listLibros){
        this.activity = activity;
        this.listProd = listLibros;
    }

    public int getCount() {
        return listProd.size();
    }

    public Object getItem(int position) {
        return listProd.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LauncherActivity.ListItem lstItem = new LauncherActivity.ListItem(activity, listProd.get(position) );

        return lstItem;
    }

}
