package com.example.applogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);


        EditText userEdit= findViewById(R.id.user_edit);
        EditText passworEdit=findViewById(R.id.password_edit);
        Button button= findViewById(R.id.button);
        Button buttonRegistrar= findViewById(R.id.buttonRegistrar);



        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Toma el texto de lo escrito en EditText
                String user=userEdit.getText().toString();
                String password=passworEdit.getText().toString();
                Log.d("MiTagPrincipal","Pulsado");
                Toast.makeText(getBaseContext(),"El usuario ha hecho click",Toast.LENGTH_SHORT).show();
                // Comprobar si los campos están vacios o no
                if (!user.isEmpty() && !password.isEmpty()) {
                    //comprobar si es igual o no
                    if (dbHelper.verificarUsuario(user,password)){
                        Intent intent = new Intent(MainActivity.this, Logueado.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getBaseContext(),"Usuario o passwod incorrectos",Toast.LENGTH_SHORT).show();


                    }
                }
                else {
                    Toast.makeText(getBaseContext(),"Usuario o password sin datos",Toast.LENGTH_SHORT).show();

                }
            }
        });
        buttonRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Registro.class);
                startActivity(intent);
            }
        });

    }

}