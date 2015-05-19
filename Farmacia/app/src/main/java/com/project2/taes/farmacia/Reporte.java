package com.project2.taes.farmacia;
import android.content.Context;

import java.io.*;
import java.util.Date;

/**
 * Created by Matt on 18/05/2015.
 */

public class Reporte implements java.io.Serializable {

    String nombreMedico;
    String areaVisita;
    String lugar;
    String acompanyante;
    Date fecha;
    String Observaciones;

    public Reporte(String areaVisita, String nombreMedico, String lugar, String acompanyante, String observaciones, Date fecha) {
        this.areaVisita = areaVisita;
        this.nombreMedico = nombreMedico;
        this.lugar = lugar;
        this.acompanyante = acompanyante;
        Observaciones = observaciones;
        this.fecha = fecha;
    }

    public void guardar(Context context) {
        ReportesSerializables reportes = new ReportesSerializables();
        reportes.leer(context);
        reportes.addReporte(this);
        reportes.guardar(context);
    }
}