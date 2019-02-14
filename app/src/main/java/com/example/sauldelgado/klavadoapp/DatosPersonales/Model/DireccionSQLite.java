package com.example.sauldelgado.klavadoapp.DatosPersonales.Model;

public class DireccionSQLite {

    int id_direccion;
    String direccion_usuario;
    String latitud_usuario;
    String longitud_usuario;

    public DireccionSQLite(int id_direccion, String direccion_usuario, String latitud_usuario, String longitud_usuario) {
        this.id_direccion = id_direccion;
        this.direccion_usuario = direccion_usuario;
        this.latitud_usuario = latitud_usuario;
        this.longitud_usuario = longitud_usuario;
    }

    public int getId_direccion() {
        return id_direccion;
    }

    public void setId_direccion(int id_direccion) {
        this.id_direccion = id_direccion;
    }

    public String getDireccion_usuario() {
        return direccion_usuario;
    }

    public void setDireccion_usuario(String direccion_usuario) {
        this.direccion_usuario = direccion_usuario;
    }

    public String getLatitud_usuario() {
        return latitud_usuario;
    }

    public void setLatitud_usuario(String latitud_usuario) {
        this.latitud_usuario = latitud_usuario;
    }

    public String getLongitud_usuario() {
        return longitud_usuario;
    }

    public void setLongitud_usuario(String longitud_usuario) {
        this.longitud_usuario = longitud_usuario;
    }

    @Override
    public String toString() {
        return "DireccionSQLite{" +
                "id_direccion=" + id_direccion +
                ", direccion_usuario='" + direccion_usuario + '\'' +
                ", latitud_usuario='" + latitud_usuario + '\'' +
                ", longitud_usuario='" + longitud_usuario + '\'' +
                '}';
    }
}
