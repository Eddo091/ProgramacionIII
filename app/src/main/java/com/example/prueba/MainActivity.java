package com.example.prueba;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.Toast;
import java.util.ArrayList;
import android.database.Cursor;
import org.w3c.dom.Text;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    BD midb;
    Cursor mitienda;
    ArrayList<String> stringArrayList = new ArrayList<String>();
    ArrayList<String> copyStringArrayList = new ArrayList<String>();
    ArrayAdapter<String> stringArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscar);

        FloatingActionButton btnAgregarTienda = (FloatingActionButton)findViewById(R.id.btnagregarTienda);
        btnAgregarTienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarTienda ("nuevo", new String[]{});
            }
        });
        obtenerDatosTienda();
        buscarTienda();

}

    void agregarTienda(String accion, String[] dataAmigo){
        Bundle enviarParametros = new Bundle();
        enviarParametros.putString("accion",accion);
        enviarParametros.putStringArray("dataAmigo",dataAmigo);
        Intent agregarTienda= new Intent(MainActivity.this, agregarTienda.class);
        agregarTienda.putExtras(enviarParametros);
        startActivity(agregarTienda);
    }
    void buscarTienda(){
        final TextView tempVal = (TextView)findViewById(R.id.txtbuscar);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                stringArrayList.clear();
                if( tempVal.getText().toString().trim().length()<1 ){//no hay texto para buscar
                    stringArrayList.addAll(copyStringArrayList);
                } else{//hacemos la busqueda
                    for (String amigo : copyStringArrayList){
                        if(amigo.toLowerCase().contains(tempVal.getText().toString().trim().toLowerCase())){
                            stringArrayList.add(amigo);
                        }
                    }
                }
                stringArrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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
