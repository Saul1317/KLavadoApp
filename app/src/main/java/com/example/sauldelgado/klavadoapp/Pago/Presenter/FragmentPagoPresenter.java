package com.example.sauldelgado.klavadoapp.Pago.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public interface FragmentPagoPresenter {
    void InsertarInformacion(ConexionSQLite conn, String numero_tarjeta, String nombre, String fecha_vencimiento, String codigo_seguridad);
}
