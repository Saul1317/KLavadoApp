package com.example.sauldelgado.klavadoapp.TipoVehiculo.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.CostosLavados.View.CostoLavado;
import com.example.sauldelgado.klavadoapp.Menu.View.MenuPrincipal;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Services.View.Servicio;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Activity.TipoLavado;
import com.example.sauldelgado.klavadoapp.TipoVehiculo.Presenter.TipoVehiculoPresenter;
import com.example.sauldelgado.klavadoapp.TipoVehiculo.Presenter.TipoVehiculoPresenterImpl;

public class TipoVehiculo extends AppCompatActivity implements TipoVehiculoView, View.OnClickListener {

    CardView automovil_cardview_tipo_vehiculo, camioneta_suv_cardview_tipo_vehiculo, camioneta_cardview_tipo_vehiculo, min_cardview_tipo_vehiculo;
    private TipoVehiculoPresenter tipoVehiculoPresenter;
    private SharedPreferences sharedPreferences;
    ConstraintLayout constraintLayout_vehiculo;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_vehiculo);
        LoadViews();
    }

    @Override
    public void LoadViews() {

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            MenuPrincipal.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            MenuPrincipal.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        constraintLayout_vehiculo = (ConstraintLayout) findViewById(R.id.constraintLayout_vehiculo);
        animationDrawable = (AnimationDrawable) constraintLayout_vehiculo.getBackground();
        animationDrawable.setEnterFadeDuration(7000);
        animationDrawable.setExitFadeDuration(7000);
        animationDrawable.start();

        sharedPreferences = getSharedPreferences("klavado", Context.MODE_PRIVATE);

        automovil_cardview_tipo_vehiculo = (CardView) findViewById(R.id.automovil_cardview_tipo_vehiculo);
        automovil_cardview_tipo_vehiculo.setOnClickListener(this);

        camioneta_suv_cardview_tipo_vehiculo = (CardView) findViewById(R.id.camioneta_suv_cardview_tipo_vehiculo);
        camioneta_suv_cardview_tipo_vehiculo.setOnClickListener(this);

        camioneta_cardview_tipo_vehiculo = (CardView) findViewById(R.id.camioneta_cardview_tipo_vehiculo);
        camioneta_cardview_tipo_vehiculo.setOnClickListener(this);

        min_cardview_tipo_vehiculo = (CardView) findViewById(R.id.min_cardview_tipo_vehiculo);
        min_cardview_tipo_vehiculo.setOnClickListener(this);

        tipoVehiculoPresenter = new TipoVehiculoPresenterImpl(this);
    }

    @Override
    public void AbrirServicios() {
        Intent intentKlavadoServicios = new Intent(TipoVehiculo.this, CostoLavado.class);
        startActivity(intentKlavadoServicios);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.automovil_cardview_tipo_vehiculo:
                tipoVehiculoPresenter.GuardarTipoVehiculo(sharedPreferences,"auto");
                break;

            case R.id.camioneta_suv_cardview_tipo_vehiculo:
                tipoVehiculoPresenter.GuardarTipoVehiculo(sharedPreferences,"suv");
                break;

            case R.id.camioneta_cardview_tipo_vehiculo:
                tipoVehiculoPresenter.GuardarTipoVehiculo(sharedPreferences,"camioneta");
                break;

            case R.id.min_cardview_tipo_vehiculo:
                tipoVehiculoPresenter.GuardarTipoVehiculo(sharedPreferences,"minivan");
                break;

            default:
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
