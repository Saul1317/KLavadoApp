package com.example.sauldelgado.klavadoapp.Splash.View;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sauldelgado.klavadoapp.LugaresDisponible.View.LugaresDisponibles;
import com.example.sauldelgado.klavadoapp.Menu.View.MenuPrincipal;
import com.example.sauldelgado.klavadoapp.Menu.View.MenuView;
import com.example.sauldelgado.klavadoapp.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CargarActivity();
            }
        },3000);
    }

    public void CargarActivity(){
        Intent intentKlavadoMenu = new Intent(SplashActivity.this, MenuPrincipal.class);
        intentKlavadoMenu.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentKlavadoMenu);
    }
}
