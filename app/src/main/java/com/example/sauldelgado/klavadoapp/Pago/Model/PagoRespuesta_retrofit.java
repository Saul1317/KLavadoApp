package com.example.sauldelgado.klavadoapp.Pago.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PagoRespuesta_retrofit {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;

    public PagoRespuesta_retrofit(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PagoRespuesta_retrofit{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
