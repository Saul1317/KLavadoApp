package com.example.sauldelgado.klavadoapp.Finalizado.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface FinalizacionServicioPresenter {
    void CargarDatosDelServicio(ConexionSQLite conn, String fecha, String hora);
}
