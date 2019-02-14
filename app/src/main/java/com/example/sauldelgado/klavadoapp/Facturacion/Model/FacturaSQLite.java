package com.example.sauldelgado.klavadoapp.Facturacion.Model;

public class FacturaSQLite {

    int id_facturacion;
    String RFC;
    String razon_social;
    String tipo_pago;

    public FacturaSQLite(int id_facturacion, String RFC, String razon_social, String tipo_pago) {
        this.id_facturacion = id_facturacion;
        this.RFC = RFC;
        this.razon_social = razon_social;
        this.tipo_pago = tipo_pago;
    }

    public int getId_facturacion() {
        return id_facturacion;
    }

    public void setId_facturacion(int id_facturacion) {
        this.id_facturacion = id_facturacion;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    @Override
    public String toString() {
        return "FacturaSQLite{" +
                "id_facturacion=" + id_facturacion +
                ", RFC='" + RFC + '\'' +
                ", razon_social='" + razon_social + '\'' +
                ", tipo_pago='" + tipo_pago + '\'' +
                '}';
    }
}
