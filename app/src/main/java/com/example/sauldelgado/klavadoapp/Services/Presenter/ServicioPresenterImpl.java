package com.example.sauldelgado.klavadoapp.Services.Presenter;

import android.content.SharedPreferences;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Services.Interactor.ServicioInteractor;
import com.example.sauldelgado.klavadoapp.Services.Interactor.ServicioInteractorImpl;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;
import com.example.sauldelgado.klavadoapp.Services.View.Servicio;

import java.util.List;

public class ServicioPresenterImpl implements ServicioPresenter, OnSelectedVehiculeFinish {

    private Servicio servicio;
    private ServicioInteractor servicioInteractor;

    public ServicioPresenterImpl(Servicio servicio) {
        this.servicio = servicio;
        servicioInteractor = new ServicioInteractorImpl();
    }

    @Override
    public void getSeriviciosDisponibles(String tipo_vehiculo) {
        servicioInteractor.getSeriviciosDisponibles(tipo_vehiculo,this);
        //servicio.showProgressBar();
        //servicio.hideServices();
    }

    @Override
    public void addProductToCart(ConexionSQLite conn, Producto producto_selected, SharedPreferences sharedPreferences) {
        servicioInteractor.addProductToCart(conn, producto_selected, sharedPreferences, this);
    }

    @Override
    public void CargarTipoServicio() {
        servicio.CargarTipoServicio();
    }

    @Override
    public void CargarAnimacionTitulo() {
        servicio.AnimacionTitulo1();
        servicio.AnimacionTitulo2();
    }

    @Override
    public void RegresarMainActivity() {
        servicio.RegresarMainActivity();
    }

    @Override
    public void setDescriptionServicesEcologico(List<Producto> productoList) {
        servicio.hideProgressBar();
        servicio.ServiceDescriptionEcologico(productoList);
        servicio.showServices();
    }

    @Override
    public void setDescriptionServicesHidrolavado(List<Producto> productoList) {
        servicio.hideProgressBar();
        servicio.ServiceDescriptionHidrolavado(productoList);
        servicio.showServices();
    }

    @Override
    public void getServiciosError() {
        servicio.hideProgressBar();
        servicio.getErrorDescripcion();
        servicio.hideServices();
    }

    @Override
    public void addSuccessful() {
        servicio.openExtras();
    }

    @Override
    public void addUnsuccessful() {
        servicio.ErrorSQL();
    }
}
