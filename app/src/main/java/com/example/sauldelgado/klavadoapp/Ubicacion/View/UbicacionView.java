package com.example.sauldelgado.klavadoapp.Ubicacion.View;

import android.location.Geocoder;
import android.location.Location;

public interface UbicacionView {
    void LoadViews();
    void LocalizacionSuccessFul(Location location);
    void LocalizacionUnsuccessFul();
    void addMarcaMap(double latitud, double longitud);
    void setNombreDireccion(String nombre_direccion);
    void TecladoActionSearch();
    void buscadorVacio();
    void abrirSiguienteActivity();
    void insertErrorSQL();

    void showProgressBar();
    void hideProgressBar();
    void bloquearBotones();
    void desbloquearBotones();
    void limpiarTxtDireccion();
    void limpiarBuscador();

    void regresarActivity();
}
