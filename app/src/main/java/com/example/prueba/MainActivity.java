package com.example.prueba;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
            switch (tbhConversor.getCurrentTabTag()){

                case "Monedas":
                    misvalores.val   = (Spinner) findViewById(R.id.spnDe);
                    de = misvalores.val .getSelectedItemPosition();
                    misvalores.val  = (Spinner) findViewById(R.id.spnA);
                    a = misvalores.val .getSelectedItemPosition();
                    resp = misvalores.datos [0][a] / misvalores.datos [0][de];
                    tmpVal = (TextView) findViewById(R.id.lblRespu );
                    tmpVal.setText(String.format("Por el valor de: "+ cantidad  + " Respuesta " + resp  ));
                    break;

                case "Superficie":
                    misvalores.val   = (Spinner) findViewById(R.id.spnDe2);
                    de = misvalores.val .getSelectedItemPosition();
                    misvalores.val  = (Spinner) findViewById(R.id.spnA2);
                    a = misvalores.val .getSelectedItemPosition();
                    resp = misvalores.datos [0][a] / misvalores.datos [0][de];

                    tmpVal=(TextView)findViewById( R.id.lblResp2 );
                    tmpVal.setText(String.format("Por el valor de: "+ cantidad  + " Respuesta " + resp  ));
                    break;

            }


        }catch (Exception err){
            TextView temp = (TextView) findViewById(R.id.lblRespu );
            TextView temp2 = (TextView) findViewById(R.id.lblResp2 );
            Toast.makeText(getApplicationContext(),"Error: Valor incorrecto",Toast.LENGTH_LONG).show();

        }
    }


}