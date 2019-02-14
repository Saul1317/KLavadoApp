package com.example.sauldelgado.klavadoapp.DatosPersonales.Model;

public class CorreoSQLite {

    int id_corre;
    String correo;

    public CorreoSQLite(int id_corre, String correo) {
        this.id_corre = id_corre;
        this.correo = correo;
    }

    public int getId_corre() {
        return id_corre;
    }

    public void setId_corre(int id_corre) {
        this.id_corre = id_corre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "CorreoSQLite{" +
                "id_corre=" + id_corre +
                ", correo='" + correo + '\'' +
                '}';
    }
}
