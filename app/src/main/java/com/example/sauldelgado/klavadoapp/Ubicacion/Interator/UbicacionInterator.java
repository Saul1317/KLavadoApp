package com.example.sauldelgado.klavadoapp.Ubicacion.Interator;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Ubicacion.Presenter.OnUbicacionInteratorFinish;
import com.google.android.gms.maps.model.LatLng;

public interface UbicacionInterator {
    void getUbicacionActualGPS(Context context, OnUbicacionInteratorFinish onUbicacionInteratorFinish);
    void updateLocacionActual(Location location, OnUbicacionInteratorFinish onUbicacionInteratorFinish);
    void getNombreDireccion(Context context, Geocoder geocoder, LatLng latLng, OnUbicacionInteratorFinish onUbicacionInteratorFinish);
    void buscarDireccion(Context context, String nombre_direccion, Geocoder geocoder, OnUbicacionInteratorFinish onUbicacionInteratorFinish);
    void guardarDireccion(Context context, ConexionSQLite conn, Geocoder geocoder, LatLng latLng, OnUbicacionInteratorFinish onUbicacionInteratorFinish);
}
