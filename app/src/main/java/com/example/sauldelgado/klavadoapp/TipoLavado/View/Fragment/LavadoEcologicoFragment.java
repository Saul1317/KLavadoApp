package com.example.sauldelgado.klavadoapp.TipoLavado.View.Fragment;

import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;

import java.util.List;

public interface LavadoEcologicoFragment {
    void setLavadoEcologicoContenido(List<Producto> servicioPremium);
    void showProgressBar();
    void hideProgressBar();
    void showRecyclerview();
    void hideRecyclerview();

    void setErrorConsultaRemota();
    void setErrorInsertLocal();
    void openExtras();
}
