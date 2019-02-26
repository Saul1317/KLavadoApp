package com.example.sauldelgado.klavadoapp.TipoServicio.Presenter;

public interface TipoServicioPresenter {
    void CargarServicioLimpieza();
    void CargarMenuPrincipal();

    void EjecutarAnimacionCardviewLimpieza();
    void EjecutarAnimacionCardviewMantenimiento();
    void EjecutarAnimacionCardviewDetallado();
}
