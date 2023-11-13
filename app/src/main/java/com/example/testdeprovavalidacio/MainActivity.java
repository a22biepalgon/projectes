package com.example.testdeprovavalidacio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    String opcionSeleccionada = ""; //string a on guardem la opció
    Button btn; //botò per a enviar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Busquem el botó per ID
        btn = findViewById(R.id.button);

        //OnClickListener del botó
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Fem un nou Intent on li diem quina clase activar
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                //Fem un put extra per pasar el valor
                intent.putExtra("valor", opcionSeleccionada);
                //Obrim la nova activity
                startActivity(intent);
            }
        });

        //Agafem el spinner per id
        Spinner spinner = findViewById(R.id.spinner);

        //Agafem l'array de strings del string.xml
        String[] opciones = getResources().getStringArray(R.array.opcionesSpinner);

        //nom de la app
        String nom = getResources().getString(R.string.app_name);

        //Fem un ArrayAdapter per posar els valors de opciones al simple_spinner_Item ??????
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //el spinner que hem agafat per id li posem l'adapter
        spinner.setAdapter(adapter);

        // Agrega un listener al Spinner para obtener la opción seleccionada
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                opcionSeleccionada = opciones[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                //aqui en principio nada
            }
        });


    }
}

