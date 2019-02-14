package com.example.sauldelgado.klavadoapp.LugaresDisponible.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.LugaresDisponible.Interator.LugaresDisponiblesInterator;
import com.example.sauldelgado.klavadoapp.LugaresDisponible.Interator.LugaresDisponiblesInteratorImpl;
import com.example.sauldelgado.klavadoapp.LugaresDisponible.View.LugaresDisponiblesView;

import java.util.ArrayList;

public class LugaresDisponiblesPresenterImpl implements LugaresDisponiblesPresenter, OnLugaresDisponiblesFinish{

    LugaresDisponiblesView lugaresDisponiblesView;
    LugaresDisponiblesInterator lugaresDisponiblesInterator;

    public LugaresDisponiblesPresenterImpl(LugaresDisponiblesView lugaresDisponiblesView) {
        this.lugaresDisponiblesView = lugaresDisponiblesView;
        lugaresDisponiblesInterator = new LugaresDisponiblesInteratorImpl();
    }

    @Override
    public void getCiudadesDisponibles() {
        lugaresDisponiblesInterator.getCiudadesDisponibles(this);
    }

    @Override
    public void addTelefono(ConexionSQLite conn, String telefono) {
        lugaresDisponiblesInterator.addTelefono(conn, telefono, this);
    }

    @Override
    public void setCiudadesDisponibles(ArrayList<String> ciudadesDisponibles) {
        lugaresDisponiblesView.llenarSpinner(ciudadesDisponibles);
    }

    @Override
    public void addTelefonoSuccessful() {
        lugaresDisponiblesView.addTelefonoSuccessful();
    }

    @Override
    public void addTelefonoUnsuccessful() {
        lugaresDisponiblesView.addTelefonoUnsuccessful();
    }

    @Override
    public void telefonoIncompleto() {
        lugaresDisponiblesView.telefonoIncompleto();
    }

    @Override
    public void telefonoVacio() {
        lugaresDisponiblesView.telefonoVacio();
    }
}
