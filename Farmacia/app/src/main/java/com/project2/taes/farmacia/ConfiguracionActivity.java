package com.project2.taes.farmacia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
    boolean usuarioCambiado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        guardado = false;
        usuarioCambiado = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        EditText myTextBox = (EditText) findViewById(R.id.usuario);
        myTextBox.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                usuarioCambiado = true;
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(!guardado && usuarioCambiado) {
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

    public void anyadirEventoCalendar(View view) {
        long calID = 1;
        long inicioMillis = 0;
        long finalMillis = 0;
        Calendar tiempoInicio = Calendar.getInstance();
        tiempoInicio.set(2015, 9, 14, 7, 30);
        inicioMillis = tiempoInicio.getTimeInMillis();
        Calendar tiempoFinal = Calendar.getInstance();
        tiempoFinal.set(2015, 9, 14, 8, 45);
        finalMillis = tiempoFinal.getTimeInMillis();

        ContentResolver cr = getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.DTSTART, inicioMillis);
        values.put(CalendarContract.Events.DTEND, finalMillis);
        values.put(CalendarContract.Events.TITLE, "Prueba");
        values.put(CalendarContract.Events.DESCRIPTION, "Evento de prueba");
        values.put(CalendarContract.Events.CALENDAR_ID, calID);
        TimeZone timeZone = TimeZone.getDefault();
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());
        Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);
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
    }
}
