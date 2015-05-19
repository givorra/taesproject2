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

        nomMedico=(TextView) findViewById(R.id.lbMedico);
        tnomMedico=(EditText) findViewById(R.id.txMedico);

        area= (TextView) findViewById(R.id.lbArea);
        tarea= (EditText)findViewById(R.id.txArea);

        lugar=(TextView) findViewById(R.id.lbArea);
        tlugar=(EditText) findViewById(R.id.txArea);

        acompanyante=(TextView) findViewById(R.id.lbAcompanyante);
        tacompanyante=(EditText) findViewById(R.id.txtAcompanayante);

        fecha=(TextView) findViewById(R.id.lbFecha);
        tfecha=(EditText) findViewById(R.id.txFecha);

        observaciones=(TextView) findViewById(R.id.lbObservaciones);
        tobservaciones=(EditText) findViewById(R.id.txObservaciones);

        botGuardar=(Button)findViewById(R.id.btGuardar);
        botCancelar=(Button)findViewById(R.id.btCancelar);

    }

    public void guardar(View view){
        String med=tarea.getText().toString();
        String are=tarea.getText().toString();
        String lug=tlugar.getText().toString();
        Boolean acom=tacompanyante.getText().toString();
        Date fec=tfecha.getDate().toString();
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
