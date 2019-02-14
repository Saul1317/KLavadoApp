package com.example.sauldelgado.klavadoapp.LugaresDisponible.View;

import java.util.ArrayList;

public interface LugaresDisponiblesView {
    void llenarSpinner(ArrayList<String> lugaresDisponibles);
    void addTelefonoSuccessful();
    void addTelefonoUnsuccessful();
    void telefonoIncompleto();
    void telefonoVacio();
}
