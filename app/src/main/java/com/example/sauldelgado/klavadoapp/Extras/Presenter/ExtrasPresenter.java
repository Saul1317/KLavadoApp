package com.example.sauldelgado.klavadoapp.Extras.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface ExtrasPresenter {
    void cargarExtras(String tipo_vehiculo);
    void obtenerPreciototal(ConexionSQLite conn);
    void showDescripcionExtra(String titulo, String descripcion, String precio);
    void addExtraToCart(ConexionSQLite conn, String servicio, double precio_servicio, String parametro);
    void removeExtraToCart(ConexionSQLite conn, String servicio);
    void CargarCitas();
    void RegresarActivity();
    //CARGAR OFERTAS
    void CargarOfertaSemanal(String tipo_vehiculo);
    void RegresarMainActivity();
    void CargarAnimacionTitulo();
}
