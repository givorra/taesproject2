import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.project2.taes.farmacia.R;
import com.project2.taes.farmacia.classes.Producto;

import java.util.ArrayList;

public class AdapterListaProductos extends ArrayAdapter<Producto> {

    private ArrayList<Producto> items;

    public AdapterListaProductos(Context context, int textViewResourceId, ArrayList<Producto> items){
        super(context,textViewResourceId,items);
        this.items=items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if (v==null){
            LayoutInflater vi=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.productos_list, null);
        }

        Producto producto= items.get(position);
        if(producto != null){
            TextView ttitulo=(TextView) v.findViewById(R.id.lbNombredelProducto);

            if(ttitulo != null){
                ttitulo.setText(producto.getTitulo());
            }
        }
        return v;
    }
}