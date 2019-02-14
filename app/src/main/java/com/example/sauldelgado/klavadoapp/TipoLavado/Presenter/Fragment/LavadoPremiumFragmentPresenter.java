package com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Fragment;

import android.content.SharedPreferences;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;

public interface LavadoPremiumFragmentPresenter {
    void getlavadosPremium(String vehiculos);
    void addProductToCart(ConexionSQLite conn, Producto producto_selected, SharedPreferences sharedPreferences);
}
