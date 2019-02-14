package com.example.sauldelgado.klavadoapp.Cita.View;

import java.util.ArrayList;

public interface CitaView{
    void CargarView();
    void LLenarSpinner(ArrayList<String> horariosList);
    void CargarUbicacion();
    void fechaError();
    void horarioError();
    void MostrarDatePickerDialog();
    void MostrarDialogAdvertencia();
    void RegresarActivity();
    void showDialogConectionError();
    void showProgressbar();
    void hideProgressbar();
    void BloquearBotonContinuar();
    void DesbloquearBotonContinuar();
}
