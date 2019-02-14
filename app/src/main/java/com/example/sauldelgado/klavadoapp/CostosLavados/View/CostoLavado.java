package com.example.sauldelgado.klavadoapp.CostosLavados.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sauldelgado.klavadoapp.CostosLavados.Presenter.CostoLavadoPresenter;
import com.example.sauldelgado.klavadoapp.CostosLavados.Presenter.CostoLavadoPresenterImpl;
import com.example.sauldelgado.klavadoapp.Data.Local.SharedPreference.MetodosSharedPreference;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Activity.TipoLavado;

public class CostoLavado extends AppCompatActivity implements CostoLavadoView, View.OnClickListener {

    CostoLavadoPresenter costoLavadoPresenter;
    CardView lavado_premium_costo, lavado_ecologico_costo;
    Animation tarjeta_premium_scale;
    TextView lavado_ecologico_precio_basico, lavado_ecologico_precio_completo, lavado_premium_precio_basico, lavado_premium_precio_completo;
    private SharedPreferences sharedPreferences;
    ImageView btn_back_costo_lavado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costo_lavado);
        costoLavadoPresenter = new CostoLavadoPresenterImpl(this);
        sharedPreferences = getSharedPreferences("klavado", Context.MODE_PRIVATE);
        lavado_ecologico_costo = (CardView) findViewById(R.id.lavado_ecologico_costo);
        lavado_ecologico_costo.setOnClickListener(this);
        lavado_premium_costo = (CardView) findViewById(R.id.lavado_premium_costo);
        lavado_premium_costo.setOnClickListener(this);

        btn_back_costo_lavado = (ImageView) findViewById(R.id.btn_back_costo_lavado);
        btn_back_costo_lavado.setOnClickListener(this);

        lavado_ecologico_precio_basico = (TextView) findViewById(R.id.lavado_ecologico_precio_basico);
        lavado_ecologico_precio_completo = (TextView) findViewById(R.id.lavado_ecologico_precio_completo);

        lavado_premium_precio_basico = (TextView) findViewById(R.id.lavado_premium_precio_basico);
        lavado_premium_precio_completo = (TextView) findViewById(R.id.lavado_premium_precio_completo);

        tarjeta_premium_scale = AnimationUtils.loadAnimation(this,R.anim.tarjeta_oferta_semanal);
        lavado_premium_costo.setAnimation(tarjeta_premium_scale);

        costoLavadoPresenter.getTipoLavado(MetodosSharedPreference.ObtenerTipoVehiculoPref(sharedPreferences));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lavado_ecologico_costo:
                abrirTipoLavado("ecologico");
                break;
            case R.id.lavado_premium_costo:
                abrirTipoLavado("premium");
                break;
            case R.id.btn_back_costo_lavado:
                onBackPressed();
                break;
        }
    }

    @Override
    public void abrirTipoLavado(String tipo_lavado) {
        Intent intentKlavadoPagos = new Intent(CostoLavado.this, TipoLavado.class);
        intentKlavadoPagos.putExtra("tipo_lavado", tipo_lavado);
        startActivity(intentKlavadoPagos);
    }

    @Override
    public void agregarPreciosEcologico(String basico, String completo) {
        lavado_ecologico_precio_basico.setText(basico);
        lavado_ecologico_precio_completo.setText(completo);
    }

    @Override
    public void agregarPreciosPremium(String basico, String completo) {
        lavado_premium_precio_basico.setText(basico);
        lavado_premium_precio_completo.setText(completo);
    }

}
