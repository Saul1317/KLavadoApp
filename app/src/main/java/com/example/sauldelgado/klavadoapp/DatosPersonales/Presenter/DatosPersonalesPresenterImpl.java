package com.example.sauldelgado.klavadoapp.DatosPersonales.Presenter;

import com.example.sauldelgado.klavadoapp.DatosPersonales.Interactor.DatosPersonalesInteractor;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Interactor.DatosPersonalesInteractorImpl;
import com.example.sauldelgado.klavadoapp.DatosPersonales.View.DatosPersonales;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

public class DatosPersonalesPresenterImpl implements  DatosPersonalesPresenter, OnDatosPersonalesFinish{

    private DatosPersonales datosPersonales;
    private DatosPersonalesInteractor datosPersonalesInteractor;

    public DatosPersonalesPresenterImpl(DatosPersonales datosPersonales) {
        this.datosPersonales = datosPersonales;
        datosPersonalesInteractor = new DatosPersonalesInteractorImpl();
    }

    @Override
    public void addDatosPersonales(ConexionSQLite conn, String nombre, String correo, String tipo_vehiculo, String modelo_vehiculo, String color_vehiculo, String descripcion) {
        datosPersonalesInteractor.addDatosPersonales(conn, nombre, correo, tipo_vehiculo, modelo_vehiculo, color_vehiculo, descripcion,  this);
    }

    @Override
    public void RegresarActivity() {
        datosPersonales.RegresarActivity();
    }

    @Override
    public void addSuccessful() {
        datosPersonales.abrirPago();
    }

    @Override
    public void addUnsuccessful() {

    }

    @Override
    public void nombreError() {
        datosPersonales.nombreError();
    }

    @Override
    public void correoError() {
        datosPersonales.correoError();
    }

    @Override
    public void comentarioError() {

    }
}
