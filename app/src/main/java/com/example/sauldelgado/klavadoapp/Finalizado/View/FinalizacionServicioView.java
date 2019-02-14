package com.example.sauldelgado.klavadoapp.Finalizado.View;

import com.example.sauldelgado.klavadoapp.Extras.Model.ExtrasSQLite;
import com.example.sauldelgado.klavadoapp.Extras.View.Extras;

import java.util.List;

public interface FinalizacionServicioView {
    void LoadViews();
    void setInformacionServicio(String fecha, String hora, String nombre, String telefono, String fecha_servicio, String direccion, String servicio, String precio, String precio_total);
    void setExtrasServicio(List<ExtrasSQLite> extrasList);
}
