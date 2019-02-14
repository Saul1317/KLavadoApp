package com.example.sauldelgado.klavadoapp.CostosLavados.Interator;

import com.example.sauldelgado.klavadoapp.CostosLavados.Presenter.OnCostoLavadoFinish;

public interface CostoLavadoInterator {
    void getCostoLavado(String vehiculos, OnCostoLavadoFinish onCostoLavadoFinish);
}
