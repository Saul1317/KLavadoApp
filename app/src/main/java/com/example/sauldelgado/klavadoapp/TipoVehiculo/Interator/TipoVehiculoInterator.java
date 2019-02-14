package com.example.sauldelgado.klavadoapp.TipoVehiculo.Interator;

import android.content.SharedPreferences;

import com.example.sauldelgado.klavadoapp.TipoVehiculo.Presenter.OnTipoVehiculoFinish;

public interface TipoVehiculoInterator {
    void GuardarTipoVehiculo(SharedPreferences sharedPreferences, String tipo_vehiculo, OnTipoVehiculoFinish onTipoVehiculoFinish);
}
