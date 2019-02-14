package com.example.sauldelgado.klavadoapp.Cita.Interactor;

import com.example.sauldelgado.klavadoapp.Cita.Presenter.OnCitaFinish;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface CitaInteractor {
    void ConsultarHorariosDisponibles(String fecha, OnCitaFinish onCitaFinish);
    void agregarInformacion(ConexionSQLite conn, String fecha, String horario, String descripcion_vehiculo, OnCitaFinish onCitaFinish);
}
