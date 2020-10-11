package com.example.prueba;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.PublicKey;
import java.util.ArrayList;
import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    // BD midb;
    Cursor mitienda;
    ArrayList<String> stringArrayList = new ArrayList<String>();
    ArrayList<String> copyStringArrayList = new ArrayList<String>();
    ArrayAdapter<String> stringArrayAdapter;

    JSONArray datosJSON;
    JSONObject jsonObject;
    Integer posicion;


    private class ObtenerDatosTiendaaa extends AsyncTask<Void, Void, String> {
        HttpURLConnection urlConnection;
        private Void[] voids;

        @Override
        protected String doInBackground(Void... voids) {
            this.voids = voids;
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL( "http://192.168.0.15:5984/mitienda/_design/Tienda/_view/mi-tienda" );
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod( "GET" );
                InputStream in = new BufferedInputStream( urlConnection.getInputStream() );
                BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );
                String linea;
                while ((linea = reader.readLine()) != null) {
                    result.append( linea );
                }
            } catch (Exception err) {
            }
            return result.toString();
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute( s );
            try {
                jsonObject = new JSONObject( s );
                datosJSON = jsonObject.getJSONArray( "rows" );
                MostrarDatosTiendaa();
            } catch (Exception ex) {
                Toast.makeText( MainActivity.this, "Error al parsear los datos" + ex.getMessage(), Toast.LENGTH_LONG ).show();
            }
        }
    }

    private void MostrarDatosTiendaa() {
        ListView ltsTienda = findViewById( R.id.ltsTienda );
        try {
            final ArrayList<String> arrayList = new ArrayList<>();
            final ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>( MainActivity.this, android.R.layout.simple_list_item_1, arrayList );
            ltsTienda.setAdapter( stringArrayAdapter );
            for (int i = 0; i < datosJSON.length(); i++) {
                stringArrayAdapter.add( datosJSON.getJSONObject( i ).getJSONObject( "value" ).getString( "Producto" ) );
            }
            stringArrayAdapter.notifyDataSetChanged();
            registerForContextMenu( ltsTienda );
        } catch (Exception ex) {
            Toast.makeText( MainActivity.this, "Error al mostrar los datos" + ex.getMessage(), Toast.LENGTH_LONG ).show();
        }
    }

    public void agregarNuevoTienda(String accion, JSONObject jsonObject) {
        try {
            Bundle enviarParametros = new Bundle();
            enviarParametros.putString( "accion", accion );
            enviarParametros.putString( "dataTienda", jsonObject.toString() );

            Intent agregarTienda = new Intent( MainActivity.this, agregar_tienda.class );
            agregarTienda.putExtras( enviarParametros );
            startActivity( agregarTienda );
        } catch (Exception e) {
            Toast.makeText( getApplicationContext(), "Error al llamar agregar en tienda: " + e.toString(), Toast.LENGTH_LONG ).show();
        }
    }

    AlertDialog eliminarTienda() {
        AlertDialog.Builder confirmacion = new AlertDialog.Builder( MainActivity.this );
        try {
            confirmacion.setTitle( datosJSON.getJSONObject( posicion ).getJSONObject( "value" ).getString( "nombre" ) );
            confirmacion.setMessage( "Esta seguro de eliminar el registro?" );
            confirmacion.setPositiveButton( "Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    eliminarDatosTienda objEliminarTienda = new eliminarDatosTienda();
                    objEliminarTienda.execute();

                    Toast.makeText( getApplicationContext(), "Amigo eliminado con exito.", Toast.LENGTH_SHORT ).show();
                    dialogInterface.dismiss();
                }
            } );
            confirmacion.setNegativeButton( "No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText( getApplicationContext(), "Eliminacion cancelada por el usuario.", Toast.LENGTH_SHORT ).show();
                    dialogInterface.dismiss();
                }
            } );
        } catch (Exception ex) {
            Toast.makeText( getApplicationContext(), "Error al mostrar la confoirmacion: " + ex.getMessage(), Toast.LENGTH_LONG ).show();
        }
        return confirmacion.create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.buscar );

        ObtenerDatosTiendaaa objObtenerTiendaa = new ObtenerDatosTiendaaa();
        objObtenerTiendaa.execute();

        FloatingActionButton btnAgregarNuevoTienda = findViewById( R.id.btnagregarTienda );
        btnAgregarNuevoTienda.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarNuevoTienda( "nuevo", jsonObject );
            }
        } );

    }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu( menu, v, menuInfo );

            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate( R.menu.menuprincipal, menu );
            try {
                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
                posicion = adapterContextMenuInfo.position;
                menu.setHeaderTitle( datosJSON.getJSONObject( posicion ).getString( "Producto" ) );
            } catch (Exception ex) {

            }

        }
    private class eliminarDatosTienda extends AsyncTask<String, String, String> {
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... parametros) {
            StringBuilder stringBuilder = new StringBuilder();
            String jsonResponse = null;
            try {
                URL url = new URL( "http://192.168.1.7:5984/db_agenda/" +
                        datosJSON.getJSONObject( posicion ).getJSONObject( "value" ).getString( "_id" ) + "?rev=" +
                        datosJSON.getJSONObject( posicion ).getJSONObject( "value" ).getString( "_rev" ) );

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod( "DELETE" );

                InputStream in = new BufferedInputStream( urlConnection.getInputStream() );
                BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );

                String inputLine;
                StringBuffer stringBuffer = new StringBuffer();
                while ((inputLine = reader.readLine()) != null) {
                    stringBuffer.append( inputLine + "\n" );
                }
                if (stringBuffer.length() == 0) {
                    return null;
                }
                jsonResponse = stringBuffer.toString();
                return jsonResponse;
            } catch (Exception ex) {
                //
            }
            return null;
        }

        }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnxAgregarTienda:
                agregarNuevoTienda( "nuevo", jsonObject );
                return true;

            case R.id.mnxModificarTienda:
                try {
                    agregarNuevoTienda( "modificar", datosJSON.getJSONObject( posicion ) );
                } catch (Exception ex) {
                }
                return true;

            case R.id.mnxEliminarTienda:

                AlertDialog eliminarTienda = eliminarTienda();
                eliminarTienda.show();
                return true;

            default:
                return super.onContextItemSelected( item );
        }
    }

}

