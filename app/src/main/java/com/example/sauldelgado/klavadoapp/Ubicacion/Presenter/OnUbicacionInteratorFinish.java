package com.example.sauldelgado.klavadoapp.Ubicacion.Presenter;

import android.content.Context;
import android.location.Location;

public interface OnUbicacionInteratorFinish {
    void setLocacionActual(Location locacionActual);
    void setLocacionActualError();
    void setGPSApagado();
    void setGPSEncendido();
    void updateLocacionActual(double latitud, double longitud);
    void setNombreDireccion(String nombre_direccion);
    void buscadorVacio();
    void insertSuccessful();
    void insertUnsuccessful();
}
