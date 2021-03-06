package com.example.sauldelgado.klavadoapp.Pago.View.Activity;

import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sauldelgado.klavadoapp.Menu.View.MenuPrincipal;
import com.example.sauldelgado.klavadoapp.Pago.Presenter.PagoPresenter;
import com.example.sauldelgado.klavadoapp.Pago.Presenter.PagoPresenterImpl;
import com.example.sauldelgado.klavadoapp.Pago.View.Fragment.EfectivoFragment;
import com.example.sauldelgado.klavadoapp.Pago.View.Fragment.TarjetaCreditoFragment;
import com.example.sauldelgado.klavadoapp.Pago.Adapter.ViewPagerAdapter;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;

public class Pago extends AppCompatActivity implements PagoView, View.OnClickListener {

    FrameLayout fragmentContenido;
    //ImageView btn_back_pago;
    PagoPresenter pagoPresenter;
    Button button_pagar;
    ConexionSQLite conn;
    TextView txt_pago_total_final;

    ConstraintLayout contraintlayout_pagos;
    AnimationDrawable animationDrawable;

    /*NUEVA ACTIVITY*/

    AppBarLayout appbar_tipo_pago;
    TabLayout tablayout_tipo_pago;
    ViewPager viewpager_tipo_pago;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_tarjeta_lavado911);
        LoadViews();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_back_pago:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    @Override
    public void LoadViews() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            MenuPrincipal.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }

        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            MenuPrincipal.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        conn = new ConexionSQLite(this, "bd_producto", null, SQLiteTablas.VERSION_BD);
        pagoPresenter = new PagoPresenterImpl(this);
        contraintlayout_pagos = (ConstraintLayout) findViewById(R.id.contraintlayout_pagos);
        animationDrawable = (AnimationDrawable) contraintlayout_pagos.getBackground();
        animationDrawable.setEnterFadeDuration(7000);
        animationDrawable.setExitFadeDuration(7000);
        animationDrawable.start();
        txt_pago_total_final = (TextView) findViewById(R.id.txt_pago_total_final);
        button_pagar = (Button) findViewById(R.id.button_pagar);
        fragmentContenido = (FrameLayout) findViewById(R.id.fragmentContenido);
        //btn_back_pago = (ImageView) findViewById(R.id.btn_back_pago);
        //btn_back_pago.setOnClickListener(this);
        tablayout_tipo_pago = (TabLayout) findViewById(R.id.tablayout_tipo_pago);
        appbar_tipo_pago = (AppBarLayout) findViewById(R.id.appbar_tipo_pago);
        viewpager_tipo_pago = (ViewPager) findViewById(R.id.viewpager_tipo_pago);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.AddFragment(new TarjetaCreditoFragment(), "Pago con tarjeta");
        viewPagerAdapter.AddFragment(new EfectivoFragment(), "Pago en efectivo");
        viewpager_tipo_pago.setAdapter(viewPagerAdapter);
        tablayout_tipo_pago.setupWithViewPager(viewpager_tipo_pago);
    }
}
