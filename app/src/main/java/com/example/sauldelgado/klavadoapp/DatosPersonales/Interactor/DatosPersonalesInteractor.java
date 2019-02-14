package com.example.sauldelgado.klavadoapp.DatosPersonales.Interactor;

import com.example.sauldelgado.klavadoapp.DatosPersonales.Presenter.OnDatosPersonalesFinish;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface DatosPersonalesInteractor {
    void addDatosPersonales(ConexionSQLite conn, String nombre, String correo, String tipo_vehiculo, String modelo_vehiculo, String color_vehiculo, String descripcion, OnDatosPersonalesFinish onDatosPersonalesFinish);
}
