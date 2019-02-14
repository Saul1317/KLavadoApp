package com.example.sauldelgado.klavadoapp.Menu.Presenter;


import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Menu.Interactor.MenuInteractor;
import com.example.sauldelgado.klavadoapp.Menu.Interactor.MenuInteractorImpl;
import com.example.sauldelgado.klavadoapp.Menu.View.MenuView;

import java.util.ArrayList;

public class MenuPresenteImpl implements MenuPresenter, OnMenuPrincipalFinish {

    private MenuView menuView;
    private MenuInteractor menuInteractor;

    public MenuPresenteImpl(MenuView menuView) {
        this.menuView = menuView;
        menuInteractor = new MenuInteractorImpl();
    }

    @Override
    public void abrirServicioExpress() { menuView.abrirServicioExpress(); }

    @Override
    public void abrirLogin() { menuView.abrirLogin(); }

    @Override
    public void abrirRegistrar() { menuView.abrirRegistrar(); }

    @Override
    public void abrirTerminosCondiciones() { menuView.abrirTerminosCondiciones(); }

    @Override
    public void verEstatusTerminos(boolean estatus) {
        menuInteractor.validarEstatusTerminos(estatus, this);
    }

    @Override
    public void bloquearBotones() { menuView.bloquearBotones(); }

    @Override
    public void desbloquearBotones() { menuView.desbloquearBotones(); }

    @Override
    public void getCiudadesDisponibles() {
        menuInteractor.getCiudadesDisponibles(this);
    }

    @Override
    public void addInformation(ConexionSQLite conn, String nombre, String telefono) {
        menuInteractor.addInformation(conn, nombre, telefono, this);
    }

    @Override
    public void terminosAceptados() { menuView.desbloquearBotones(); }

    @Override
    public void terminosRechazados() { menuView.bloquearBotones(); }

    @Override
    public void setCiudadesDisponibles(ArrayList<String> ciudadesDisponibles) {
        menuView.llenarSpinner(ciudadesDisponibles);
    }

    @Override
    public void GuardadoCorrectamente() {
        menuView.abrirServicioExpress();
    }

    @Override
    public void GuardadoIncorrecto() {

    }


    @Override
    public void NombreVacio() {
        menuView.NombreVacio();
    }

    @Override
    public void NumeroVacio() {
        menuView.NumeroVacio();
    }

    @Override
    public void faltanNumeros() {
        menuView.faltanNumeros();
    }
}
