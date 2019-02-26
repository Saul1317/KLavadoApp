package com.example.sauldelgado.klavadoapp.TipoServicio.View;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Menu.View.MenuPrincipal;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Services.View.Servicio;
import com.example.sauldelgado.klavadoapp.TipoServicio.Presenter.TipoServicioPresenter;
import com.example.sauldelgado.klavadoapp.TipoServicio.Presenter.TipoServicioPresenterImpl;
import com.example.sauldelgado.klavadoapp.TipoVehiculo.View.TipoVehiculo;

public class TipoServicio extends AppCompatActivity implements TipoServicioView , View.OnClickListener {

    private CardView cardview_tipo_servicio_limpieza, cardview_tipo_servicio_mantenimiento, cardview_tipo_servicio_soporte;
    private TipoServicioPresenter tipoServicioPresenter;
    Animation tarjeta_animation_izq, tarjeta_animation_der;
    ConstraintLayout contraintlayout_servicio;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_servicio);
        LoadView();
    }


    @Override
    public void LoadView() {
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

        tipoServicioPresenter = new TipoServicioPresenterImpl(this);

        contraintlayout_servicio = (ConstraintLayout) findViewById(R.id.contraintlayout_servicio);
        animationDrawable = (AnimationDrawable) contraintlayout_servicio.getBackground();
        animationDrawable.setEnterFadeDuration(7000);
        animationDrawable.setExitFadeDuration(7000);
        animationDrawable.start();

        cardview_tipo_servicio_limpieza = (CardView) findViewById(R.id.cardview_tipo_servicio_limpieza);
        cardview_tipo_servicio_limpieza.setOnClickListener(this);

        cardview_tipo_servicio_mantenimiento = (CardView) findViewById(R.id.cardview_tipo_servicio_mantenimiento);
        cardview_tipo_servicio_mantenimiento.setOnClickListener(this);

        cardview_tipo_servicio_soporte = (CardView) findViewById(R.id.cardview_tipo_servicio_soporte);
        cardview_tipo_servicio_soporte.setOnClickListener(this);

        tipoServicioPresenter.EjecutarAnimacionCardviewLimpieza();
        tipoServicioPresenter.EjecutarAnimacionCardviewMantenimiento();
        tipoServicioPresenter.EjecutarAnimacionCardviewDetallado();
    }

    @Override
    public void CargarServicioLimpieza() {
        Intent intentKlavadoTipoVehiculo = new Intent(TipoServicio.this, TipoVehiculo.class);
        startActivity(intentKlavadoTipoVehiculo);
    }

    @Override
    public void CargarMenuPrincipal() {
        onBackPressed();
    }

    @Override
    public void CargarServicioMantenimiento() {
        Toast.makeText(this, "Mantenimiento", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void EjecutarAnimacionCardviewLimpieza() {
        tarjeta_animation_izq = AnimationUtils.loadAnimation(this,R.anim.tipo_servicio_deslizar_izq);
        cardview_tipo_servicio_limpieza.setAnimation(tarjeta_animation_izq);
    }

    @Override
    public void EjecutarAnimacionCardviewMantenimiento() {
        tarjeta_animation_der = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.tipo_servicio_deslizar_der);
        cardview_tipo_servicio_mantenimiento.setAnimation(tarjeta_animation_der);
    }

    @Override
    public void EjecutarAnimacionCardviewDetallado() {
        tarjeta_animation_izq = AnimationUtils.loadAnimation(this,R.anim.tipo_servicio_deslizar_izq);
        cardview_tipo_servicio_soporte.setAnimation(tarjeta_animation_izq);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.cardview_tipo_servicio_limpieza:
                tipoServicioPresenter.CargarServicioLimpieza();
                break;

            case R.id.cardview_tipo_servicio_mantenimiento:
                break;

            case R.id.cardview_tipo_servicio_soporte:
                break;

            default:
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
