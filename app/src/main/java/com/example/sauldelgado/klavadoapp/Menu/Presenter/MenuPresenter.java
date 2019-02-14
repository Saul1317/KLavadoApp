package com.example.sauldelgado.klavadoapp.Menu.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

import java.util.ArrayList;

public interface MenuPresenter {
    void abrirServicioExpress();
    void abrirLogin();
    void abrirRegistrar();
    void abrirTerminosCondiciones();

    void verEstatusTerminos(boolean estatus);
    void bloquearBotones();
    void desbloquearBotones();

    void getCiudadesDisponibles();
    void addInformation(ConexionSQLite conn, String nombre, String telefono);

}
