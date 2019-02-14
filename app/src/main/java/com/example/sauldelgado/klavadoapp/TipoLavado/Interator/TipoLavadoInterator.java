package com.example.sauldelgado.klavadoapp.TipoLavado.Interator;

import android.content.SharedPreferences;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;
import com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Fragment.OnLavadoFragmentFinish;

public interface TipoLavadoInterator {
    void getlavadosPremium(String vehiculos, OnLavadoFragmentFinish onTipoLavadoFinish);
    void getlavadosEcologico(String vehiculos, OnLavadoFragmentFinish onTipoLavadoFinish);
    void addProductToCart(ConexionSQLite conn, Producto producto, SharedPreferences sharedPreferences, OnLavadoFragmentFinish onTipoLavadoFinish);
}
