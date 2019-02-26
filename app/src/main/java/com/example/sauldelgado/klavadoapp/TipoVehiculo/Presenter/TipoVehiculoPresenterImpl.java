package com.example.sauldelgado.klavadoapp.TipoVehiculo.Presenter;

import android.content.SharedPreferences;

import com.example.sauldelgado.klavadoapp.TipoVehiculo.Interator.TipoVehiculoInterator;
import com.example.sauldelgado.klavadoapp.TipoVehiculo.Interator.TipoVehiculoInteratorImpl;
import com.example.sauldelgado.klavadoapp.TipoVehiculo.View.TipoVehiculoView;

public class TipoVehiculoPresenterImpl implements TipoVehiculoPresenter, OnTipoVehiculoFinish{

    TipoVehiculoView tipoVehiculoView;
    TipoVehiculoInterator tipoVehiculoInterator;

    public TipoVehiculoPresenterImpl(TipoVehiculoView tipoVehiculoView) {
        this.tipoVehiculoView = tipoVehiculoView;
        tipoVehiculoInterator= new TipoVehiculoInteratorImpl();
    }

    @Override
    public void GuardarTipoVehiculo(SharedPreferences sharedPreferences, String tipo_vehiculo) {
        tipoVehiculoInterator.GuardarTipoVehiculo(sharedPreferences, tipo_vehiculo, this);
    }

    @Override
    public void CargarServiciosDisponibles() {
        tipoVehiculoView.AbrirServicios();
    }
}
