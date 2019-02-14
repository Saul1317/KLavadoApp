package com.example.sauldelgado.klavadoapp.Cita.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Horarios {

    @SerializedName("id_horario")
    @Expose
    private String idHorario;
    @SerializedName("horario_disponible")
    @Expose
    private String horarioDisponible;
    @SerializedName("citas_apartadas")
    @Expose
    private Object citasApartadas;

    public Horarios(String idHorario, String horarioDisponible, Object citasApartadas) {
        this.idHorario = idHorario;
        this.horarioDisponible = horarioDisponible;
        this.citasApartadas = citasApartadas;
    }

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }

    public String getHorarioDisponible() {
        return horarioDisponible;
    }

    public void setHorarioDisponible(String horarioDisponible) {
        this.horarioDisponible = horarioDisponible;
    }

    public Object getCitasApartadas() {
        return citasApartadas;
    }

    public void setCitasApartadas(Object citasApartadas) {
        this.citasApartadas = citasApartadas;
    }
}
