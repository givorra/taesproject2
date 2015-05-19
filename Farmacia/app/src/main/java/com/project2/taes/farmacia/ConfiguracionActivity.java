package com.project2.taes.farmacia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;
import java.util.TimeZone;


public class ConfiguracionActivity extends ActionBarActivity {

    boolean guardado;
    boolean campoCambiado;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        guardado = false;
        campoCambiado = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        EditText usuarioText = (EditText) findViewById(R.id.usuario);
        EditText contrasenyaText = (EditText) findViewById(R.id.contrasenya);
        EditText servidorText = (EditText) findViewById(R.id.servidor);

        TextWatcher watcher = new TextWatcher() {
            public void afterTextChanged(Editable s) {
                campoCambiado = true;
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        };
        usuarioText.addTextChangedListener(watcher);
        contrasenyaText.addTextChangedListener(watcher);
        servidorText.addTextChangedListener(watcher);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_configuracion, menu);
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
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(!guardado && campoCambiado) {
            new AlertDialog.Builder(this)
                    .setMessage("Está seguro que quiere salir? Los cambios no quedarán guardados.")
                    .setCancelable(false)
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ConfiguracionActivity.this.finish();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else
            ConfiguracionActivity.this.finish();
    }


    /*
    private long consultaIdCalendario() {
        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String[] projection = new String[] {
                CalendarContract.Calendars._ID,
                CalendarContract.Calendars.ACCOUNT_NAME,
                CalendarContract.Calendars.CALENDAR_DISPLAY_NAME,
                CalendarContract.Calendars.NAME,
                CalendarContract.Calendars.CALENDAR_COLOR
        };

        Cursor calendarCursor = managedQuery(uri, projection, null, null, null);
        return Long.parseLong(projection[0]);
    } */

    public void guardar(View view) {
        guardado = true;
        new AlertDialog.Builder(this)
                .setMessage("Guardado con éxito.")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ConfiguracionActivity.this.finish();
                    }
                })
                .show();
    }
}
