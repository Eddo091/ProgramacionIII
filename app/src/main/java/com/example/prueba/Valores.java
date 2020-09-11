package com.example.prueba;

import android.widget.Spinner;

public class Valores {
        public

        Spinner val;
        Spinner val2;

        String [][] etiquetas = {
                {"Zafiro","Rubí","Esmeralda"},
                {"Metro","CM", "Pulgadas", "Pies","Varas","Yardas","Km","Millas"}
        };

        double[][] datos = new double[][]{
                new double[]{1, 0.55, 0.34, 1.07}, //Tabhost1
                new double[]{1,0.11,0.111,0.092,0.00014,0.000013,0.0000092},//Tabhost2

        };

        String[] obtenerConversor(int posicion){
                return etiquetas[posicion];
        }



        public Double convertir(int tipo, int de, int a, double cantidad) {
                return datos[tipo][a] / datos[tipo][de] * cantidad;
        }
}

