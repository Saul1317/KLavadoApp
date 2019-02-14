package com.example.sauldelgado.klavadoapp.Pago.Interactor;

import com.example.sauldelgado.klavadoapp.Pago.Presenter.OnPagoFinish;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface PagoInteractor {
    void sacarPrecioTotal(ConexionSQLite conn, boolean estadoFacturacion, OnPagoFinish onPagoFinish);
    void InsertarInformacion(ConexionSQLite conn, String numero_tarjeta, String nombre, String fecha_vencimiento, String codigo_seguridad, OnPagoFinish onPagoFinish);
    void validarFacturacion(ConexionSQLite conn, OnPagoFinish onPagoFinish);
    void AbrirDatosFacturacion(boolean estatusFacturacion, OnPagoFinish onPagoFinish);
    void EliminarDatosFacturacion(ConexionSQLite conn, OnPagoFinish onPagoFinish);
}
