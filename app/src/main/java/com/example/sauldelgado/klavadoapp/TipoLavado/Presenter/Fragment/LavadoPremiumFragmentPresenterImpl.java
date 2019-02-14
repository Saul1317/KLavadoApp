package com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Fragment;

import android.content.SharedPreferences;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;
import com.example.sauldelgado.klavadoapp.TipoLavado.Interator.TipoLavadoInterator;
import com.example.sauldelgado.klavadoapp.TipoLavado.Interator.TipoLavadoInteratorImpl;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Fragment.LavadoPremiumFragment;

import java.util.List;

public class LavadoPremiumFragmentPresenterImpl implements LavadoPremiumFragmentPresenter, OnLavadoFragmentFinish {

    LavadoPremiumFragment lavadoPremiumFragment;
    TipoLavadoInterator tipoLavadoInterator;


    public LavadoPremiumFragmentPresenterImpl(LavadoPremiumFragment lavadoPremiumFragment) {
        this.lavadoPremiumFragment = lavadoPremiumFragment;
        tipoLavadoInterator = new TipoLavadoInteratorImpl();
    }

    @Override
    public void getlavadosPremium(String vehiculos) {
        tipoLavadoInterator.getlavadosPremium(vehiculos, this);
        lavadoPremiumFragment.showProgressBar();
        lavadoPremiumFragment.hideRecyclerview();
    }

    @Override
    public void addProductToCart(ConexionSQLite conn, Producto producto_selected, SharedPreferences sharedPreferences) {
        tipoLavadoInterator.addProductToCart(conn, producto_selected, sharedPreferences, this);
    }

    @Override
    public void setDescriptionServices(List<Producto> productoList) {
        lavadoPremiumFragment.setLavadoPremiumContenido(productoList);
        lavadoPremiumFragment.hideProgressBar();
        lavadoPremiumFragment.showRecyclerview();
    }

    @Override
    public void getServiciosError() {
        lavadoPremiumFragment.setErrorConsultaRemota();
        lavadoPremiumFragment.hideProgressBar();
    }

    @Override
    public void addSuccessful() {
        lavadoPremiumFragment.openExtras();
    }

    @Override
    public void addUnsuccessful() {
        lavadoPremiumFragment.setErrorInsertLocal();
    }
}
