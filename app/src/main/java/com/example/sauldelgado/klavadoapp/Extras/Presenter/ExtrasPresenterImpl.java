package com.example.sauldelgado.klavadoapp.Extras.Presenter;

import com.example.sauldelgado.klavadoapp.Extras.Interactor.ExtrasInteractor;
import com.example.sauldelgado.klavadoapp.Extras.Interactor.ExtrasInteractorImpl;
import com.example.sauldelgado.klavadoapp.Extras.Model.Producto_Extras;
import com.example.sauldelgado.klavadoapp.Extras.View.Extras;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

import java.util.List;

public class ExtrasPresenterImpl implements ExtrasPresenter, OnSelectedExtraFinish{

    private Extras extras;
    private ExtrasInteractor extrasInteractor;

    public ExtrasPresenterImpl(Extras extras) {
        this.extras = extras;
        extrasInteractor = new ExtrasInteractorImpl();
    }

    @Override
    public void cargarExtras(String tipo_vehiculo) {
        extrasInteractor.cargarExtras(tipo_vehiculo, this);
        extras.showProgressbar();
        extras.BloquearBotonContinuar();
    }

    @Override
    public void obtenerPreciototal(ConexionSQLite conn) {
        extrasInteractor.sacarPrecioTotal(conn, this);
    }

    @Override
    public void showDescripcionExtra(String titulo, String descripcion, String precio) {
        extras.showDescripcionExtra(titulo, descripcion, precio);
    }

    @Override
    public void addExtraToCart(ConexionSQLite conn, String servicio, double precio_servicio, String parametro) {
        extrasInteractor.addExtraToCart(conn, servicio, precio_servicio, parametro, this);
    }

    @Override
    public void removeExtraToCart(ConexionSQLite conn, String servicio) {
        extrasInteractor.removeExtraToCart(conn, servicio, this);
    }

    @Override
    public void CargarCitas() {
        extras.CargarCitas();
    }

    @Override
    public void RegresarActivity() {
        extras.RegresarActivity();
    }

    @Override
    public void CargarOfertaSemanal(String tipo_vehiculo) {
        extras.hideOfertas();
        extrasInteractor.CargarOfertaSemanal(tipo_vehiculo, this);
    }

    @Override
    public void RegresarMainActivity() {
        extras.RegresarActivity();
    }

    @Override
    public void CargarAnimacionTitulo() {
        extras.AnimacionTitulo1();
        extras.AnimacionTitulo2();
    }

    @Override
    public void setTotalPrice(String totalPrice) {
        extras.setTotalPrice(totalPrice);
    }

    @Override
    public void showExtras(List<Producto_Extras> producto_extras) {
        extras.hideProgressbar();
        extras.showExtras(producto_extras);
        extras.DesbloquearBotonContinuar();
    }

    @Override
    public void ConsultarExtrasError() {
        extras.hideProgressbar();
        extras.showDialogConectionError();
        extras.DesbloquearBotonContinuar();
    }

    @Override
    public void SinOfertas() {
        extras.hideOfertas();
    }

    @Override
    public void setOfertas(String nombre, String precio_original, String precio_descuento, String porcentaje_descuento) {
        extras.showOfertas();
        extras.setOfertaSemanal(nombre, precio_original, precio_descuento, porcentaje_descuento);
    }
}
