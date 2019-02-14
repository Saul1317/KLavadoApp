package com.example.sauldelgado.klavadoapp.Menu.Interactor;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Menu.Presenter.OnMenuPrincipalFinish;

public interface MenuInteractor {
    void validarEstatusTerminos(boolean estatus, OnMenuPrincipalFinish onMenuPrincipalFinish);
    void getCiudadesDisponibles(OnMenuPrincipalFinish onMenuPrincipalFinish);
    void addInformation(ConexionSQLite conn, String nombre, String telefono, OnMenuPrincipalFinish onMenuPrincipalFinish);
}
