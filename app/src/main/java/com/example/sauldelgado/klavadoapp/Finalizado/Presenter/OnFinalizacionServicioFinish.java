package com.example.sauldelgado.klavadoapp.Finalizado.Presenter;

import com.example.sauldelgado.klavadoapp.Extras.Model.ExtrasSQLite;
import java.util.List;

public interface OnFinalizacionServicioFinish {

    void setInformacionDelServicio(String fecha, String hora, String nombre, String telefono, String fecha_servicio, String direccion, String servicio, String precio, String precio_total);
    void setCargarExtrasSeleccionados(List<ExtrasSQLite> extrasSeleccionadosList);
}
