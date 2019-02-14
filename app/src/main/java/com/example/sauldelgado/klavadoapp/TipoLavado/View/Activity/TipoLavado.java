package com.example.sauldelgado.klavadoapp.TipoLavado.View.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Activity.TipoLavadoPresenter;
import com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Activity.TipoLavadoPresenterImpl;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Fragment.LavadoEcologicoFragmentImpl;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Fragment.LavadoPremiumFragmentImpl;

public class TipoLavado extends AppCompatActivity implements TipoLavadoView, CompoundButton.OnCheckedChangeListener {

    TipoLavadoPresenter tipoLavadoPresenter;
    SwitchCompat switch_tipo_lavado;
    TextView txt_ecologia, txt_premium;
    LavadoEcologicoFragmentImpl lavadoEcologicoFragment;
    LavadoPremiumFragmentImpl lavadoPremiumFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_lavado);
        lavadoEcologicoFragment = new LavadoEcologicoFragmentImpl();
        tipoLavadoPresenter = new TipoLavadoPresenterImpl(this);
        lavadoPremiumFragment = new LavadoPremiumFragmentImpl();
        txt_ecologia = (TextView) findViewById(R.id.txt_ecologia);
        txt_premium = (TextView) findViewById(R.id.txt_premium);
        switch_tipo_lavado = (SwitchCompat) findViewById(R.id.switch_tipo_lavado);
        switch_tipo_lavado.setOnCheckedChangeListener(this);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            String datos = parametros.getString("tipo_lavado");
            switch (datos){
                case "premium":
                    abrirLavadoPremium();
                    switch_tipo_lavado.setChecked(true);
                    cambiarColorPremium();
                    break;
                case "ecologico":
                    abrirLavadoEcologico();
                    switch_tipo_lavado.setChecked(false);
                    cambiarColorEcologico();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void abrirLavadoEcologico() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragment_tipo_lavado, lavadoEcologicoFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void abrirLavadoPremium() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.fragment_tipo_lavado, lavadoPremiumFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void cambiarColorEcologico() {
        txt_ecologia.setTextColor(getResources().getColor(R.color.icons));
        txt_premium.setTextColor(getResources().getColor(R.color.icons));
    }

    @Override
    public void cambiarColorPremium() {
        txt_ecologia.setTextColor(getResources().getColor(R.color.blac_selected));
        txt_premium.setTextColor(getResources().getColor(R.color.blac_selected));
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            abrirLavadoPremium();
            cambiarColorPremium();
        }else{
            abrirLavadoEcologico();
            cambiarColorEcologico();
        }
    }
}
