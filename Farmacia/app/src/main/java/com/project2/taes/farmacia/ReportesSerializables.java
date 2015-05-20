package com.project2.taes.farmacia;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Matt on 18/05/2015.
 */
public class ReportesSerializables implements java.io.Serializable {

    final transient String fileName = "reportes.txt";
    ArrayList<Reporte> reportes;

    public ReportesSerializables() {
        reportes = new ArrayList<>();
    }

    public ArrayList<Reporte> getReportes() {
        return reportes;
    }

    public void addReporte(Reporte reporte) {
        reportes.add(reporte);
    }

    public boolean guardar(Context context) {

        try {
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(reportes);
            os.close();
            fos.close();
        }
        catch(Exception ex) {
            return false;
        }
        return true;
    }

    public boolean leer(Context context) {
        try {
            FileInputStream fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            reportes = (ArrayList<Reporte>) is.readObject();
            is.close();
            fis.close();
        } catch(Exception ex) {
            return false;
        }
        return true;
    }

    public boolean guardarPlantilla(Context context) {
        try {
            Reporte r = new Reporte("area de mi casa", "Pepe", "una cafeteria", false, "me toco pagar a mi", new Date(2011, 4, 7), "Paracetamol x3");
            Reporte r2 = new Reporte("un area cualquiera", "Manolo", "mi casa", true, "tuve que esperar una hora", new Date(2007, 2,2),"Aspirina x2");
            reportes.add(r);
            reportes.add(r2);
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(reportes);
            os.close();
            fos.close();
        }
        catch(Exception ex) {
            return false;
        }
        return true;
    }

    public void borrarTodo(Context context) {
        reportes.clear();
        guardar(context);
    }
}
