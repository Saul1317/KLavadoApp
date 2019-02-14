package com.example.sauldelgado.klavadoapp.Pago.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface PagoPresenter {
    void obtenerPreciototal(ConexionSQLite conn, boolean estadoFacturacion);
    void validarFacturacion(ConexionSQLite conn);
    void AbrirDatosFacturacion(boolean estatusFacturacion);
    void AbrirFormularioDatosFacturacion();
    void EliminarDatosFacturacion(ConexionSQLite conn);
}
