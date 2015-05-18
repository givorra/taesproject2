package com.project2.taes.farmacia;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Matt on 18/05/2015.
 */
public class ReportesSerializables implements java.io.Serializable {

    final transient String fileName = "reporte";
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

    public void borrarTodo(Context context) {
        reportes.clear();
        guardar(context);
    }
}
