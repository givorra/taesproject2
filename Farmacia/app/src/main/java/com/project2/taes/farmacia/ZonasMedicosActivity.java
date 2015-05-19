package com.project2.taes.farmacia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.ArrayList;


public class ZonasMedicosActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas_medicos);
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
        SpinnerDefecto();

    }

    private Spinner spZonasMedicos;
    private ListView lvMedicos;
    // Chapuza para que no muestre los medicos en la primera carga
    private boolean primeraVez;

    private void SpinnerDefecto()
    {
        spZonasMedicos = (Spinner) findViewById(R.id.zonas);
        primeraVez = true;
        // contexto, datos, apariencia
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.zonas, R.layout.spinner_zonas_item);

        spZonasMedicos.setAdapter(new NothingSelectedSpinnerAdapter(adapter,R.layout.contact_spinner_row_nothing_selected,this));

        spZonasMedicos.setOnItemSelectedListener
                (new OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        if (!primeraVez) {
                            // Hay que enviar la zona para saber que medicos mostrar
                            cargarMedicosZona(parentView.getSelectedItem().toString());
                            lvMedicos.setVisibility(View.VISIBLE);
                        } else primeraVez = false;
                    }

                    public void onNothingSelected(AdapterView<?> parentView) {
                    }
                });
    }

    private void cargarMedicosZona(String zona)
    {
        lvMedicos = (ListView) findViewById(R.id.medicos);
        ItemMedicoAdapter adapter;
        String medicos[];
        ArrayList<Medico> itemsMedicos = new ArrayList<>();
        //String arr[] = getResources().getStringArray(R.array.medicosA);
        //if (arr[0].equals(arr[1]))
        //return;
        switch (zona)
        {
            // Parametros: contexto donde se esta utilizando la ista, datos, recurso que define el aspecto visual de la lista
            case "Alicante":
                medicos = getResources().getStringArray(R.array.medicosA);
                break;
            case "San Juan":
                medicos = getResources().getStringArray(R.array.medicosB);
                break;
            case "Campello":
                medicos = getResources().getStringArray(R.array.medicosC);
                break;
            case "Villa Joyosa":
                medicos = getResources().getStringArray(R.array.medicosD);
                break;
            case "San Vicente":
                medicos = getResources().getStringArray(R.array.medicosE);
                break;
            default:
                medicos = null;
        }

        Medico med;
        String image;
        String n_colegiado="Nº colegiado: 00000";
        int nAux=0;

        for (int i = 0; i < 11; i++)
        {
            nAux+=1;
            image = "drawable/medico"+i;
            med = new Medico(i+1, medicos[i], n_colegiado+nAux, image);
            itemsMedicos.add(med);
        }

        adapter = new ItemMedicoAdapter(this, itemsMedicos);
        lvMedicos.setAdapter(adapter);
        lvMedicos.setOnItemClickListener
                (new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        //int posicion = parentView.getSelectedItemPosition();
                        Medico medico = (Medico)parentView.getAdapter().getItem(position);//lvMedicos.getSelectedItem();
                        Intent act = new Intent(getBaseContext(), MedicoInfoActivity.class);
                        act.putExtra("sampleObject", medico);
                        startActivity(act);
                    }
                });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent i = new Intent(this, ConfiguracionActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
