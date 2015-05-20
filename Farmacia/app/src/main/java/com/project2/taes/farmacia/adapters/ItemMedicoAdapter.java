package com.project2.taes.farmacia.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project2.taes.farmacia.Medico;
import com.project2.taes.farmacia.R;
import com.project2.taes.farmacia.utils.CircleImageView;

import java.util.ArrayList;

/**
 * Created by Andrea on 19/05/2015.
 */
public class ItemMedicoAdapter extends BaseAdapter
{
    protected Activity activity;
    protected ArrayList<Medico> items;

    public ItemMedicoAdapter(Activity activity, ArrayList<Medico> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View vi=contentView;

        if(contentView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vi = inflater.inflate(R.layout.listview_medicos_item, null);
        }

        Medico item = items.get(position);

        CircleImageView image = (CircleImageView) vi.findViewById(R.id.foto_medico);
        int imageResource = activity.getResources().getIdentifier(item.getFoto(), null, activity.getPackageName());
        image.setImageDrawable(activity.getResources().getDrawable(imageResource));

        TextView nombre = (TextView) vi.findViewById(R.id.nombre_medico);
        nombre.setText(item.getNombre());

        TextView tipo = (TextView) vi.findViewById(R.id.n_colegiado);
        tipo.setText(item.getN_colegiado());

        return vi;
    }

    /*
    setOnItemClickListener
            (new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parentView, View selectedItemView, int position, long id) {
            Intent act = new Intent(getBaseContext(), MedicoInfoActivity.class);
            startActivity(act);
        }
    });*/
}
