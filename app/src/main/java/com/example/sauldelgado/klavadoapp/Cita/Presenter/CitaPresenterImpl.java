package com.example.sauldelgado.klavadoapp.Cita.Presenter;

import com.example.sauldelgado.klavadoapp.Cita.Interactor.CitaInteractor;
import com.example.sauldelgado.klavadoapp.Cita.Interactor.CitaInteratorImpl;
import com.example.sauldelgado.klavadoapp.Cita.View.Cita;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;

import java.util.ArrayList;

public class CitaPresenterImpl implements CitaPresenter, OnCitaFinish{

    private Cita citaView;
    private CitaInteractor citaInteractor;

    public CitaPresenterImpl(Cita citaView) {
        this.citaView = citaView;
        citaInteractor = new CitaInteratorImpl();
    }

    @Override
    public void ConsultarHorariosDisponibles(String fecha) {
        citaInteractor.ConsultarHorariosDisponibles(fecha,this);
        citaView.showProgressbar();
        citaView.BloquearBotonContinuar();
    }

    @Override
    public void agregarDatosPersonales(ConexionSQLite conn, String fecha, String horario, String descripcion) {
        citaInteractor.agregarInformacion(conn, fecha, horario, descripcion, this);
    }

    @Override
    public void MostrarDatePickerDialog() {
        citaView.MostrarDatePickerDialog();
    }

    @Override
    public void RegresarActivity() {
        citaView.RegresarActivity();
    }

    @Override
    public void MostrarHorariosDisponibles(ArrayList<String> horariosList) {
        citaView.hideProgressbar();
        citaView.LLenarSpinner(horariosList);
        citaView.DesbloquearBotonContinuar();
    }

    @Override
    public void showDialogConectionError() {
        citaView.hideProgressbar();
        citaView.showDialogConectionError();
        citaView.DesbloquearBotonContinuar();
    }

    @Override
    public void addToCartSucessful() {
        citaView.CargarUbicacion();
    }

    @Override
    public void fechaError() {
        citaView.fechaError();
    }

    @Override
    public void horarioError() {
        citaView.MostrarDialogAdvertencia();
    }
}
