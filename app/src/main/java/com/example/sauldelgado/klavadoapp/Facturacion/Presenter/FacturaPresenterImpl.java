package com.example.sauldelgado.klavadoapp.Facturacion.Presenter;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Facturacion.Interator.FacturacionInterator;
import com.example.sauldelgado.klavadoapp.Facturacion.Interator.FacturacionInteratorImpl;
import com.example.sauldelgado.klavadoapp.Facturacion.View.FacturacionView;

public class FacturaPresenterImpl implements FacturacionPresenter, OnFacturacionFinish{

    FacturacionView facturacionView;
    FacturacionInterator facturacionInterator;

    public FacturaPresenterImpl(FacturacionView facturacionView) {
        this.facturacionView = facturacionView;
        facturacionInterator = new FacturacionInteratorImpl();
    }


    @Override
    public void addFacturacion(ConexionSQLite conn, String RFC, String razon_social, String tipo_pago, String correo) {
        facturacionInterator.addFacturacion(conn, RFC, razon_social, tipo_pago, correo, this);
    }

    @Override
    public void dialogoFacturacion() {
        facturacionView.dialogoFacturacion();
    }

    @Override
    public void facturacionGuardadaCorrectamente() {
        facturacionView.abrirSiguienteActivity();
    }

    @Override
    public void facturacionError() {
        facturacionView.facturacionError();
    }

    @Override
    public void CorreoVacio() {
        facturacionView.CorreoVacio();
    }

    @Override
    public void RFCvacio() {
        facturacionView.RFCvacio();
    }

    @Override
    public void RazonSocialVacio() {
        facturacionView.RazonSocialVacio();
    }


}
