package com.project2.taes.farmacia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.project2.taes.farmacia.fragments.ReporteGastoFragment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ReportesGastosActivity extends ActionBarActivity {

    private String mCurrentPhotoPath;
    private final int REQUEST_IMAGE_CAPTURE = 1;
    ReporteGastoFragment reporteGastoFragment;

    EditText titulo;
    EditText importe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_gastos);

        FragmentManager fragmentManager = getSupportFragmentManager();
        reporteGastoFragment = (ReporteGastoFragment) fragmentManager.findFragmentById(R.id.fragment_gastos);

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

        ImageButton fabButton = (ImageButton) findViewById(R.id.fab_anyadirGastos);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.custom_dialog);
                dialog.setTitle("Reporte de gastos nuevo");

                titulo = (EditText) dialog.findViewById(R.id.editGastoTitulo);
                importe = (EditText) dialog.findViewById(R.id.editGastoImporte);


                Button bFoto = (Button) dialog.findViewById(R.id.buttonGastosFoto);
                bFoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        tomarFoto(v);
                    }
                });

                Button bOK = (Button) dialog.findViewById(R.id.buttonGastosGuardar);
                bOK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Bitmap myBitmap = decodificarImagen(500, 500);
                        reporteGastoFragment.add(new ReporteGastos(titulo.getText().toString(),
                               Float.parseFloat(importe.getText().toString()), myBitmap));
                        dialog.dismiss();

                    }
                });

                Button bCancelar = (Button) dialog.findViewById(R.id.buttonGastosCancelar);
                bCancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reportes_gastos, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            /*Bitmap foto = decodificarImagen(50,50);
            reporteGastoFragment.add(new ReporteGastos("Nuevo", 1.0f, foto));
            */
        }
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

    public void tomarFoto(View view) {

        Intent intentoTomarFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intentoTomarFoto.resolveActivity(getPackageManager()) != null) {
            File archivoFoto = null;
            try {
                archivoFoto = crearArchivoFoto();
            } catch (IOException ex) {
                Log.d("error", ex.getMessage());
            }
            if (archivoFoto != null) {
                intentoTomarFoto.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(archivoFoto));
                startActivityForResult(intentoTomarFoto, REQUEST_IMAGE_CAPTURE);
            }
        }

    }

    public File crearArchivoFoto() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nombreArchivo = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        //File storageDir = getExternalFilesDir(null);
        File image = File.createTempFile(
                nombreArchivo,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        //mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        mCurrentPhotoPath = image.getAbsolutePath();

        return image;
    }

    /**
     * Decodificacion de imagen
     * Si es para mostrar en una vista pasar dimensiones de dicha vista
     * Si es para hacer Parse pasar dimensiones maximas permitidas para la subida
     * @param anchura anchura de la imagen devuelta
     * @param altura altura de la imagen devuelta
     * @return bitmap decodificada
     */
    private Bitmap decodificarImagen(int anchura, int altura) {

        // Dimensiones del bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        //mCurrentPhotoPath es la ruta de la imagen a decodificar
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoAnchura = bmOptions.outWidth;
        int photoAltura = bmOptions.outHeight;

        // Determina la escala
        int scaleFactor = Math.min(photoAnchura/anchura, photoAltura/altura);

        // Decodificacion de la imagen
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);

        return bitmap;
    }
}

