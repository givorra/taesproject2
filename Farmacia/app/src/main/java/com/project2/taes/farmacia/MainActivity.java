package com.project2.taes.farmacia;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.project2.taes.farmacia.R;

import com.project2.taes.farmacia.adapters.NavDrawerListAdapter;
import com.project2.taes.farmacia.fragments.ReporteFragment;
import com.project2.taes.farmacia.slidingmenu.NavDrawerItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


public class MainActivity extends ActionBarActivity {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    // nav drawer title
    private CharSequence mDrawerTitle;
    // used to store app title
    private CharSequence mTitle;
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
    private ReporteFragment reportesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        mTitle = mDrawerTitle = "Reportes";
        getSupportActionBar().setTitle(mTitle);
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
        navDrawerItems = new ArrayList<NavDrawerItem>();
        // adding nav drawer items to array
        // Medicos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Calendario
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Rutas
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1), true, "22"));
        // Ayuda
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Configuracion
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        //Reportes Gastos
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        // Recycle the typed array
        navMenuIcons.recycle();
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
        reportesFragment = new ReporteFragment();
        // enabling action bar app icon and behaving it as toggle button


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name) { // nav drawer close - description for accessibility
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
                // calling onPrepareOptionsMenu() to show action bar icons
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        ImageButton fabButton = (ImageButton) findViewById(R.id.fab_image_button);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AnyadirReporteActivity.class);
                startActivity(i);
            }
        });

        ImageButton fabButtonEnviar = (ImageButton) findViewById(R.id.fab_image_enviar);
        fabButtonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(v.getContext())
                        .setMessage("Se enviarán los reportes pendientes.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Borrar reportes pendientes
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame_container ,reportesFragment).commit();
        }

        //GUARDAR
        //Reporte reporteNuevo = new Reporte("Matt", "Alicante");
        //reporteNuevo.guardar(getApplicationContext());

        //LEER
        //ReportesSerializables reportes = new ReportesSerializables();
        //reportes.leer(getApplicationContext());
        //reportes.getReportes();

        //BORRAR TODOS
        //reportes.borrarTodo(getApplicationContext());




    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        reportesFragment.refrescar();
    }

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    //Intent i = new Intent(this, ZonasMedicosActivity.class);
    //startActivity(i);


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(this, ConfiguracionActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     *      * Called when invalidateOptionsMenu() is triggered
     *      
     */

/*
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    */
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     *      * When using the ActionBarDrawerToggle, you must call it during
     *      * onPostCreate() and onConfigurationChanged()...
     *      
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void displayView(int position) {
        Intent i;
        switch (position) {
            case 0:
                i = new Intent(this, ZonasMedicosActivity.class);
                startActivity(i);
                break;
            case 1:
                new AlertDialog.Builder(this)
                        .setMessage("Se abrirá el calendario de este mes.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mostrarCalendario(0);
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
                break;
            case 2:
                new AlertDialog.Builder(this)
                        .setMessage("Tiene 22 rutas nuevas para importar al calendario.")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mostrarCalendarioRutasNuevas();
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
                break;
            case 4:
                 i = new Intent(this, ConfiguracionActivity.class);
                startActivity(i);
                break;
            case 5:
                i = new Intent(this, ReportesGastosActivity.class);
                startActivity(i);
            default:
                break;
        }

    }

    private void mostrarCalendarioRutasNuevas() {
        new AlertDialog.Builder(this)
                .setMessage("Quiere ver el calendario ahora?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse("content://com.android.calendar/time"));
                        startActivity(i);*/
                        anyadirEventoCalendar();
                        mostrarCalendario(1);
                    }
                })
                .setNegativeButton("Mas tarde", null)
                .show();
    }

    public void anyadirEventoCalendar() {
        long calID = 1;
        long inicioMillis = 0;
        long finalMillis = 0;
        Calendar tiempoInicio = Calendar.getInstance();
        tiempoInicio.set(2015, 5, 2, 7, 30);
        inicioMillis = tiempoInicio.getTimeInMillis();
        Calendar tiempoFinal = Calendar.getInstance();
        tiempoFinal.set(2015, 5, 2, 8, 45);
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

    private void mostrarCalendario(int mes) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, mes);
        long time = cal.getTime().getTime();
        Uri.Builder builder =
                CalendarContract.CONTENT_URI.buildUpon();
        builder.appendPath("time");
        builder.appendPath(Long.toString(time));
        Intent intent =
                new Intent(Intent.ACTION_VIEW, builder.build());
        startActivity(intent);
    }

}

