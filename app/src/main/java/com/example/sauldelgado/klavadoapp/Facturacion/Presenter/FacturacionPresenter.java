package com.example.sauldelgado.klavadoapp.Facturacion.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface FacturacionPresenter {
    void addFacturacion(ConexionSQLite conn, String RFC, String razon_social, String tipo_pago, String correo);
    void dialogoFacturacion();
}
