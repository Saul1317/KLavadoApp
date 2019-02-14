package com.example.sauldelgado.klavadoapp.TipoVehiculo.Interator;

import android.content.SharedPreferences;

import com.example.sauldelgado.klavadoapp.Data.Local.SharedPreference.MetodosSharedPreference;
import com.example.sauldelgado.klavadoapp.TipoVehiculo.Presenter.OnTipoVehiculoFinish;

public class TipoVehiculoInteratorImpl implements  TipoVehiculoInterator{

    @Override
    public void GuardarTipoVehiculo(SharedPreferences sharedPreferences, String tipo_vehiculo, OnTipoVehiculoFinish onTipoVehiculoFinish) {
        MetodosSharedPreference.GuardarTipoVehiculoPref(sharedPreferences, tipo_vehiculo);
        onTipoVehiculoFinish.CargarServiciosDisponibles();
    }

}
