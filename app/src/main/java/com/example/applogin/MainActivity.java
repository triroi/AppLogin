package com.example.applogin;

import androidx.appcompat.app.AppCompatActivity;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText userEdit= findViewById(R.id.user_edit);
        EditText passworEdit=findViewById(R.id.password_edit);
        TextView resultText= findViewById(R.id.result_set);
        Button button= findViewById(R.id.button);



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
                    if (authenticateUser(user,password)){
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
    public boolean authenticateUser(String username, String password) {
        Connection connection = DatabaseConnector.connect();

        if (connection != null) {
            try {
                String query = "SELECT * FROM usuarios WHERE user=? AND password=?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                ResultSet resultSet = preparedStatement.executeQuery();
                boolean authenticated = resultSet.next();

                resultSet.close();
                preparedStatement.close();
                DatabaseConnector.disconnect();

                return authenticated;
            } catch (SQLException | java.sql.SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}