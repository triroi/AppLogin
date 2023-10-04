package com.example.applogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText userEdit= findViewById(R.id.user_edit);
        EditText passworEdit=findViewById(R.id.password_edit);
        TextView resultText= findViewById(R.id.result_set);
        Button button= findViewById(R.id.button);
        String userCheck="ines";
        String passwordCheck="ines";

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Toma el texto de lo escrito en EditText
                String user=userEdit.getText().toString();
                String password=passworEdit.getText().toString();
                // Comprobar si los campos están vacios o no
                if (!user.isEmpty() && !password.isEmpty()) {
                    //comprobar si es igual o no
                    if (user.equals(userCheck) && password.equals(passwordCheck)){

                        resultText.setText("Usuario y password son correctos");
                    }
                    else {
                        resultText.setText("Usuario o password son incorrectos");
                    }
                }
                else {
                    resultText.setText("Usuario o password sin datos");
                }
            }
        });
    }
}