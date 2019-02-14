package com.example.sauldelgado.klavadoapp.Facturacion.Interator;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Facturacion.Presenter.OnFacturacionFinish;

public interface FacturacionInterator {
    void addFacturacion(ConexionSQLite conn, String RFC, String razon_social, String tipo_pago, String correo, OnFacturacionFinish onFacturacionFinish);
}
