package com.example.applogin;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Users.db";
    private static final int DATABASE_VERSION = 1;

    // Sentencia SQL para crear la tabla
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user TEXT NOT NULL," +
                    "password TEXT NOT NULL,"+
                    "edad INTEGER," +
                    "direccion TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
    public void insertarDatos(String user,String password, int edad, String direccion) {
        // Obtener una instancia de la base de datos para escribir
        SQLiteDatabase db = this.getWritableDatabase();

        // Crear un objeto ContentValues para insertar datos
        ContentValues values = new ContentValues();
        values.put("user", user);
        values.put("password",password);
        values.put("edad", edad);
        values.put("direccion", direccion);

        // Insertar datos en la tabla
        long newRowId = db.insert("usuarios", null, values);

        // Cierra la conexión a la base de datos
        //db.close();
    }
    public boolean verificarUsuario(String user, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id"};
        String selection = "user=? AND password=?";
        String[] selectionArgs = {user, password};

        Cursor cursor = db.query("usuarios", columns, selection, selectionArgs, null, null, null);

        int count = cursor.getCount();
        cursor.close();
        //db.close();

        return count > 0;
    }
    @SuppressLint("Range")
    public Usuario getUsuario(String user, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id", "user", "password", "edad", "direccion"};
        String selection = "user=? AND password=?";
        String[] selectionArgs = {user, password};
        Cursor cursor = db.query("usuarios", columns, selection, selectionArgs, null, null, null);

        Usuario usuario = null;
        if (cursor.moveToFirst()) {
            usuario = new Usuario(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("user")),
                    cursor.getString(cursor.getColumnIndex("password")),
                    cursor.getInt(cursor.getColumnIndex("edad")),
                    cursor.getString(cursor.getColumnIndex("direccion")));
        }

        cursor.close();
        // db.close();
        return usuario;
    }
}
