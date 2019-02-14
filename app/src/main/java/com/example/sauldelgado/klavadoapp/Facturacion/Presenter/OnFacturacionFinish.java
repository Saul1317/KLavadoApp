package com.example.sauldelgado.klavadoapp.Facturacion.Presenter;

public interface OnFacturacionFinish {
    void facturacionGuardadaCorrectamente();
    void facturacionError();
    void CorreoVacio();
    void RFCvacio();
    void RazonSocialVacio();
}
