package com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Fragment;

import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;

import java.util.List;

public interface OnLavadoFragmentFinish {
    void setDescriptionServices(List<Producto> productoList);
    void getServiciosError();
    void addSuccessful();
    void addUnsuccessful();
}
