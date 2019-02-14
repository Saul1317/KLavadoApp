package com.example.sauldelgado.klavadoapp.Data.Local.Sqlite;

public class Productos {

    Integer id_producto;
    String nombre_producto;
    Double precio_producto;
    String tipo_producto;

    public Productos(Integer id_producto, String nombre_producto, Double precio_producto, String tipo_producto) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio_producto = precio_producto;
        this.tipo_producto = tipo_producto;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public Double getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(Double precio_producto) {
        this.precio_producto = precio_producto;
    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "id_producto=" + id_producto +
                ", nombre_producto='" + nombre_producto + '\'' +
                ", precio_producto=" + precio_producto +
                ", tipo_producto='" + tipo_producto + '\'' +
                '}';
    }
}
