package com.learning.ControlMinim2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide(); /** Ocultem l'action bar */

        new CountDownTimer(2000, 1000) { /** Timer de compte enrere per forçar l'splash screen 2 segons */
            @Override
            public void onTick(long millisUntilFinished) {}

            @Override
            public void onFinish() { /** Al finalitzar el timer, iniciem la main activity */
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivityForResult(intent, 0);
                finish();
            }
        }.start();
    }
}
