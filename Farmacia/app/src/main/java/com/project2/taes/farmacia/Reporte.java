package com.project2.taes.farmacia;
import android.content.Context;

import java.io.*;

/**
 * Created by Matt on 18/05/2015.
 */

public class Reporte implements java.io.Serializable {

    String nombre;
    String zona;

    public Reporte(String nombre, String zona) {
        this.nombre = nombre;
        this.zona = zona;
    }

    public void guardar(Context context) {
        ReportesSerializables reportes = new ReportesSerializables();
        reportes.leer(context);
        reportes.addReporte(this);
        reportes.guardar(context);
    }
}