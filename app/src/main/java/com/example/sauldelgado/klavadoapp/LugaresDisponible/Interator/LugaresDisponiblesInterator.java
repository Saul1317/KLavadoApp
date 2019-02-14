package com.example.sauldelgado.klavadoapp.LugaresDisponible.Interator;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.LugaresDisponible.Presenter.OnLugaresDisponiblesFinish;

public interface LugaresDisponiblesInterator {
    void getCiudadesDisponibles(OnLugaresDisponiblesFinish onLugaresDisponiblesFinish);
    void addTelefono(ConexionSQLite conn, String telefono, OnLugaresDisponiblesFinish onLugaresDisponiblesFinish);
}
