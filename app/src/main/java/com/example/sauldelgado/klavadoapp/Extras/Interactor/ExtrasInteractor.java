package com.example.sauldelgado.klavadoapp.Extras.Interactor;

import com.example.sauldelgado.klavadoapp.Extras.Presenter.OnSelectedExtraFinish;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface ExtrasInteractor {
    void cargarExtras(String tipo_vehiculo, OnSelectedExtraFinish onSelectedExtraFinish);
    void sacarPrecioTotal(ConexionSQLite conn, OnSelectedExtraFinish onSelectedExtraFinish);
    void addExtraToCart(ConexionSQLite conn, String servicio, double precio_servicio, String parametro, OnSelectedExtraFinish onSelectedExtraFinish);
    void removeExtraToCart(ConexionSQLite conn, String servicio, OnSelectedExtraFinish onSelectedExtraFinish);
    //CONSULTAR OFERTA SEMANAL
    void CargarOfertaSemanal(String tipo_vehiculo, OnSelectedExtraFinish onSelectedExtraFinish);
}
