package com.example.sauldelgado.klavadoapp.LugaresDisponible.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface LugaresDisponiblesPresenter {

    void getCiudadesDisponibles();
    void addTelefono(ConexionSQLite conn, String telefono);

}
