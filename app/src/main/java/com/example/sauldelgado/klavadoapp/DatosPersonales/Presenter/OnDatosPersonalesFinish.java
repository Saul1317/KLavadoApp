package com.example.sauldelgado.klavadoapp.DatosPersonales.Presenter;

public interface OnDatosPersonalesFinish {

    void addSuccessful();
    void addUnsuccessful();

    void nombreError();
    void correoError();
    void comentarioError();
}
