package com.example.sauldelgado.klavadoapp.Pago.Presenter;

import android.util.Log;

import com.example.sauldelgado.klavadoapp.Pago.Interactor.PagoInteractor;
import com.example.sauldelgado.klavadoapp.Pago.Interactor.PagoInteractorImpl;
import com.example.sauldelgado.klavadoapp.Pago.View.Activity.PagoView;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Pago.View.Fragment.TarjetaCreditoView;

public class PagoPresenterImpl implements PagoPresenter, FragmentPagoPresenter, OnPagoFinish {

    private PagoView pagoView;
    private TarjetaCreditoView tarjetaCreditoFragment;
    private PagoInteractor pagoInteractor;

    public PagoPresenterImpl(PagoView pagoView) {
        this.pagoView = pagoView;
        pagoInteractor = new PagoInteractorImpl();
    }

    public PagoPresenterImpl(TarjetaCreditoView tarjetaCreditoFragment) {
        this.tarjetaCreditoFragment = tarjetaCreditoFragment;
        pagoInteractor = new PagoInteractorImpl();
    }

    @Override
    public void obtenerPreciototal(ConexionSQLite conn, boolean estadoFacturacion) {
        pagoInteractor.sacarPrecioTotal(conn, estadoFacturacion, this);
    }

    @Override
    public void validarFacturacion(ConexionSQLite conn) {
        pagoInteractor.validarFacturacion(conn, this);
    }

    @Override
    public void AbrirDatosFacturacion(boolean estatusFacturacion) {
        pagoInteractor.AbrirDatosFacturacion(estatusFacturacion, this);
    }

    @Override
    public void AbrirFormularioDatosFacturacion() {
        tarjetaCreditoFragment.AbrirDatosFacturacion();
    }

    @Override
    public void EliminarDatosFacturacion(ConexionSQLite conn) {
        pagoInteractor.EliminarDatosFacturacion(conn, this);
    }

    //RESPUESTAS DEL INTERATOR

    @Override
    public void setTotalPrice(String totalPrice) {
        tarjetaCreditoFragment.setPrecioTotal(totalPrice);
    }

    @Override
    public void PagoCompletado() {
        tarjetaCreditoFragment.AbrirResumenDeCompra();
    }

    @Override
    public void PagoError() {
        tarjetaCreditoFragment.PagoError();
    }

    @Override
    public void MostrarFacturacionAgregadaCorrectamente() {
        tarjetaCreditoFragment.MostrarFacturacionAgregadaCorrectamente();
    }

    @Override
    public void MostrarSinFactura() {
        tarjetaCreditoFragment.MostrarSinFactura();
    }

    @Override
    public void AbrirDatosFacturacion() {
        tarjetaCreditoFragment.AbrirDatosFacturacion();
    }

    @Override
    public void dialogEliminarDatosFacturacion() {
        tarjetaCreditoFragment.dialogEliminarDatosFacturacion();
    }

    @Override
    public void dialogDatosFacturacion() {
        tarjetaCreditoFragment.dialogAceptarFacturacion();
    }

    @Override
    public void InsertarInformacion(ConexionSQLite conn, String numero_tarjeta, String nombre, String fecha_vencimiento, String codigo_seguridad) {
        pagoInteractor.InsertarInformacion(conn, numero_tarjeta, nombre, fecha_vencimiento, codigo_seguridad, this);
    }




}
