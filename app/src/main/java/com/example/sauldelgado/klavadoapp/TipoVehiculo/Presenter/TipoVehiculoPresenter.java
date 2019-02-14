package com.example.sauldelgado.klavadoapp.TipoVehiculo.Presenter;

import android.content.SharedPreferences;

public interface TipoVehiculoPresenter {
    void GuardarTipoVehiculo(SharedPreferences sharedPreferences, String tipo_vehiculo);
    void CargarAnimacionTitulo();
    void ActivityBack();
}
