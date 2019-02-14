package com.example.sauldelgado.klavadoapp.TipoVehiculo.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.CostosLavados.View.CostoLavado;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Services.View.Servicio;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Activity.TipoLavado;
import com.example.sauldelgado.klavadoapp.TipoVehiculo.Presenter.TipoVehiculoPresenter;
import com.example.sauldelgado.klavadoapp.TipoVehiculo.Presenter.TipoVehiculoPresenterImpl;

public class TipoVehiculo extends AppCompatActivity implements TipoVehiculoView, View.OnClickListener {

    private CardView automovil_cardview_tipo_vehiculo, camioneta_suv_cardview_tipo_vehiculo, camioneta_cardview_tipo_vehiculo, min_cardview_tipo_vehiculo;
    private TipoVehiculoPresenter tipoVehiculoPresenter;
    private SharedPreferences sharedPreferences;
    private TextView titulo1_tipo_vehiculo, titulo2_tipo_vehiculo;
    ImageView btn_back_tipo_vehiculo;
    Animation tarjeta_animation_izq, tarjeta_animation_der;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_vehiculo);
        LoadViews();
    }

    @Override
    public void LoadViews() {
        sharedPreferences = getSharedPreferences("klavado", Context.MODE_PRIVATE);
        titulo1_tipo_vehiculo = (TextView) findViewById(R.id.titulo1_tipo_vehiculo);
        titulo2_tipo_vehiculo = (TextView) findViewById(R.id.titulo2_tipo_vehiculo);

        btn_back_tipo_vehiculo = (ImageView) findViewById(R.id.btn_back_tipo_vehiculo);
        btn_back_tipo_vehiculo.setOnClickListener(this);

        automovil_cardview_tipo_vehiculo = (CardView) findViewById(R.id.automovil_cardview_tipo_vehiculo);
        automovil_cardview_tipo_vehiculo.setOnClickListener(this);

        camioneta_suv_cardview_tipo_vehiculo = (CardView) findViewById(R.id.camioneta_suv_cardview_tipo_vehiculo);
        camioneta_suv_cardview_tipo_vehiculo.setOnClickListener(this);

        camioneta_cardview_tipo_vehiculo = (CardView) findViewById(R.id.camioneta_cardview_tipo_vehiculo);
        camioneta_cardview_tipo_vehiculo.setOnClickListener(this);

        min_cardview_tipo_vehiculo = (CardView) findViewById(R.id.min_cardview_tipo_vehiculo);
        min_cardview_tipo_vehiculo.setOnClickListener(this);

        tipoVehiculoPresenter = new TipoVehiculoPresenterImpl(this);
        tipoVehiculoPresenter.CargarAnimacionTitulo();
    }

    @Override
    public void AbrirServicios() {
        Intent intentKlavadoServicios = new Intent(TipoVehiculo.this, CostoLavado.class);
        startActivity(intentKlavadoServicios);
    }

    @Override
    public void AnimacionTitulo1() {
        tarjeta_animation_der = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.tipo_servicio_deslizar_der);
        titulo1_tipo_vehiculo.setAnimation(tarjeta_animation_der);
    }

    @Override
    public void AnimacionTitulo2() {
        tarjeta_animation_izq = AnimationUtils.loadAnimation(this,R.anim.tipo_servicio_deslizar_izq);
        titulo2_tipo_vehiculo.setAnimation(tarjeta_animation_izq);
    }

    @Override
    public void ActivityBack() {
        onBackPressed();
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

            case R.id.btn_back_tipo_vehiculo:
                tipoVehiculoPresenter.ActivityBack();
                break;
            default:
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
