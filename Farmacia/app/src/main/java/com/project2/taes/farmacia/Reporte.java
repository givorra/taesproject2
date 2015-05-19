package com.project2.taes.farmacia;
import android.content.Context;

import com.project2.taes.farmacia.classes.Producto;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Matt on 18/05/2015.
 */

public class Reporte implements java.io.Serializable {

    public String nombreMedico;
    public String areaVisita;
    public String lugar;
    public boolean acompanyante;
    public Date fecha;
    public String Observaciones;
    public ArrayList<Producto> productos;
    public Reporte(String areaVisita, String nombreMedico, String lugar, boolean acompanyante, String observaciones, Date fecha) {
        this.areaVisita = areaVisita;
        this.nombreMedico = nombreMedico;
        this.lugar = lugar;
        this.acompanyante = acompanyante;
        Observaciones = observaciones;
        this.fecha = fecha;
        this.productos = Producto.getItems();
    }

    public void guardar(Context context) {
        ReportesSerializables reportes = new ReportesSerializables();
        reportes.leer(context);
        reportes.addReporte(this);
        reportes.guardar(context);
    }
}