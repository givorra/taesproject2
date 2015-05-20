package com.project2.taes.farmacia;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ReporteActivity extends ActionBarActivity {

    private int id_reporte;
    private ReportesSerializables reportes;
    private Reporte reporte;
    private TextView txtMedico;
    private TextView txtArea;
    private TextView txtLugar;
    private TextView txtAcompanyante;
    private TextView txtObservaciones;
    private TextView txtProductos;
    private TextView txtFecha;


    private android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        id_reporte = Integer.parseInt(getIntent().getStringExtra("idPosicion"));
        //Log.d("myTag", Integer.toString(id_reporte) + "*****************************************************************");
        reportes = new ReportesSerializables();
        reportes.guardarPlantilla(this);
        reporte = reportes.getReportes().get(id_reporte);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        txtMedico = (TextView) findViewById(R.id.txtNombreMedico);
        txtMedico.setText(reporte.nombreMedico);
        /*txtArea = (TextView) findViewById(R.id.txtAreaVisita);
        txtArea.setText(reporte.areaVisita);*/
        txtLugar = (TextView) findViewById(R.id.txtLugar);
        txtLugar.setText(reporte.lugar);
        txtFecha = (TextView) findViewById(R.id.txtFecha);
        txtFecha.setText(reporte.fecha.toString());
        txtAcompanyante = (TextView) findViewById(R.id.txtAcompanyante);
        if(reporte.acompanyante)
        {
            txtAcompanyante.setText("Si");
        }
        else txtAcompanyante.setText("No");
        txtObservaciones = (TextView) findViewById(R.id.txtObservaciones);
        txtObservaciones.setText(reporte.Observaciones);
        txtProductos = (TextView) findViewById(R.id.txtProductos);
        String cad_productos="";
        for(int i=0;i<reporte.productos.size();i++)
        {
            cad_productos += reporte.productos.get(i).getTitulo() + " (" + reporte.productos.get(i).getCantidad() + " unidades)";
            cad_productos += "\n";
        }
        txtProductos.setText(cad_productos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reporte, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
