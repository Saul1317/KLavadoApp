package com.example.sauldelgado.klavadoapp.TipoServicio.Presenter;

import com.example.sauldelgado.klavadoapp.TipoServicio.Interator.TipoServicioInterator;
import com.example.sauldelgado.klavadoapp.TipoServicio.Interator.TipoServicioInteratorImpl;
import com.example.sauldelgado.klavadoapp.TipoServicio.View.TipoServicio;

public class TipoServicioPresenterImpl implements TipoServicioPresenter {

    private TipoServicio tipoServicio;
    private TipoServicioInterator tipoServicioInterator;

    public TipoServicioPresenterImpl(TipoServicio tipoServicio) {
        this.tipoServicio = tipoServicio;
        tipoServicioInterator = new TipoServicioInteratorImpl();
    }

    @Override
    public void CargarServicioLimpieza() {
        tipoServicio.CargarServicioLimpieza();
    }

    @Override
    public void CargarMenuPrincipal() {
        tipoServicio.CargarMenuPrincipal();
    }

    @Override
    public void EjecutarAnimacionTitulo1() {
        tipoServicio.EjecutarAnimacionTitulo1();
    }

    @Override
    public void EjecutarAnimacionTitulo2() {
        tipoServicio.EjecutarAnimacionTitulo2();
    }

    @Override
    public void EjecutarAnimacionCardviewLimpieza() {
        tipoServicio.EjecutarAnimacionCardviewLimpieza();
    }

    @Override
    public void EjecutarAnimacionCardviewMantenimiento() {
        tipoServicio.EjecutarAnimacionCardviewMantenimiento();
    }

    @Override
    public void EjecutarAnimacionCardviewDetallado() {
        tipoServicio.EjecutarAnimacionCardviewDetallado();
    }

}
