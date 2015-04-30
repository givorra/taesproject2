package com.project2.taes.farmacia;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        ImageView icon = new ImageView(this); //Con ello creamos el icono
        icon.setImageResource(R.drawable.ic_action_floatingbutton);

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .build();

        //Con esto creamos cada boton del submenu
        ImageView iconNewReport = new ImageView(this);
        iconNewReport.setImageResource(R.drawable.ic_action_report);

        ImageView iconSendReport = new ImageView(this);
        iconSendReport.setImageResource(R.drawable.ic_action_send);

        ImageView iconNewProduct = new ImageView(this);
        iconNewProduct.setImageResource(R.drawable.ic_action_product);

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        SubActionButton buttoniconNewReport = itemBuilder.setContentView(iconNewReport).build();
        SubActionButton buttoniconSendReport = itemBuilder.setContentView(iconSendReport).build();
        SubActionButton buttoniconNewProduct = itemBuilder.setContentView(iconNewProduct).build();

        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttoniconNewReport)
                .addSubActionView(buttoniconSendReport)
                .addSubActionView(buttoniconNewProduct)
                .attachTo(actionButton)
                .build();

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
