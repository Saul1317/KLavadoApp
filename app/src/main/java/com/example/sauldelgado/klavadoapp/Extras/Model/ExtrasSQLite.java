package com.example.sauldelgado.klavadoapp.Extras.Model;

public class ExtrasSQLite {
    Integer id_extra;
    String nombre_extra;
    Double precio_extra;
    String descripcion_extra;

    public ExtrasSQLite(Integer id_extra, String nombre_extra, Double precio_extra, String descripcion_extra) {
        this.id_extra = id_extra;
        this.nombre_extra = nombre_extra;
        this.precio_extra = precio_extra;
        this.descripcion_extra = descripcion_extra;
    }

    public Integer getId_extra() {
        return id_extra;
    }

    public void setId_extra(Integer id_extra) {
        this.id_extra = id_extra;
    }

    public String getNombre_extra() {
        return nombre_extra;
    }

    public void setNombre_extra(String nombre_extra) {
        this.nombre_extra = nombre_extra;
    }

    public Double getPrecio_extra() {
        return precio_extra;
    }

    public void setPrecio_extra(Double precio_extra) {
        this.precio_extra = precio_extra;
    }

    public String getDescripcion_extra() {
        return descripcion_extra;
    }

    public void setDescripcion_extra(String descripcion_extra) {
        this.descripcion_extra = descripcion_extra;
    }

    @Override
    public String toString() {
        return "ExtrasSQLite{" +
                "id_extra=" + id_extra +
                ", nombre_extra='" + nombre_extra + '\'' +
                ", precio_extra=" + precio_extra +
                ", descripcion_extra='" + descripcion_extra + '\'' +
                '}';
    }
}
