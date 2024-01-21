package com.example.applogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Activity registro a la que se llega tras pulsar registrar en el main
// el usuario insertará los datos necesario para la cuenta y el acceso a la app
public class Registro extends AppCompatActivity {
    // se crea un objeto de DatabaseHelper
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        // se instancia dbHelper
        dbHelper = new DatabaseHelper(this);
        // se localizan todos los campos y el botón con el que el usuario interactúa
        Button buttonRegistrar= findViewById(R.id.buttonRegistrar);
        EditText userEdit= findViewById(R.id.user_edit);
        EditText passworEdit=findViewById(R.id.password_edit);
        EditText edadEdit= findViewById(R.id.edad_edit);
        EditText direccionEdit=findViewById(R.id.direccion_edit);
        // listener de el botón para confirmar la entrada de datos
        buttonRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // se comprueba que no falten campos por rellenar
                if (!userEdit.getText().toString().isEmpty() && !passworEdit.getText().toString().isEmpty() && !direccionEdit.getText().toString().isEmpty() && !edadEdit.getText().toString().isEmpty()){
                    // se comprueba que no exista ya ese nombre de usuario con esa contraseña
                    if (!dbHelper.verificarUsuario(userEdit.getText().toString(),passworEdit.getText().toString())){
                        // se insertan los datos a la base de datos y se redirecciona al main
                        dbHelper.insertarDatos(userEdit.getText().toString(),passworEdit.getText().toString(),Integer.parseInt(edadEdit.getText().toString()),direccionEdit.getText().toString());
                        Toast.makeText(getBaseContext(),"Usuario registrado correctamente",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registro.this, MainActivity.class);
                        startActivity(intent);
                    }
                    // se indica que cambie el nombre de usuario
                    else {
                        Toast.makeText(getBaseContext(),"Debe introducir otro nombre de usuario",Toast.LENGTH_SHORT).show();
                    }
                }
                // se indica que faltan datos
                else {
                    Toast.makeText(getBaseContext(),"Faltan datos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}