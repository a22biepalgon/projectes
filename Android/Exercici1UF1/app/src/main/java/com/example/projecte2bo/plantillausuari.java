package com.example.projecte2bo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
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
    Persona persona = new Persona();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plantillausuari);
        id = getIntent().getExtras().getInt("id");
        usuari = getIntent().getExtras().getString("usuari");


        String[] items = {"ESO", "BATX", "CFGS", "CFGM"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        TextView dateTextView = findViewById(R.id.editTextDate);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected item from the adapter
                String selectedItem = (String) parentView.getItemAtPosition(position);

                // Use the selected item as needed
                switch (selectedItem) {
                    case "ESO":
                        persona.setEstudis(1);
                        break;
                    case "BATX":
                        persona.setEstudis(2);
                        break;
                    case "CFGM":
                        persona.setEstudis(3);
                        break;
                    case "CFGS":
                        persona.setEstudis(4);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePickerDialog(dateTextView);
            }
        });


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
                persona = new Persona();
                // Obtener los datos de cada fila
                persona.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                persona.setNom(cursor.getString(cursor.getColumnIndexOrThrow("nom")));
                persona.setCognom(cursor.getString(cursor.getColumnIndexOrThrow("cognom")));
                persona.setDni(cursor.getString(cursor.getColumnIndexOrThrow("dni")));
                persona.setCodiPostal(cursor.getInt(cursor.getColumnIndexOrThrow("codiPostal")));
                persona.setDireccio(cursor.getString(cursor.getColumnIndexOrThrow("direccio")));
                persona.setDataNaixement(cursor.getString(cursor.getColumnIndexOrThrow("data_Naixement")));
                persona.setGenere(cursor.getInt(cursor.getColumnIndexOrThrow("genere")));
                persona.setEstudis(cursor.getInt(cursor.getColumnIndexOrThrow("estudis")));
                persona.setPwd(cursor.getString(cursor.getColumnIndexOrThrow("pwd")));
                // Hacer algo con los datos (por ejemplo, mostrarlos en un Toast)
                EditText edNom = findViewById(R.id.editTextTextPersonName2);
                edNom.setText(persona.getNom());
                edNom.setInputType(InputType.TYPE_NULL);
                edNom.setKeyListener(null);
                EditText edCognom = findViewById(R.id.editTextTextPersonName3);
                edCognom.setText(persona.getCognom());
                EditText edDni = findViewById(R.id.editTextTextPersonName4);
                edDni.setText(persona.getDni());

                EditText edPostal = findViewById(R.id.editTextTextPostalAddress);
                edPostal.setText(persona.getCodiPostal().toString());
                EditText edDireccio = findViewById(R.id.editTextTextMultiLine);
                edDireccio.setText(persona.getDireccio());
                EditText edData = findViewById(R.id.editTextDate);
                edData.setText(persona.getDataNaixement());
                RadioGroup rgGenere = findViewById(R.id.radioGroupGenere);
                if (persona.getGenere() == 0) {
                    rgGenere.check(R.id.radioButton4);
                } else {
                    rgGenere.check(R.id.radioButton3);
                }
                switch (persona.getEstudis()) {
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
            Toast.makeText(this, R.string.no_user_found, Toast.LENGTH_SHORT).show();
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
                String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                dateTextView.setText(selectedDate);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public boolean tornar(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.putExtra("usuari", usuari);
        intent.putExtra("id", id);
        startActivity(intent);
        return true;
    }

    public void actualitzarPerfil(View view) {
        if (persona != null) {
            showConfirmationDialog();
        }
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirmació")
                .setMessage("Estas segur que vols guardar els canvis?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Action to be taken when "Yes" is clicked
                        EditText edNom = findViewById(R.id.editTextTextPersonName2);
                        EditText edCognom = findViewById(R.id.editTextTextPersonName3);
                        EditText edDni = findViewById(R.id.editTextTextPersonName4);
                        EditText edPostal = findViewById(R.id.editTextTextPostalAddress);
                        EditText edDireccio = findViewById(R.id.editTextTextMultiLine);
                        EditText edData = findViewById(R.id.editTextDate);
                        RadioGroup rgGenere = findViewById(R.id.radioGroupGenere);
                        Spinner sEstudis = findViewById(R.id.spinner);

                        persona.setNom(edNom.getText().toString());
                        persona.setCognom(edCognom.getText().toString());
                        persona.setDni(edDni.getText().toString());
                        persona.setCodiPostal(Integer.parseInt(edPostal.getText().toString()));
                        persona.setDireccio(edDireccio.getText().toString());
                        persona.setDataNaixement(edData.getText().toString());
                        RadioButton seleccionat = findViewById(rgGenere.getCheckedRadioButtonId());
                        if (seleccionat.getText().equals("Home")) {
                            persona.setGenere(1);
                        } else {
                            persona.setGenere(2);
                        }
                        System.out.println("FENT UPDATE");
                        ContentValues values = new ContentValues();
                        values.put("nom", persona.getNom());
                        values.put("cognom", persona.getCognom());
                        values.put("dni", persona.getDni());
                        values.put("codiPostal", persona.getCodiPostal());
                        values.put("direccio", persona.getDireccio());
                        values.put("data_Naixement", persona.getDataNaixement());
                        values.put("genere", persona.getGenere());
                        values.put("estudis", persona.getEstudis());
                        values.put("pwd", persona.getPwd());

                        MiSQLiteOpenHelper dbHelper = new MiSQLiteOpenHelper(plantillausuari.this);


                        // Obtener una base de datos en modo lectura
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        String selection = "id=?";
                        String id = "" + persona.getId();
                        System.out.println(id);
                        String[] selectionArgs = {id};

                        db.update("usuaris", values, selection, selectionArgs);

                        db.close();
                        Toast.makeText(plantillausuari.this, "Actualitzat Correctament!", Toast.LENGTH_SHORT).show();
                        // For example, you can perform some operation or dismiss the dialog
                        //dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Action to be taken when "No" is clicked
                        System.out.println("NO");
                        // For example, you can cancel the operation or dismiss the dialog
                        dialog.dismiss();
                    }
                })
                .show();

    }

}