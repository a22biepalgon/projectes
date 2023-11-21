package com.example.projecte2bo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void launchSecondActivity(View view) {
        //Agafem el valor del usuari
        EditText etext = (EditText) findViewById(R.id.editTextTextPersonName);
        String user = etext.getText().toString();
        EditText edContra = (EditText) findViewById(R.id.editTextTextPassword);
        String password = edContra.getText().toString();


        MiSQLiteOpenHelper dbHelper = new MiSQLiteOpenHelper(this);
        // Obtener una base de datos en modo lectura
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String seleccion = "nom = ? ";
        String[] seleccionArgs = {user};
        // Definir la proyección (columnas que deseas recuperar)
        String[] proyeccion = {"id" , "pwd"};

        // Realizar la consulta SELECT
        Cursor cursor = db.query("usuaris", proyeccion, seleccion, seleccionArgs, null, null, null);

        // Procesar los resultados de la consulta
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Obtener los datos de cada fila
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String pwd = cursor.getString(cursor.getColumnIndexOrThrow("pwd"));

                if(pwd.equals(password)){
                    Intent intent = new Intent(this, MainActivity2.class);
                    intent.putExtra("usuari", user);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "Usuari o Contrasenya Incorrecte", Toast.LENGTH_SHORT).show();
                }

            } while (cursor.moveToNext());

            // Cerrar el cursor cuando hayas terminado de usarlo
            cursor.close();
        } else {
            // No se encontraron registros
            Toast.makeText(this, "Aquest usuari no eisteix", Toast.LENGTH_SHORT).show();
        }

        // Cerrar la conexión con la base de datos
        db.close();













    }
}