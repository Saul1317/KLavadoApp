package com.example.sauldelgado.klavadoapp.Cita.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Cita.Presenter.CitaPresenter;
import com.example.sauldelgado.klavadoapp.Cita.Presenter.CitaPresenterImpl;
import com.example.sauldelgado.klavadoapp.CostosLavados.View.CostoLavado;
import com.example.sauldelgado.klavadoapp.DatosPersonales.View.DatosPersonales;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.Services.View.Servicio;
import com.example.sauldelgado.klavadoapp.Ubicacion.View.Ubicacion;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Cita extends AppCompatActivity implements CitaView, View.OnClickListener {

    private TextInputEditText edt_fecha, edt_comentario_usuario;
    private Button button_programacion_continuar;
    private Spinner spinner_horario_disponible;
    private ImageView btn_back_programacion;
    private ProgressBar progressbar_programacion;

    private static final String CERO = "0";
    private static final String BARRA = "/";
    static SimpleDateFormat formatoVista = new SimpleDateFormat("dd-MM-yyyy");
    static SimpleDateFormat formatoBD = new SimpleDateFormat("yyyy-MM-dd");
    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);
    private ConexionSQLite conn;
    CitaPresenter citaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programacion_klavado);
        CargarView();
    }

    @Override
    public void CargarView() {
        citaPresenter = new CitaPresenterImpl(this);
        conn = new ConexionSQLite(this, "bd_producto", null, SQLiteTablas.VERSION_BD);
        progressbar_programacion = (ProgressBar) findViewById(R.id.progressbar_programacion);

        edt_fecha = (TextInputEditText) findViewById(R.id.edt_fecha);
        edt_fecha.setOnClickListener(this);
        edt_fecha.setText(formatoBD.format(c.getTime()));

        edt_comentario_usuario = (TextInputEditText) findViewById(R.id.edt_comentario_usuario);

        button_programacion_continuar = (Button) findViewById(R.id.button_programacion_continuar);
        button_programacion_continuar.setOnClickListener(this);

        spinner_horario_disponible = (Spinner) findViewById(R.id.spinner_horario_disponible);

        btn_back_programacion = (ImageView) findViewById(R.id.btn_back_programacion);
        btn_back_programacion.setOnClickListener(this);

        citaPresenter.ConsultarHorariosDisponibles(formatoBD.format(c.getTime()));
    }

    @Override
    public void CargarUbicacion() {
        Intent intentKlavadoUbicacion = new Intent(Cita.this, Ubicacion.class);
        startActivity(intentKlavadoUbicacion);
    }

    @Override
    public void LLenarSpinner(ArrayList<String> horariosList) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, horariosList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_horario_disponible.setAdapter(dataAdapter);
    }

    @Override
    public void fechaError() {
        edt_fecha.setError("Campo obligatorio");
    }

    @Override
    public void horarioError() {
        Toast.makeText(this, "ocupamos un horario para atenderte", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void MostrarDatePickerDialog() {
        DatePickerDialog recogerFecha= new DatePickerDialog(this ,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
                String fecha_cita =  year + BARRA + mesFormateado + BARRA + diaFormateado;
                edt_fecha.setText(fecha_cita);
                citaPresenter.ConsultarHorariosDisponibles(year + BARRA + mesFormateado + BARRA + diaFormateado);
            }
        },anio, mes, dia);

        recogerFecha.getDatePicker().setMinDate(System.currentTimeMillis());
        recogerFecha.show();
    }

    @Override
    public void MostrarDialogAdvertencia() {
        AlertDialog.Builder alertConfirmacion = new AlertDialog.Builder(this);
        LayoutInflater inflaterConfirmacion = this.getLayoutInflater();
        View dialoglayoutConfirmacion = inflaterConfirmacion.inflate(R.layout.dialog_advertencia, null);
        alertConfirmacion.setCancelable(false);
        alertConfirmacion.setView(dialoglayoutConfirmacion);
        final AlertDialog alertDialogConfirmacion = alertConfirmacion.show();

        TextView txtTitutlo_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_titulo);
        TextView txtDescripcion_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_contenido);

        Button botonAceptar_dialog_confirmacion_aceptar = (Button) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_button);
        botonAceptar_dialog_confirmacion_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogConfirmacion.dismiss();
            }
        });
    }

    @Override
    public void RegresarActivity() {
        onBackPressed();
    }

    @Override
    public void showDialogConectionError() {
        AlertDialog.Builder alertConfirmacion = new AlertDialog.Builder(this);
        LayoutInflater inflaterConfirmacion = this.getLayoutInflater();
        View dialoglayoutConfirmacion = inflaterConfirmacion.inflate(R.layout.dialog_conexion_error, null);
        alertConfirmacion.setCancelable(false);
        alertConfirmacion.setView(dialoglayoutConfirmacion);
        final AlertDialog alertDialogConfirmacion = alertConfirmacion.show();

        TextView txtTitutlo_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_titulo);
        TextView txtDescripcion_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_contenido);

        Button botonAceptar_dialog_confirmacion_aceptar = (Button) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_button);
        botonAceptar_dialog_confirmacion_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKlavadoServicio = new Intent(Cita.this, CostoLavado.class);
                intentKlavadoServicio.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentKlavadoServicio);
                alertDialogConfirmacion.dismiss();
            }
        });
    }

    @Override
    public void showProgressbar() {
        progressbar_programacion.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressbar_programacion.setVisibility(View.GONE);
    }

    @Override
    public void BloquearBotonContinuar() {
        button_programacion_continuar.setClickable(false);
    }

    @Override
    public void DesbloquearBotonContinuar() {
        button_programacion_continuar.setClickable(true);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.edt_fecha:
                MostrarDatePickerDialog();
                break;

            case R.id.spinner_horario_disponible:
                break;

            case R.id.button_programacion_continuar:
                citaPresenter.agregarDatosPersonales(conn, edt_fecha.getText().toString(), spinner_horario_disponible.getSelectedItem().toString(), edt_comentario_usuario.getText().toString());
                break;

            case R.id.btn_back_programacion:
                citaPresenter.RegresarActivity();
                break;

            default:
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
