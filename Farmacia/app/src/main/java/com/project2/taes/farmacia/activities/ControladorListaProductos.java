package com.project2.taes.farmacia;

import android.app.ListActivity;
import android.os.Bundle;


import com.project2.taes.farmacia.R;
import com.project2.taes.farmacia.adapters.AdapterListaProducto;
import com.project2.taes.farmacia.classes.Producto;

import java.util.ArrayList;

public class ControladorListaProductos extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //Llamada al evento de la clase padre
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productos_list);

        // Obtenemos la lista de productos
        ArrayList<Producto> productos = Producto.getItems();
        // Entregamos la lista de productos al adaptador de la lista en el Layout productos_list.xml
        setListAdapter(new AdapterListaProducto(this, R.layout.productos_list, productos));
    }
}