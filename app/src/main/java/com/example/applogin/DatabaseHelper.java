package com.example.applogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Usuarios.db";
    private static final String TABLE_NAME = "usuarios";
    private static final String COL_ID = "ID";
    private static final String COL_USUARIO = "USUARIO";
    private static final String COL_CONTRASENA = "CONTRASENA";
    private static final String COL_EDAD = "EDAD";
    private static final String COL_DIRECCION = "DIRECCION";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_USUARIO + " TEXT, " +
                COL_CONTRASENA + " TEXT, " +
                COL_EDAD + " INTEGER, " +
                COL_DIRECCION + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertarUsuario(String usuario, String contrasena, int edad, String direccion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USUARIO, usuario);
        contentValues.put(COL_CONTRASENA, contrasena);
        contentValues.put(COL_EDAD, edad);
        contentValues.put(COL_DIRECCION, direccion);
        long resultado = db.insert(TABLE_NAME, null, contentValues);
        return resultado != -1;
    }

    public boolean verificarCredenciales(String usuario, String contrasena) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_ID};
        String selection = COL_USUARIO + "=? AND " + COL_CONTRASENA + "=?";
        String[] selectionArgs = {usuario, contrasena};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        return count > 0;
    }
}
