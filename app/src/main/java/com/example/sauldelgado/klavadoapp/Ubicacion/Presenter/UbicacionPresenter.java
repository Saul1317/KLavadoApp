package com.example.sauldelgado.klavadoapp.Ubicacion.Presenter;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.google.android.gms.maps.model.LatLng;

public interface UbicacionPresenter {
    void getUbicacionActualGPS(Context context);
    void updateLocacionActual(Location location);
    void getNombreDireccion(Context context, Geocoder geocoder, LatLng latLng);
    void buscarDireccion(Context context, String txt_direccion, Geocoder geocoder);
    void guardarDireccion(Context context, ConexionSQLite conn, Geocoder geocoder, LatLng latLng);
    void limpiarBuscador();
    void regresarActivity();
}
