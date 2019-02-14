package com.example.sauldelgado.klavadoapp.Extras.Presenter;

import com.example.sauldelgado.klavadoapp.Extras.Model.Producto_Extras;

import java.util.List;

public interface OnSelectedExtraFinish {
    void setTotalPrice(String totalPrice);
    void showExtras(List<Producto_Extras> producto_extras);
    void ConsultarExtrasError();
    void SinOfertas();
    void setOfertas(String nombre, String precio_original, String precio_descuento, String porcentaje_descuento);
}
