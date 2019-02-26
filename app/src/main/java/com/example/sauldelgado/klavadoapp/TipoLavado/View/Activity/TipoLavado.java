package com.example.sauldelgado.klavadoapp.TipoLavado.View.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.sauldelgado.klavadoapp.Menu.View.MenuPrincipal;
import com.example.sauldelgado.klavadoapp.Pago.Adapter.ViewPagerAdapter;
import com.example.sauldelgado.klavadoapp.Pago.View.Fragment.EfectivoFragment;
import com.example.sauldelgado.klavadoapp.Pago.View.Fragment.TarjetaCreditoFragment;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Activity.TipoLavadoPresenter;
import com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Activity.TipoLavadoPresenterImpl;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Fragment.LavadoEcologicoFragmentImpl;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Fragment.LavadoPremiumFragmentImpl;

public class TipoLavado extends AppCompatActivity implements TipoLavadoView, CompoundButton.OnCheckedChangeListener {

    TipoLavadoPresenter tipoLavadoPresenter;
    TabLayout tablayout_tipo_pago;
    ViewPager viewpager_tipo_pago;
    int INDEX_TAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_lavado);
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
        tipoLavadoPresenter = new TipoLavadoPresenterImpl(this);
        SacarTipoLavado();
        tablayout_tipo_pago = (TabLayout) findViewById(R.id.tablayout_tipo_lavado);
        viewpager_tipo_pago = (ViewPager) findViewById(R.id.viewpager_tipo_lavado);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.AddFragment(new LavadoEcologicoFragmentImpl(), "Ecologico");
        viewPagerAdapter.AddFragment(new LavadoPremiumFragmentImpl(), "Premium");
        viewpager_tipo_pago.setAdapter(viewPagerAdapter);
        tablayout_tipo_pago.setupWithViewPager(viewpager_tipo_pago);
        tablayout_tipo_pago.getTabAt(INDEX_TAB).select();
    }

    @Override
    public void abrirLavadoEcologico() {

    }

    @Override
    public void abrirLavadoPremium() {

    }

    @Override
    public void cambiarColorEcologico() {

    }

    @Override
    public void cambiarColorPremium() {

    }

    @Override
    public void SacarTipoLavado() {
        Bundle parametros = this.getIntent().getExtras();
        if(parametros !=null){
            String datos = parametros.getString("tipo_lavado");
            Log.e("Tipo Lavado", datos);
            if(datos.equals("ecologico")){
                INDEX_TAB = 0;
            }else{
                INDEX_TAB = 1;
            }
        }else{
            INDEX_TAB = 0;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }
}
