package com.example.applogin;
// se importa desde main el usuario
import static com.example.applogin.MainActivity.usuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
// Activity logueado a la que se llega tras un acceso correcto en la pantalla de login
// en ella se verán los datos del usuario cuando se registró

public class Logueado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logueado);
        // se crea un usuario, que es el importado desde el main al hacer el acceso
        Usuario usu = usuario;
        // se localiza cada uno de los textview que va a ser relleno con los datos del usuario
        TextView user= findViewById(R.id.textUserData);
        TextView edad=findViewById(R.id.textEdadData);
        TextView direccion=findViewById(R.id.textDireccionData);
        // se rellenan los textview con los datos
        user.setText(usu.getUser());
        edad.setText(String.valueOf(usu.getEdad()));
        direccion.setText(usu.getDireccion());
    }
}