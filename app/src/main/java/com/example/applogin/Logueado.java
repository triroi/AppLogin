package com.example.applogin;

import static com.example.applogin.MainActivity.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Logueado extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueado);
        Usuario usu = usuario;
        TextView user= findViewById(R.id.textUserData);
        TextView edad=findViewById(R.id.textEdadData);
        TextView direccion=findViewById(R.id.textDireccionData);

        user.setText(usu.getUser());
        edad.setText(String.valueOf(usu.getEdad()));
        direccion.setText(usu.getDireccion());
    }
}