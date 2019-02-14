package com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Fragment;

import android.content.SharedPreferences;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;
import com.example.sauldelgado.klavadoapp.TipoLavado.Interator.TipoLavadoInterator;
import com.example.sauldelgado.klavadoapp.TipoLavado.Interator.TipoLavadoInteratorImpl;
import com.example.sauldelgado.klavadoapp.TipoLavado.View.Fragment.LavadoEcologicoFragment;

import java.util.List;

public class LavadoEcologicoFragmentPresenterImpl implements LavadoEcologicoFragmentPresenter, OnLavadoFragmentFinish {

    LavadoEcologicoFragment lavadoEcologicoFragment;
    TipoLavadoInterator tipoLavadoInterator;

    public LavadoEcologicoFragmentPresenterImpl(LavadoEcologicoFragment lavadoEcologicoFragment) {
        this.lavadoEcologicoFragment = lavadoEcologicoFragment;
        tipoLavadoInterator = new TipoLavadoInteratorImpl();
    }

    @Override
    public void getlavadosEcologico(String vehiculos) {
        tipoLavadoInterator.getlavadosEcologico(vehiculos, this);
        lavadoEcologicoFragment.showProgressBar();
        lavadoEcologicoFragment.hideRecyclerview();
    }

    @Override
    public void addProductToCart(ConexionSQLite conn, Producto producto_selected, SharedPreferences sharedPreferences) {
        tipoLavadoInterator.addProductToCart(conn, producto_selected, sharedPreferences, this);
    }

    @Override
    public void setDescriptionServices(List<Producto> productoList) {
        lavadoEcologicoFragment.setLavadoEcologicoContenido(productoList);
        lavadoEcologicoFragment.hideProgressBar();
        lavadoEcologicoFragment.showRecyclerview();
    }

    @Override
    public void getServiciosError() {
        lavadoEcologicoFragment.setErrorConsultaRemota();
        lavadoEcologicoFragment.hideProgressBar();
    }

    @Override
    public void addSuccessful() {
        lavadoEcologicoFragment.openExtras();
    }

    @Override
    public void addUnsuccessful() {
        lavadoEcologicoFragment.setErrorInsertLocal();
    }
}
