package com.project2.taes.farmacia;

import android.graphics.Bitmap;

/**
 * Created by Matt on 20/05/2015.
 */
public class ReporteGastos {

    public String titulo;
    public Float importe;
    public Bitmap image;

    public ReporteGastos(String titulo, Float importe, Bitmap image) {
        this.titulo = titulo;
        this.importe = importe;
        this.image = image;
    }
}
