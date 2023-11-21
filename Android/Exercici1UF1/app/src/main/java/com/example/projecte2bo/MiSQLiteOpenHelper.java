package com.example.projecte2bo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MiSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DATOS = "mi_base_de_datos.db";
    private static final int VERSION_BASE_DATOS = 1;

    public MiSQLiteOpenHelper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_BASE_DATOS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear la tabla de usuarios
        String crearTablaUsuarios = "CREATE TABLE usuaris (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nom TEXT," +
                " cognom TEXT," +
                " dni TEXT," +
                " codiPostal INTEGER," +
                " direccio TEXT," +
                " data_Naixement DATE," +
                " genere INTEGER," +
                " estudis INTEGER," +
                " pwd TEXT)";
        db.execSQL(crearTablaUsuarios);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Manejar la actualización de la base de datos si es necesario
    }
    // Método para eliminar la tabla
    public void eliminarTabla() {
        // Obtén una referencia a la base de datos en modo escritura
        SQLiteDatabase db = this.getWritableDatabase();

        // Ejecuta la instrucción SQL para eliminar la tabla
        db.execSQL("DROP TABLE IF EXISTS usuaris");

        // Cierra la conexión con la base de datos
        db.close();
    }
}
