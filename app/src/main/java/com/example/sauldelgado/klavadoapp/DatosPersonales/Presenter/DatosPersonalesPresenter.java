package com.example.sauldelgado.klavadoapp.DatosPersonales.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface DatosPersonalesPresenter {
    void addDatosPersonales(ConexionSQLite conn, String nombre, String correo, String tipo_vehiculo, String modelo_vehiculo, String color_vehiculo, String descripcion);
    void RegresarActivity();
}
