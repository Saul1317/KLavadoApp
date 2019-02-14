package com.example.sauldelgado.klavadoapp.Extras.View;

import com.example.sauldelgado.klavadoapp.Extras.Model.Producto_Extras;

import java.util.List;

public interface ExtrasView {
    void LoadView();
    void showExtras(List<Producto_Extras> producto_extras);
    void hideExtras();
    void showDescripcionExtra(String titulo, String descripcion, String precio);
    void showProgressbar();
    void hideProgressbar();
    void showDialogConectionError();
    void setTotalPrice(String totalPrice);
    void CargarCitas();
    void RegresarActivity();
    void BloquearBotonContinuar();
    void DesbloquearBotonContinuar();

    //OCULTAR TARJETA DE OFERTAS
    void showOfertas();
    void hideOfertas();
    void setOfertaSemanal(String nombre, String precio_original, String precio_descuento, String porcentaje_descuento);
    void RegresarMainActivity();
    void AnimacionTitulo1();
    void AnimacionTitulo2();
}
