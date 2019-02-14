package com.example.sauldelgado.klavadoapp.Finalizado.Interator;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Finalizado.Presenter.OnFinalizacionServicioFinish;

public interface FinalizacionServicioInterator {
    void CargarDatosDelServicio(ConexionSQLite conn, String fecha, String hora, OnFinalizacionServicioFinish onFinalizacionServicioFinish);
}
