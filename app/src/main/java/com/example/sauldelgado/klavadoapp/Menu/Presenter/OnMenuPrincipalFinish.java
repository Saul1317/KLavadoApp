package com.example.sauldelgado.klavadoapp.Menu.Presenter;

import java.util.ArrayList;

public interface OnMenuPrincipalFinish {
    void terminosAceptados();
    void terminosRechazados();
    void setCiudadesDisponibles(ArrayList<String> ciudadesDisponibles);
    void GuardadoCorrectamente();
    void GuardadoIncorrecto();
    void NombreVacio();
    void NumeroVacio();
    void faltanNumeros();
}
