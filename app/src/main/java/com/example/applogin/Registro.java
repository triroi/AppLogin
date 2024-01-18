package com.example.applogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        dbHelper = new DatabaseHelper(this);
        Button buttonRegistrar= findViewById(R.id.buttonRegistrar);
        EditText userEdit= findViewById(R.id.user_edit);
        EditText passworEdit=findViewById(R.id.password_edit);
        EditText edadEdit= findViewById(R.id.edad_edit);
        EditText direccionEdit=findViewById(R.id.direccion_edit);

        buttonRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dbHelper.insertarDatos(userEdit.getText().toString(),passworEdit.getText().toString(),Integer.parseInt(edadEdit.getText().toString()),direccionEdit.getText().toString());
                Toast.makeText(getBaseContext(),"Usuario registrado correctamente",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Registro.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}