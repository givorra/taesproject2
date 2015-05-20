package com.project2.taes.farmacia;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;


public class AnyadirReporteActivity extends ActionBarActivity {

    TextView nomMedico;
    EditText tnomMedico;

    TextView area;
    EditText tarea;

    TextView lugar;
    EditText tlugar;

    TextView acompanyante;
    EditText tacompanyante;

    TextView fecha;
    EditText tfecha;

    TextView observaciones;
    EditText tobservaciones;

    Button botGuardar;
    Button botCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyadir_reporte);

        TextView nomMedico=(TextView) findViewById(R.id.lbMedico);
        EditText tnomMedico=(EditText) findViewById(R.id.txMedico);

        TextView area= (TextView) findViewById(R.id.lbArea);
        EditText tarea= (EditText)findViewById(R.id.txArea);

        TextView lugar=(TextView) findViewById(R.id.lbArea);
        EditText tlugar=(EditText) findViewById(R.id.txArea);

        TextView acompanyante=(TextView) findViewById(R.id.lbAcompanyante);
        EditText tacompanyante=(EditText) findViewById(R.id.txtAcompanayante);

        TextView fecha=(TextView) findViewById(R.id.lbFecha);
        EditText tfecha=(EditText) findViewById(R.id.txFecha);

        TextView observaciones=(TextView) findViewById(R.id.lbObservaciones);
        EditText tobservaciones=(EditText) findViewById(R.id.txObservaciones);

        Button botGuardar=(Button)findViewById(R.id.btGuardar);
        Button botCancelar=(Button)findViewById(R.id.btCancelar);

    }

    public void guardar(View view){
        String med=tarea.getText().toString();
        String are=tarea.getText().toString();
        String lug=tlugar.getText().toString();
        Boolean acom=tacompanyante.getText().toString();
        Date fec=tfecha.get();
        String obs=tobservaciones.getText().toString();


        Reporte reporte=new Reporte(are, med, lug, acom, obs, fec);
    }

    @Override
    public OnClick(View view){
        reporte.guardar(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_anyadir_reporte, menu);
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
