package com.example.sauldelgado.klavadoapp.CostosLavados.Presenter;

import com.example.sauldelgado.klavadoapp.CostosLavados.Interator.CostoLavadoInterator;
import com.example.sauldelgado.klavadoapp.CostosLavados.Interator.CostoLavadoInteratorImpl;
import com.example.sauldelgado.klavadoapp.CostosLavados.View.CostoLavadoView;

public class CostoLavadoPresenterImpl implements CostoLavadoPresenter, OnCostoLavadoFinish{

    CostoLavadoView costoLavadoView;
    CostoLavadoInterator costoLavadoInterator;


    public CostoLavadoPresenterImpl(CostoLavadoView costoLavadoView) {
        this.costoLavadoView = costoLavadoView;
        costoLavadoInterator = new CostoLavadoInteratorImpl();
    }

    @Override
    public void getTipoLavado(String tipo_vehiculo) {
        costoLavadoInterator.getCostoLavado(tipo_vehiculo, this);
    }

    @Override
    public void setCostoLavadoEcologico(String basico, String completo) {
        costoLavadoView.agregarPreciosEcologico(basico, completo);
    }

    @Override
    public void setCostoLavadoPremium(String basico, String completo) {
        costoLavadoView.agregarPreciosPremium(basico, completo);
    }
}
