package com.example.sauldelgado.klavadoapp.Terminos.Presenter;

import com.example.sauldelgado.klavadoapp.Terminos.Interator.TerminosInterator;
import com.example.sauldelgado.klavadoapp.Terminos.Interator.TerminosInteratorImp;
import com.example.sauldelgado.klavadoapp.Terminos.View.TerminosView;

public class TerminosPresenterImp implements  TerminosPresenter{

    private TerminosView terminosView;
    private TerminosInterator terminosInterator;

    public TerminosPresenterImp(TerminosView terminosView) {
        this.terminosView = terminosView;
        terminosInterator = new TerminosInteratorImp();
    }

    @Override
    public void Regresar() {
        terminosView.Regresar();
    }
}
