package com.example.prueba;
import androidx.appcompat.app.AppCompatActivity;

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

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TabHost tbhConversor;
    Valores misvalores= new Valores();
    EditText num1;
    TextView lblresp;
    EditText num_1;
    TextView lblR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.txtnum1 );
        lblresp = findViewById(R.id.lblRespu );
        num_1 = findViewById(R.id.txtnum1_2 );
        lblR = findViewById(R.id.lblResp2 );
        tbhConversor = findViewById(R.id.tbhConversor);
        tbhConversor.setup();

        tbhConversor.addTab(tbhConversor.newTabSpec("Moneda").setContent(R.id.Moneda).setIndicator("Moneda", null));
        tbhConversor.addTab(tbhConversor.newTabSpec("Superficie").setContent(R.id.Superficie).setIndicator("Superficie", null));

        tbhConversor.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                num1.getText().clear();
                lblresp.setText("");
                num_1.getText().clear();
                lblR.setText( "" );
                
            }
        });
    }


    public void Convertir(View view){
        try {
            TextView tmpVal = (TextView) findViewById(R.id.txtnum1 );
            double cantidad = Double.parseDouble(tmpVal.getText().toString());
            int de = 0, a = 0;
            double resp = 0;
            final Spinner spnTipo = (Spinner)findViewById(R.id.spnTipo_);
            final Spinner spnDe = (Spinner) findViewById( R.id.spnDe );
            final Spinner spnA = (Spinner) findViewById( R.id.spnA );

            spnTipo.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                    spnDe.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,misvalores.obtenerConversor(position) ));
                    spnA.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,misvalores.obtenerConversor(position) ));
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }


            } );

            Button btnConvertir = (Button)findViewById(R.id.btnConvertir);
            btnConvertir.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView tempVal = (TextView)findViewById(R.id.txtnum1);
                    double cantidad = Double.parseDouble(tempVal.getText().toString());
                    int d = spnDe.getSelectedItemPosition();
                    int a = spnA.getSelectedItemPosition();
                    int tipo = spnTipo.getSelectedItemPosition();
                    Double respuesta = misvalores.convertir(tipo,d,a,cantidad);
                    tempVal = (TextView)findViewById(R.id.lblRespu);
                    tempVal.setText("Respuesta: "+ respuesta);
                }
            } );


        }catch (Exception err){
            Toast.makeText(getApplicationContext(),"Error: Valor incorrecto",Toast.LENGTH_LONG).show();

        }
    }


}