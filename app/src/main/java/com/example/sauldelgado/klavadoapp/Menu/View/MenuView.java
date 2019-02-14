package com.example.sauldelgado.klavadoapp.Menu.View;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.LugaresDisponible.Presenter.OnLugaresDisponiblesFinish;

import java.util.ArrayList;

public interface MenuView {
    void cargarViews();
    void abrirServicioExpress();
    void abrirLogin();
    void abrirRegistrar();
    void abrirTerminosCondiciones();
    void bloquearBotones();
    void desbloquearBotones();
    void llenarSpinner(ArrayList<String> lugaresDisponibles);
    void NombreVacio();
    void NumeroVacio();
    void faltanNumeros();
    void GuardadoIncorrecto();
}
