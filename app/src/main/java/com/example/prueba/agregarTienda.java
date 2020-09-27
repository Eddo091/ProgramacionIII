package com.example.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class agregarTienda extends AppCompatActivity {
    BD miDB;
    String accion = "nuevo";
    String idTienda = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.menu );
        Button btnGuardarTienda = (Button) findViewById( R.id.btnInsertar );
        btnGuardarTienda.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tempVal = (TextView) findViewById( R.id.txtproducto );
                String producto = tempVal.getText().toString();

                tempVal = (TextView) findViewById( R.id.txtPrecio );
                String precio = tempVal.getText().toString();

                tempVal = (TextView) findViewById( R.id.txtcodigo );
                String codigo = tempVal.getText().toString();

                tempVal = (TextView) findViewById( R.id.txtFecha );
                String fecha = tempVal.getText().toString();

                String[] data = {idTienda, codigo, producto, fecha, precio};
                miDB = new BD( getApplicationContext(), "", null, 1 );
                miDB.mantenimientoTienda( accion, data );

                Toast.makeText( getApplicationContext(), "Registro de amigo insertado con exito", Toast.LENGTH_LONG ).show();
                Intent mostrarAmigos = new Intent( agregarTienda.this, MainActivity.class );
                startActivity( mostrarAmigos );
            }
        } );
        mostrarDatosAmigo();
    }

    void mostrarDatosAmigo() {
        try {
            Bundle recibirParametros = getIntent().getExtras();
            accion = recibirParametros.getString( "accion" );
            if (accion.equals( "modificar" )) {
                String[] dataAmigo = recibirParametros.getStringArray( "dataAmigo" );

                idTienda = dataAmigo[0];

                TextView tempVal = (TextView) findViewById( R.id.txtcodigo );
                tempVal.setText( dataAmigo[1] );

                tempVal = (TextView) findViewById( R.id.txtproducto );
                tempVal.setText( dataAmigo[2] );

                tempVal = (TextView) findViewById( R.id.txtFecha );
                tempVal.setText( dataAmigo[3] );

                tempVal = (TextView) findViewById( R.id.txtPrecio );
                tempVal.setText( dataAmigo[4] );
            }
        } catch (Exception ex) {
            ///
        }
    }
}
