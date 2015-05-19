package com.project2.taes.farmacia;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.project2.taes.farmacia.utils.CircleImageView;


public class MedicoInfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medico_info);

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

        // Obteniendo medico seleccionado
        Intent i = getIntent();
        Medico medico = (Medico)i.getSerializableExtra("sampleObject");

        CircleImageView image = (CircleImageView)findViewById(R.id.foto_medico);
        int imageResource = getResources().getIdentifier(medico.getFoto(), null, getPackageName());
        image.setImageDrawable(getResources().getDrawable(imageResource));

        TextView nombre = (TextView) findViewById(R.id.nombre_medico);
        nombre.setText(medico.getNombre());

        TextView tipo = (TextView) findViewById(R.id.n_colegiado);
        tipo.setText(medico.getN_colegiado());
   
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medico_info, menu);
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
