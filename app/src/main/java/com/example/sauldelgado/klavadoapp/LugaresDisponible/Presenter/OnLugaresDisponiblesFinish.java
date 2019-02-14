package com.example.sauldelgado.klavadoapp.LugaresDisponible.Presenter;

import java.util.ArrayList;

public interface OnLugaresDisponiblesFinish {
    void setCiudadesDisponibles(ArrayList<String> ciudadesDisponibles);
    void addTelefonoSuccessful();
    void addTelefonoUnsuccessful();
    void telefonoIncompleto();
    void telefonoVacio();
}
