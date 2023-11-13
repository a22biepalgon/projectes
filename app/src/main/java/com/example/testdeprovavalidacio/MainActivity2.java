package com.example.testdeprovavalidacio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Definim el valor del textview
        TextView tv = findViewById(R.id.textView);

        //Agafem la opció seleccionada que s'ha passat per intent
        String opcion = getIntent().getStringExtra("valor");

        //fem el set text del textview
        tv.setText(opcion);
    }
}