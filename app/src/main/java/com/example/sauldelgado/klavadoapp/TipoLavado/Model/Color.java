package com.example.sauldelgado.klavadoapp.TipoLavado.Model;

public class Color {

    int id_color;
    String nombre_color;
    String hexadecimal;

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public String getNombre_color() {
        return nombre_color;
    }

    public void setNombre_color(String nombre_color) {
        this.nombre_color = nombre_color;
    }

    public String getHexadecimal() {
        return hexadecimal;
    }

    public void setHexadecimal(String hexadecimal) {
        this.hexadecimal = hexadecimal;
    }

    @Override
    public String toString() {
        return "Color{" +
                "id_color=" + id_color +
                ", nombre_color='" + nombre_color + '\'' +
                ", hexadecimal='" + hexadecimal + '\'' +
                '}';
    }
}
