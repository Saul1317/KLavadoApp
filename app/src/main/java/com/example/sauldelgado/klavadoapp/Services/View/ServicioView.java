package com.example.sauldelgado.klavadoapp.Services.View;

import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;

import java.util.List;

public interface ServicioView {
    void LoadViews();
    void CargarTipoServicio();

    void showServices();
    void hideServices();

    void ServiceDescriptionEcologico(List<Producto> servicios);
    void ServiceDescriptionHidrolavado(List<Producto> servicios);

    void getErrorDescripcion();
    void ErrorSQL();

    void openExtras();

    void showProgressBar();
    void hideProgressBar();

    void RegresarMainActivity();

    void AnimacionTitulo1();
    void AnimacionTitulo2();
}
