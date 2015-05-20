package com.project2.taes.farmacia;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AnyadirReporteActivity extends ActionBarActivity implements View.OnClickListener {

    TextView nomMedico;
    EditText tnomMedico;

    TextView area;
    EditText tarea;

    TextView lugar;
    EditText tlugar;

    TextView acompanyante;
    CheckBox tacompanyante;

    TextView fecha;
    EditText tfecha;

    TextView observaciones;
    EditText tobservaciones;

    TextView productos;
    EditText tproductos;

    Button botGuardar;
    Button botCancelar;

    private DatePickerDialog datePickerDialog;

    private DateFormat dateFormatter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyadir_reporte);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        nomMedico = (TextView) findViewById(R.id.lbMedico);
        tnomMedico = (EditText) findViewById(R.id.txMedico);

        area = (TextView) findViewById(R.id.lbArea);
        tarea = (EditText) findViewById(R.id.txArea);

        lugar = (TextView) findViewById(R.id.lbArea);
        tlugar = (EditText) findViewById(R.id.txArea);

        acompanyante = (TextView) findViewById(R.id.lbAcompanyante);
        tacompanyante = (CheckBox) findViewById(R.id.checkBox);

        fecha = (TextView) findViewById(R.id.lbFecha);
        tfecha = (EditText) findViewById(R.id.txFecha);
        tfecha.setOnClickListener(this);

        productos = (TextView) findViewById(R.id.lbProductos);
        tproductos = (EditText) findViewById(R.id.txProductos);

        observaciones = (TextView) findViewById(R.id.lbObservaciones);
        tobservaciones = (EditText) findViewById(R.id.txObservaciones);



        botGuardar = (Button) findViewById(R.id.btGuardar);
        botCancelar = (Button) findViewById(R.id.btCancelar);

        Calendar newCalendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tfecha.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));





    }

    public void guardar(View view) {
        String med = tnomMedico.getText().toString();
        String are = tarea.getText().toString();
        String lug = tlugar.getText().toString();
        Boolean acom = tacompanyante.isChecked();

        String sfecha=tfecha.getText().toString();
        Date fec=null;
        try{
            fec=dateFormatter.parse(sfecha);
        }catch (Exception e){
            System.out.println("Unable to parse date stamp");
        }

        String prod = tproductos.getText().toString();
        String obs = tobservaciones.getText().toString();



        Reporte reporte=new Reporte(are, med, lug, acom, obs, fec, prod);
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

    @Override
    public void onClick(View view) {
        if (view == tfecha) {
            datePickerDialog.show();
        }
    }
}
