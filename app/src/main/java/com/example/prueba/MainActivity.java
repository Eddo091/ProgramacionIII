package com.example.prueba;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import org.w3c.dom.Text;

import java.util.Objects;

public class MainActivity extends Activity {
    public
    TabHost TbhConversorUno_;
    Valores misvalores= new Valores();
    EditText num1_;
    TextView Resp_;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1_=findViewById( R.id.txtnum1_ );
        Resp_=findViewById( R.id.lblrespuesta_ );
        TbhConversorUno_=findViewById( R.id.TbhConversor );
        TbhConversorUno_.setup();
        TbhConversorUno_.addTab(TbhConversorUno_.newTabSpec("Monedas").setContent(R.id.TbhConversor).setIndicator("Monedas", null));
        TbhConversorUno_.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                num1_.getText().clear();
                Resp_.setText("");
            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)



    public void NuevaConversion(View view){
        LimpiarTexto();


    }

    private void LimpiarTexto() {
        num1_.setText( "" );
        Resp_.setText( "" );
    }

    ;

    private void validacion() {
        String num1__= num1_.getText().toString();
        if (num1__.equals( "" )){
            num1_.setError( "Ingrese cantidad" );
        }
        ;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void Convertir(View view) {
        try {

            TextView tmpVal= (TextView) findViewById( R.id.txtnum1_ );
            double cantidad = Double.parseDouble( tmpVal.getText().toString() );
            validacion();
            int de = 0, a = 0;
            double resp = 0;

            switch (Objects.requireNonNull( TbhConversorUno_.getCurrentTabTag() )){
                case "Monedas":
                    misvalores.val = (Spinner) findViewById(R.id.cboUno2);
                    de = misvalores.val.getSelectedItemPosition();
                    misvalores.val = (Spinner) findViewById(R.id.cboDos2);
                    a= misvalores.val.getSelectedItemPosition();
                    resp= misvalores.datos[0][a]/misvalores.datos[0][de];

                    break;

            }

            tmpVal = (TextView) findViewById(R.id.lblrespuesta_);
            tmpVal.setText(String.format("Respuesta: " + resp));

        } catch (Exception e) {

            TextView temp = (TextView) findViewById(R.id.lblrespuesta_);


            Toast.makeText(getApplicationContext(),"Error: Ingrese la cantidad",Toast.LENGTH_LONG).show();
        }

    }

    ;
}