package com.example.projecte2bo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void launchSecondActivity(View view) {
        Log.d("HOLA", "Button clicked!");
        Intent intent = new Intent(this, MainActivity2.class);
        EditText etext = (EditText) findViewById(R.id.editTextTextPersonName);
        intent.putExtra("usuari", etext.getText().toString());
        startActivity(intent);

    }
}