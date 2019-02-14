package com.example.sauldelgado.klavadoapp.LugaresDisponible.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CiudadesDisponibles {

    @SerializedName("id_ciudad")
    @Expose
    private String idCiudad;
    @SerializedName("ciudad")
    @Expose
    private String ciudad;
    @SerializedName("disponible")
    @Expose
    private String disponible;

    public CiudadesDisponibles(String idCiudad, String ciudad, String disponible) {
        this.idCiudad = idCiudad;
        this.ciudad = ciudad;
        this.disponible = disponible;
    }

    public String getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "CiudadesDisponibles{" +
                "idCiudad='" + idCiudad + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", disponible='" + disponible + '\'' +
                '}';
    }
}
