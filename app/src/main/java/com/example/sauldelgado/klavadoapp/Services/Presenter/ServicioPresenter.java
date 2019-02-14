package com.example.sauldelgado.klavadoapp.Services.Presenter;


import android.content.SharedPreferences;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;

public interface ServicioPresenter {
    void getSeriviciosDisponibles(String tipo_vehiculo);
    void addProductToCart(ConexionSQLite conn, Producto producto_selected, SharedPreferences sharedPreferences);
    void CargarTipoServicio();
    void CargarAnimacionTitulo();
    void RegresarMainActivity();
}
