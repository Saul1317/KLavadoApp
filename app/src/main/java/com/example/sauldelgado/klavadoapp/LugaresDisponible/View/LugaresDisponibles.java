package com.example.sauldelgado.klavadoapp.LugaresDisponible.View;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.LugaresDisponible.Presenter.LugaresDisponiblesPresenter;
import com.example.sauldelgado.klavadoapp.LugaresDisponible.Presenter.LugaresDisponiblesPresenterImpl;
import com.example.sauldelgado.klavadoapp.Menu.View.MenuPrincipal;
import com.example.sauldelgado.klavadoapp.R;

import java.util.ArrayList;

public class LugaresDisponibles extends AppCompatActivity implements LugaresDisponiblesView, View.OnClickListener {

    LugaresDisponiblesPresenter lugaresDisponiblesPresenter;
    Spinner spinner_ciudad_disponible;
    Button button_ciudad_disponible_continuar;
    TextInputEditText txt_telefono;
    ConexionSQLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lugares_disponibles);
        conn = new ConexionSQLite(this, "bd_producto", null, SQLiteTablas.VERSION_BD);
        spinner_ciudad_disponible = (Spinner) findViewById(R.id.spinner_ciudad_disponible);
        txt_telefono = (TextInputEditText) findViewById(R.id.txt_telefono);
        button_ciudad_disponible_continuar = (Button) findViewById(R.id.button_ciudad_disponible_continuar);
        button_ciudad_disponible_continuar.setOnClickListener(this);
        lugaresDisponiblesPresenter = new LugaresDisponiblesPresenterImpl(this);
        lugaresDisponiblesPresenter.getCiudadesDisponibles();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_ciudad_disponible_continuar:
                lugaresDisponiblesPresenter.addTelefono(conn, txt_telefono.getText().toString());
                break;

            default:
                break;
        }
    }

    @Override
    public void llenarSpinner(ArrayList<String> lugaresDisponibles) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lugaresDisponibles);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ciudad_disponible.setAdapter(dataAdapter);
    }

    @Override
    public void addTelefonoSuccessful() {
        Intent prueba = new Intent(LugaresDisponibles.this, MenuPrincipal.class);
        startActivity(prueba);
    }

    @Override
    public void addTelefonoUnsuccessful() {
        Toast.makeText(this, "Ocurrió un error, intente de nuevo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void telefonoIncompleto() {
        txt_telefono.setError("Telefono Incompleto");
    }

    @Override
    public void telefonoVacio() {
        txt_telefono.setError("Campo Obligatorio");
    }
}
