package com.example.applogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Activity main, el del acceso principal, cuenta con la opción de login o de registro
public class MainActivity extends AppCompatActivity {
    // se crean objetos de las clases Usuario y DatabaseHelper
    private DatabaseHelper dbHelper;
    // se hace public static para que luego la activity logueado la pueda ver
    public static Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);

        // se localizan los campos y botones con los que el usuario tendrá interacción
        EditText userEdit= findViewById(R.id.user_edit);
        EditText passworEdit=findViewById(R.id.password_edit);
        Button button= findViewById(R.id.button);
        Button buttonRegistrar= findViewById(R.id.buttonRegistrar);


        // se crea un listener para el botón de login
        // cuando se pulsa comprueba si los campos están rellenos o no y se verifica si son compatibles
        // con los datos de la bbdd
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Toma el texto de lo escrito en EditText
                String user=userEdit.getText().toString();
                String password=passworEdit.getText().toString();
                Log.d("MiTagPrincipal","Pulsado");
                //Toast.makeText(getBaseContext(),"El usuario ha hecho click",Toast.LENGTH_SHORT).show();
                // Comprobar si los campos están vacios o no
                if (!user.isEmpty() && !password.isEmpty()) {
                    //comprobar si es igual o no
                    if (dbHelper.verificarUsuario(user,password)){
                        // usuario se instancia con el método get usuario con lso datos que coincidan con usuario y contraseña
                        usuario=dbHelper.getUsuario(user,password);
                        // se crea un intent para pasar a la siguiente activity
                        Intent intent = new Intent(MainActivity.this, Logueado.class);
                        // se inicia ese intent
                        startActivity(intent);
                    }
                    // si no es igual sale un toast indicando que no son correctos
                    else {
                        Toast.makeText(getBaseContext(),"Usuario o passwod incorrectos",Toast.LENGTH_SHORT).show();
                    }
                }
                // si faltan datos, el toast lo indica
                else {
                    Toast.makeText(getBaseContext(),"Usuario o password sin datos",Toast.LENGTH_SHORT).show();
                }
            }
        });
        // listener del botón de registrar, si se pulsa, se abre otro activity
        buttonRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // se crea un intent para pasar a la activity de registro
                Intent intent = new Intent(MainActivity.this, Registro.class);
                // se inicia el intent
                startActivity(intent);
            }
        });

    }

}