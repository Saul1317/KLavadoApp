package com.example.sauldelgado.klavadoapp.Ubicacion.Presenter;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Ubicacion.Interator.UbicacionInterator;
import com.example.sauldelgado.klavadoapp.Ubicacion.Interator.UbicacionInteratorImpl;
import com.example.sauldelgado.klavadoapp.Ubicacion.View.UbicacionView;
import com.google.android.gms.maps.model.LatLng;

public class UbicacionPresenterImpl implements UbicacionPresenter, OnUbicacionInteratorFinish {

    private UbicacionView ubicacionView;
    private UbicacionInterator ubicacionInterator;

    public UbicacionPresenterImpl(UbicacionView ubicacionView) {
        this.ubicacionView = ubicacionView;
        ubicacionInterator = new UbicacionInteratorImpl();
    }

    @Override
    public void getUbicacionActualGPS(Context context) {
        ubicacionInterator.getUbicacionActualGPS(context, this);
        ubicacionView.bloquearBotones();
        ubicacionView.limpiarTxtDireccion();
        ubicacionView.showProgressBar();
    }

    @Override
    public void updateLocacionActual(Location location) {
        ubicacionInterator.updateLocacionActual(location, this);
    }

    @Override
    public void getNombreDireccion(Context context, Geocoder geocoder, LatLng latLng) {
        ubicacionInterator.getNombreDireccion(context, geocoder, latLng, this);
    }

    @Override
    public void buscarDireccion(Context context, String txt_direccion, Geocoder geocoder) {
        ubicacionInterator.buscarDireccion(context, txt_direccion, geocoder, this);
    }

    @Override
    public void guardarDireccion(Context context, ConexionSQLite conn, Geocoder geocoder, LatLng latLng) {
        ubicacionInterator.guardarDireccion(context, conn, geocoder, latLng, this);
    }

    @Override
    public void limpiarBuscador() {
        ubicacionView.limpiarBuscador();
    }

    @Override
    public void regresarActivity() {
        ubicacionView.regresarActivity();
    }

    @Override
    public void setLocacionActual(Location locacionActual) {
        ubicacionView.LocalizacionSuccessFul(locacionActual);
    }

    @Override
    public void setLocacionActualError() {
        ubicacionView.LocalizacionUnsuccessFul();
        ubicacionView.hideProgressBar();
        ubicacionView.desbloquearBotones();
    }

    @Override
    public void setGPSApagado() {

    }

    @Override
    public void setGPSEncendido() {

    }

    @Override
    public void updateLocacionActual(double latitud, double longitud) {
        ubicacionView.addMarcaMap(latitud, longitud);
    }

    @Override
    public void setNombreDireccion(String nombre_direccion) {
        ubicacionView.setNombreDireccion(nombre_direccion);
        ubicacionView.hideProgressBar();
        ubicacionView.desbloquearBotones();
    }

    @Override
    public void buscadorVacio() {
        ubicacionView.buscadorVacio();
    }

    @Override
    public void insertSuccessful() {
        ubicacionView.abrirSiguienteActivity();
    }

    @Override
    public void insertUnsuccessful() {
        ubicacionView.insertErrorSQL();
    }
}
