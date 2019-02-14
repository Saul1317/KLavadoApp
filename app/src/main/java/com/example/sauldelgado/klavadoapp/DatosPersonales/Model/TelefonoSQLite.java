package com.example.sauldelgado.klavadoapp.DatosPersonales.Model;

public class TelefonoSQLite {
    int id_telefono;
    String telefono;

    public TelefonoSQLite(int id_telefono, String telefono) {
        this.id_telefono = id_telefono;
        this.telefono = telefono;
    }

    public int getId_telefono() {
        return id_telefono;
    }

    public void setId_telefono(int id_telefono) {
        this.id_telefono = id_telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "TelefonoSQLite{" +
                "id_telefono=" + id_telefono +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
