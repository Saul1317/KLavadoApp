package com.example.sauldelgado.klavadoapp.Extras.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Extras_Ofertas {

    @SerializedName("id_extras")
    @Expose
    private String idExtras;
    @SerializedName("nombre_extras")
    @Expose
    private String nombreExtras;
    @SerializedName("descripcion_extras")
    @Expose
    private String descripcionExtras;
    @SerializedName("precio_extras")
    @Expose
    private String precioExtras;
    @SerializedName("tipo_extra")
    @Expose
    private String tipoExtra;
    @SerializedName("advertencia")
    @Expose
    private String advertencia;
    @SerializedName("porcentaje_descuento")
    @Expose
    private String porcentajeDescuento;
    @SerializedName("descuento_disponible")
    @Expose
    private String descuentoDisponible;
    @SerializedName("vehiculos")
    @Expose
    private String vehiculos;

    public Extras_Ofertas(String idExtras, String nombreExtras, String descripcionExtras, String precioExtras, String tipoExtra, String advertencia, String porcentajeDescuento, String descuentoDisponible, String vehiculos) {
        this.idExtras = idExtras;
        this.nombreExtras = nombreExtras;
        this.descripcionExtras = descripcionExtras;
        this.precioExtras = precioExtras;
        this.tipoExtra = tipoExtra;
        this.advertencia = advertencia;
        this.porcentajeDescuento = porcentajeDescuento;
        this.descuentoDisponible = descuentoDisponible;
        this.vehiculos = vehiculos;
    }

    public String getIdExtras() {
        return idExtras;
    }

    public void setIdExtras(String idExtras) {
        this.idExtras = idExtras;
    }

    public String getNombreExtras() {
        return nombreExtras;
    }

    public void setNombreExtras(String nombreExtras) {
        this.nombreExtras = nombreExtras;
    }

    public String getDescripcionExtras() {
        return descripcionExtras;
    }

    public void setDescripcionExtras(String descripcionExtras) {
        this.descripcionExtras = descripcionExtras;
    }

    public String getPrecioExtras() {
        return precioExtras;
    }

    public void setPrecioExtras(String precioExtras) {
        this.precioExtras = precioExtras;
    }

    public String getTipoExtra() {
        return tipoExtra;
    }

    public void setTipoExtra(String tipoExtra) {
        this.tipoExtra = tipoExtra;
    }

    public String getAdvertencia() {
        return advertencia;
    }

    public void setAdvertencia(String advertencia) {
        this.advertencia = advertencia;
    }

    public String getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(String porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public String getDescuentoDisponible() {
        return descuentoDisponible;
    }

    public void setDescuentoDisponible(String descuentoDisponible) {
        this.descuentoDisponible = descuentoDisponible;
    }

    public String getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(String vehiculos) {
        this.vehiculos = vehiculos;
    }

    @Override
    public String toString() {
        return "Extras_Ofertas{" +
                "idExtras='" + idExtras + '\'' +
                ", nombreExtras='" + nombreExtras + '\'' +
                ", descripcionExtras='" + descripcionExtras + '\'' +
                ", precioExtras='" + precioExtras + '\'' +
                ", tipoExtra='" + tipoExtra + '\'' +
                ", advertencia='" + advertencia + '\'' +
                ", porcentajeDescuento='" + porcentajeDescuento + '\'' +
                ", descuentoDisponible='" + descuentoDisponible + '\'' +
                ", vehiculos='" + vehiculos + '\'' +
                '}';
    }
}
