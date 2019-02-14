package com.example.sauldelgado.klavadoapp.Cita.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface CitaPresenter {
    void ConsultarHorariosDisponibles(String fecha);
    void agregarDatosPersonales(ConexionSQLite conn, String fecha, String horario, String descripcion_vehiculo);
    void MostrarDatePickerDialog();
    void RegresarActivity();
}
