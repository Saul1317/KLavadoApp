package com.example.sauldelgado.klavadoapp.Pago.View.Fragment;

public interface TarjetaCreditoView {
    void AbrirResumenDeCompra();
    void setPrecioTotal(String precioTotal);
    void PagoError();
    void MostrarFacturacionAgregadaCorrectamente();
    void MostrarSinFactura();
    void AbrirDatosFacturacion();
    void dialogEliminarDatosFacturacion();
    void dialogAceptarFacturacion();
}
