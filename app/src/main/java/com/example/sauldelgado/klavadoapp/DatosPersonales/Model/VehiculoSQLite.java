package com.example.sauldelgado.klavadoapp.DatosPersonales.Model;

public class VehiculoSQLite {

    int id_vehiculo;
    String descripcion_vehiculo;

    public VehiculoSQLite(int id_vehiculo, String descripcion_vehiculo) {
        this.id_vehiculo = id_vehiculo;
        this.descripcion_vehiculo = descripcion_vehiculo;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getDescripcion_vehiculo() {
        return descripcion_vehiculo;
    }

    public void setDescripcion_vehiculo(String descripcion_vehiculo) {
        this.descripcion_vehiculo = descripcion_vehiculo;
    }

    @Override
    public String toString() {
        return "VehiculoSQLite{" +
                "id_vehiculo=" + id_vehiculo +
                ", descripcion_vehiculo='" + descripcion_vehiculo + '\'' +
                '}';
    }
}
