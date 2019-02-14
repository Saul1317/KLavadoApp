package com.example.sauldelgado.klavadoapp.Cita.Model;

public class CitaSQLite {

    int id_cita;
    String horarios;
    String fecha;

    public CitaSQLite(int id_cita, String horarios, String fecha) {
        this.id_cita = id_cita;
        this.horarios = horarios;
        this.fecha = fecha;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "CitaSQLite{" +
                "id_cita=" + id_cita +
                ", horarios='" + horarios + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
