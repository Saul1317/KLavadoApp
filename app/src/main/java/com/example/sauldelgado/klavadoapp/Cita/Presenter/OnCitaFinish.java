package com.example.sauldelgado.klavadoapp.Cita.Presenter;

import java.util.ArrayList;

public interface OnCitaFinish {
    void MostrarHorariosDisponibles(ArrayList<String> horariosList);
    void showDialogConectionError();
    void addToCartSucessful();
    void fechaError();
    void horarioError();
}
