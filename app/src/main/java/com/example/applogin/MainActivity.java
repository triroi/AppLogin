package com.example.applogin;

import androidx.appcompat.app.AppCompatActivity;

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


public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText userEdit= findViewById(R.id.user_edit);
        EditText passworEdit=findViewById(R.id.password_edit);
        TextView resultText= findViewById(R.id.result_set);
        Button button= findViewById(R.id.button);
        dbHelper=new DatabaseHelper(this);
        dbHelper.insertarUsuario("ines","ines",24, "Calle Gladiolo, 7");

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
                    if (dbHelper.verificarCredenciales(user,password)){
                        resultText.setText("Usuario y password son correctos");
                    }
                    else {
                        resultText.setText("Usuario o password son incorrectos");
                        button.setBackgroundColor(Color.RED);

                    }
                }
                else {
                    resultText.setText("Usuario o password sin datos");
                    button.setBackgroundColor(Color.RED);
                }
            }
        });
    }
}