package com.example.prueba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class agregarTienda extends AppCompatActivity {
/*    BD miDB;
    String accion = "nuevo";
    String idTienda = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.menu );
        Button btnGuardarTienda = (Button) findViewById( R.id.btnGuardarTienda );
        btnGuardarTienda.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tempVal = (TextView) findViewById( R.id.txtNombreProducto );
                String producto = tempVal.getText().toString();

                tempVal = (TextView) findViewById( R.id.txtPrecioProducto );
                String precio = tempVal.getText().toString();

                tempVal = (TextView) findViewById( R.id.txtcodigotienda );
                String codigo = tempVal.getText().toString();


                String[] data = {idTienda, codigo, producto, precio};
                miDB = new BD( getApplicationContext(), "", null, 1 );
                miDB.mantenimientoTienda( accion, data );

                Toast.makeText( getApplicationContext(), "Registro de amigo insertado con exito", Toast.LENGTH_LONG ).show();
                mostrarListaTienda();
            }
        } );
        mostrarDatosAmigo();
    }

    void mostrarListaTienda(){
        Intent mostrarTienda = new Intent(agregarTienda.this, MainActivity.class);
        startActivity(mostrarTienda);
    }

    void mostrarDatosAmigo() {
        try {
            Bundle recibirParametros = getIntent().getExtras();
            accion = recibirParametros.getString( "accion" );
            if (accion.equals( "modificar" )) {
                String[] dataTienda = recibirParametros.getStringArray( "dataTienda" );

                idTienda = dataTienda[0];

                TextView tempVal = (TextView) findViewById( R.id.txtcodigotienda);
                tempVal.setText( dataTienda[1] );

                tempVal = (TextView) findViewById( R.id.txtNombreProducto);
                tempVal.setText( dataTienda[2] );

                tempVal = (TextView) findViewById( R.id.txtPrecioProducto );
                tempVal.setText( dataTienda[3] );
            }
        } catch (Exception ex) {
            ///
        }
    }*/
}
