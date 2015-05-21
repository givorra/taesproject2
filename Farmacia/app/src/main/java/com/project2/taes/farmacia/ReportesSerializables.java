package com.project2.taes.farmacia;

import android.content.Context;

import com.project2.taes.farmacia.classes.Producto;

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

    /*++++++++++ CODIGO GACEL +++++++++*/
    public void setReportes(ArrayList<Reporte> reportes){this.reportes = reportes;}
    /*++++++++++ CODIGO GACEL +++++++++*/

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
            Reporte r = new Reporte("Alicante", "Maria", "Una cafeteria", false, "Me tocó pagar a mi", new Date(2011, 4, 7));
            Reporte r2 = new Reporte("Alicante", "Pepe", "Mi casa", true, "Tuve que esperar una hora", new Date(2007, 2,2), "Maquinilla de afeitar (2 unidades)");
            //Reporte r3 = new Reporte("area del vecino", "Juan", "en la calle", true, "me pago el taxi", new Date(2008,8,8));
            reportes.add(r);
            reportes.add(r2);
            //reportes.add(r3);
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
