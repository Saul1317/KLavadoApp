package com.example.sauldelgado.klavadoapp.Services.Presenter;

import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;

import java.util.List;

public interface OnSelectedVehiculeFinish {
    void setDescriptionServicesEcologico(List<Producto> productoList);
    void setDescriptionServicesHidrolavado(List<Producto> productoList);
    void getServiciosError();
    void addSuccessful();
    void addUnsuccessful();
}
