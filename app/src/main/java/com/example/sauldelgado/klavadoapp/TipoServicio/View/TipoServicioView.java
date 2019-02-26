package com.example.sauldelgado.klavadoapp.TipoServicio.View;

public interface TipoServicioView {
    void LoadView();
    void CargarServicioLimpieza();
    void CargarMenuPrincipal();
    void CargarServicioMantenimiento();

    void EjecutarAnimacionCardviewLimpieza();
    void EjecutarAnimacionCardviewMantenimiento();
    void EjecutarAnimacionCardviewDetallado();
}
