package com.example.applogin;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
// se extiende de SQLiteOpenHelper para poder emplear la base de datos de SQLite,
// determinada para las aplicaciones android
public class DatabaseHelper extends SQLiteOpenHelper {
    //se ponen constantes el nombre de la base de datos y la versión
    private static final String DATABASE_NAME = "Users.db";
    private static final int DATABASE_VERSION = 1;

    // Sentencia SQL para crear la tabla
    // el id como autoincrement para no tener que rellenar manualmente
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS usuarios (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "user TEXT NOT NULL," +
                    "password TEXT NOT NULL,"+
                    "edad INTEGER," +
                    "direccion TEXT);";
    //al constructor se le pasa el contexto con el nombre d ela base de datos y la versión

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
// los métodos que la interfaz te obliga a implementar
    // cuando se crea, se ejecuta la creación de la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }
// cuando se actualiza se elimina si existe la tabla y se crea la base de datos de nuevo
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(db);
    }
    // creamos un método para insertar en la base de datos los datos de registro para la app
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
        // comentamos esta línea para poder ver los datos que se insertan con la ventana App Inspection
        //db.close();
    }
    // creamos método de verificación de usuarios, comprueba si existe en la bd algun usuario y contraseña que
    // sea igual que el que inserta el usuario para accceder a la app
    public boolean verificarUsuario(String user, String password) {
        // obtiene una instancia de la base de datos para leer
        SQLiteDatabase db = this.getReadableDatabase();
        // lo que se devuelve
        String[] columns = {"id"};
        // se especifican los campos por los que se va a buscar
        String selection = "user=? AND password=?";
        //se pasan los argumentos que completan esa sentencia
        String[] selectionArgs = {user, password};
        // se emplea un cursor para recorrer, pasando los datos por los que se buscará
        Cursor cursor = db.query("usuarios", columns, selection, selectionArgs, null, null, null);
        // devuelve el número de coincidencias
        int count = cursor.getCount();
        // se cierra el cursor para liberar recursos
        cursor.close();
        // se cierra la conexión a la base de datos
        // pero la comentamos para poder ver los datos en App Inspection
        //db.close();
        // devuelve cuantas coincidencias hay
        return count > 0;
    }
    // creamos método para que se devuelva todos los datos del usuario que ha accedido
    @SuppressLint("Range")
    public Usuario getUsuario(String user, String password) {
        // Obtener una instancia de la base de datos para leer
        SQLiteDatabase db = this.getReadableDatabase();
        // se indica los campos que queremos se nos devuelvan
        String[] columns = {"id", "user", "password", "edad", "direccion"};
        // se indica por qué campos se filtran
        String selection = "user=? AND password=?";
        // se pasan los argumentos por los que se filtrará
        String[] selectionArgs = {user, password};
        // s eemplea un cursor para recorrer los datos
        Cursor cursor = db.query("usuarios", columns, selection, selectionArgs, null, null, null);
        // se crea un usuario
        Usuario usuario = null;
        // se instancia y se rellena los datos de la clase usuario
        if (cursor.moveToFirst()) {
            usuario = new Usuario(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("user")),
                    cursor.getString(cursor.getColumnIndex("password")),
                    cursor.getInt(cursor.getColumnIndex("edad")),
                    cursor.getString(cursor.getColumnIndex("direccion")));
        }
        // se cierra el cursor para liberar recursos
        cursor.close();
        // se cierra la conexión a la base de datos
        // pero la comentamos para poder ver los datos en App Inspection
        // db.close();
        // el método devuelve un objeto de la clase usuario con los datos recolectados de la base de datos
        return usuario;
    }
}
