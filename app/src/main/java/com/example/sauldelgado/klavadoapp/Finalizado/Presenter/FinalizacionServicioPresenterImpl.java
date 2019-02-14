package com.example.sauldelgado.klavadoapp.Finalizado.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Extras.Model.ExtrasSQLite;
import com.example.sauldelgado.klavadoapp.Finalizado.Interator.FinalizacionServicioInterator;
import com.example.sauldelgado.klavadoapp.Finalizado.Interator.FinalizacionServicioInteratorImpl;
import com.example.sauldelgado.klavadoapp.Finalizado.View.FinalizacionServicio;

import java.util.List;

public class FinalizacionServicioPresenterImpl implements FinalizacionServicioPresenter, OnFinalizacionServicioFinish{

    FinalizacionServicioInterator finalizacionServicioInterator;
    FinalizacionServicio finalizacionServicio;

    public FinalizacionServicioPresenterImpl(FinalizacionServicio finalizacionServicio) {
        this.finalizacionServicio = finalizacionServicio;
        finalizacionServicioInterator = new FinalizacionServicioInteratorImpl();
    }

    @Override
    public void CargarDatosDelServicio(ConexionSQLite conn, String fecha, String hora) {
        finalizacionServicioInterator.CargarDatosDelServicio(conn, fecha, hora, this);
    }

    @Override
    public void setInformacionDelServicio(String fecha, String hora, String nombre, String telefono, String fecha_servicio,
                                          String direccion, String servicio, String precio, String precio_total) {
        finalizacionServicio.setInformacionServicio(fecha, hora, nombre, telefono, fecha_servicio, direccion, servicio, precio, precio_total);
    }

    @Override
    public void setCargarExtrasSeleccionados(List<ExtrasSQLite> extrasSeleccionadosList) {
        finalizacionServicio.setExtrasServicio(extrasSeleccionadosList);
    }
}
