package com.example.ficheros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EscribirInterna extends AppCompatActivity implements View.OnClickListener {

    private EditText operando1, operando2;
    private TextView resultado, propiedades;
    private Button botonSumar;
    public final static String NOMBREFICHERO = "resultado.txt";
    Memoria miMemoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir_interna);
        operando1 = (EditText) findViewById(R.id.edtOperando1);
        operando2 = (EditText) findViewById(R.id.edtOperando2);
        resultado = (TextView) findViewById(R.id.txvMostrarResultado);
        propiedades = (TextView) findViewById(R.id.txvMostrarPropiedades);
        botonSumar = (Button) findViewById(R.id.btnSumar);
        botonSumar.setOnClickListener(this);
        miMemoria = new Memoria(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        int r;
        String texto;

        if (view == botonSumar)
        {
            try {
                r = Integer.parseInt(operando1.getText().toString()) + Integer.parseInt(operando2.getText().toString());
                texto = String.valueOf(r);
            }
            catch (NumberFormatException e)
            {
                Log.e("Error", e.getMessage());
                texto = "0";
                Toast.makeText(getApplicationContext(),"Error: " + e.getMessage(), Toast.LENGTH_SHORT);
            }
            resultado.setText(texto);
            if (miMemoria.escribirInterna(NOMBREFICHERO, texto, false, "UTF-8"))
            {
                propiedades.setText(miMemoria.mostrarPropiedadesInterna(NOMBREFICHERO));
            }
            else
            {
                propiedades.setText("Error al escribir en el fichero " + NOMBREFICHERO);
            }
        }
    }
}
