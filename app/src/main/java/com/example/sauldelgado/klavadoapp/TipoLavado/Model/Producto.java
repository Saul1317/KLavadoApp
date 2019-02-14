package com.example.sauldelgado.klavadoapp.TipoLavado.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Producto {

    @SerializedName("id_producto")
    @Expose
    private String idProducto;
    @SerializedName("nombre_producto")
    @Expose
    private String nombreProducto;
    @SerializedName("descripcion_producto")
    @Expose
    private String descripcionProducto;
    @SerializedName("precio_producto")
    @Expose
    private String precioProducto;
    @SerializedName("vehiculos")
    @Expose
    private String vehiculos;
    @SerializedName("tipo_producto")
    @Expose
    private String tipoProducto;

    public Producto(String idProducto, String nombreProducto, String descripcionProducto, String precioProducto, String vehiculos, String tipoProducto) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcionProducto = descripcionProducto;
        this.precioProducto = precioProducto;
        this.vehiculos = vehiculos;
        this.tipoProducto = tipoProducto;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(String precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(String vehiculos) {
        this.vehiculos = vehiculos;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto='" + idProducto + '\'' +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", descripcionProducto='" + descripcionProducto + '\'' +
                ", precioProducto='" + precioProducto + '\'' +
                ", vehiculos='" + vehiculos + '\'' +
                ", tipoProducto='" + tipoProducto + '\'' +
                '}';
    }
}
