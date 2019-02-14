package com.example.sauldelgado.klavadoapp.Pago.Presenter;

public interface OnPagoFinish {
    void setTotalPrice(String totalPrice);
    void PagoCompletado();
    void PagoError();
    void MostrarFacturacionAgregadaCorrectamente();
    void MostrarSinFactura();
    void AbrirDatosFacturacion();
    void dialogEliminarDatosFacturacion();
    void dialogDatosFacturacion();
}
