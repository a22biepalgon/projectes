package com.example.projecte2bo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Calendar;
import java.util.Date;

public class plantillausuari extends AppCompatActivity {
    String usuari;
    Integer id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantillausuari);
        id =  getIntent().getExtras().getInt("id");
        usuari = getIntent().getExtras().getString("usuari");


        String[] items = {"ESO","BATX", "CFGS", "CFGM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        TextView dateTextView = findViewById(R.id.editTextDate);
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(dateTextView);
            }});


        // Crear una instancia de MiSQLiteOpenHelper
        MiSQLiteOpenHelper dbHelper = new MiSQLiteOpenHelper(this);


        // Obtener una base de datos en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String seleccion = "id = ?";
        String[] seleccionArgs = {id.toString()};
        // Definir la proyección (columnas que deseas recuperar)
        String[] proyeccion = {"*"};

        // Realizar la consulta SELECT
        Cursor cursor = db.query("usuaris", proyeccion, seleccion, seleccionArgs, null, null, null);

        // Procesar los resultados de la consulta
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Obtener los datos de cada fila
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nom = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
                String cognom = cursor.getString(cursor.getColumnIndexOrThrow("cognom"));
                String dni = cursor.getString(cursor.getColumnIndexOrThrow("dni"));
                Integer codiPostal = cursor.getInt(cursor.getColumnIndexOrThrow("codiPostal"));
                String direccio = cursor.getString(cursor.getColumnIndexOrThrow("direccio"));
                String dataNaixement = cursor.getString(cursor.getColumnIndexOrThrow("data_Naixement"));
                Integer genere = cursor.getInt(cursor.getColumnIndexOrThrow("genere"));
                Integer estudis = cursor.getInt(cursor.getColumnIndexOrThrow("estudis"));
                // Hacer algo con los datos (por ejemplo, mostrarlos en un Toast)
                EditText edNom = findViewById(R.id.editTextTextPersonName2);
                edNom.setText(nom);
                EditText edCognom = findViewById(R.id.editTextTextPersonName3);
                edCognom.setText(cognom);
                EditText edDni = findViewById(R.id.editTextTextPersonName4);
                edDni.setText(dni);

                EditText edPostal = findViewById(R.id.editTextTextPostalAddress);
                edPostal.setText(codiPostal.toString());
                EditText edDireccio = findViewById(R.id.editTextTextMultiLine);
                edDireccio.setText(direccio);
                EditText edData = findViewById(R.id.editTextDate);
                edData.setText(dataNaixement);
                RadioGroup rgGenere = findViewById(R.id.radioGroupGenere);
                if(genere == 0){
                    rgGenere.check(R.id.radioButton4);
                }else{
                    rgGenere.check(R.id.radioButton3);
                }
                switch(estudis){
                    case 1:
                        spinner.setSelection(adapter.getPosition("ESO"));
                        break;
                    case 2:
                        spinner.setSelection(adapter.getPosition("BATX"));
                        break;
                    case 3:
                        spinner.setSelection(adapter.getPosition("CFGM"));
                        break;
                    case 4:
                        spinner.setSelection(adapter.getPosition("CFGS"));
                        break;

                }

                //Toast.makeText(this, "ID: " + id + ", Nombre: " + nom + ", Cognom: " + cognom, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());

            // Cerrar el cursor cuando hayas terminado de usarlo
            cursor.close();
        } else {
            // No se encontraron registros
            Toast.makeText(this, "No se encontraron registros en la tabla usuarios", Toast.LENGTH_SHORT).show();
        }

        // Cerrar la conexión con la base de datos
        db.close();
/*
        // Obtener una base de datos en modo escritura
        SQLiteDatabase dbw = dbHelper.getWritableDatabase();

        // Insertar datos en la tabla de usuarios
        ContentValues valores = new ContentValues();
        valores.put("nom", "Anna");
        valores.put("cognom", "Palomar");
        valores.put("dni", "45153512X");
        valores.put("codiPostal", 8860);
        valores.put("direccio", "Avinguda 3");
        valores.put("data_Naixement", "12/03/2002");
        valores.put("genere", 2);
        valores.put("pwd", "1234");
        valores.put("estudis", 2);



        long resultado = dbw.insert("usuaris", null, valores);

        // Verificar si la inserción fue exitosa
        if (resultado != -1) {
            Toast.makeText(this, "Datos insertados correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al insertar datos", Toast.LENGTH_SHORT).show();
        }

        // Cerrar la conexión con la base de datos
        dbw.close();*/
    }
    private void openDatePickerDialog(final TextView dateTextView) {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Handle the selected date
                String selectedDate =dayOfMonth + "/" + (month + 1) + "/" + year;
                dateTextView.setText( selectedDate);
            }
        }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    public boolean tornar(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("usuari", usuari);
        intent.putExtra("id", id);
        startActivity(intent);
        return true;
    }


}