package com.example.prueba;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.database.Cursor;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    BD midb;
    Cursor mitienda;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);



}

    void obtenerDatosTienda(){
        midb = new BD(getApplicationContext(), "", null, 1);
        mitienda = midb.mantenimientoTienda("consultar", null);
        if( mitienda.moveToFirst() ){ //hay registro en la BD que mostrar
            mostrarDatosTienda();
        } else{ //No tengo registro que mostrar.
            Toast.makeText(getApplicationContext(), "No hay registros de cliente que mostrar",Toast.LENGTH_LONG).show();
            //Intent agregarTienda = new Intent(MainActivity.this, agregarTienda.class);
            //startActivity(agregarTienda);
        }
    }
    void mostrarDatosTienda(){

    }
}
