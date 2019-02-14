package com.example.sauldelgado.klavadoapp.Services.Interactor;

import android.content.SharedPreferences;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;
import com.example.sauldelgado.klavadoapp.Services.Presenter.OnSelectedVehiculeFinish;

public interface ServicioInteractor {
    void getSeriviciosDisponibles(String tipo_vehiculo, OnSelectedVehiculeFinish onSelectedVehiculeFinish);
    void addProductToCart(ConexionSQLite conn, Producto producto, SharedPreferences sharedPreferences, OnSelectedVehiculeFinish onSelectedVehiculeFinish);
}
