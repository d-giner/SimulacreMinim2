package com.learning.ControlMinim2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    private TextView userTxt, passTxt, resTxt;
    private Button logBut;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logBut = findViewById(R.id.logintButton);
        userTxt = findViewById(R.id.usuariText);
        passTxt = findViewById(R.id.passwordText);
        resTxt = findViewById(R.id.resText);

        sharedPref = getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        if (sharedPref.contains("user")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivityForResult(intent, 0);
            finish();
        }
        else{
            loginUsuari();
        }
    }

    public void loginUsuari(){
        logBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resTxt.setText("Ok!");

                final String user, password;
                user = userTxt.getText().toString();
                password = passTxt.getText().toString();

                if (user.equals("user") && password.equals("dsamola")){
                    saveLoginSharedPreferences(user, password);

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivityForResult(intent, 0);
                    finish();
                }
                else{
                    userTxt.setText("");
                    passTxt.setText("");
                    resTxt.setText("Credencials incorrectes.");
                }
            }
        });
    }

    public void saveLoginSharedPreferences(String user, String pass){
        editor.putString("user",user);
        editor.putString("password",pass);
        editor.commit();
    }
}
