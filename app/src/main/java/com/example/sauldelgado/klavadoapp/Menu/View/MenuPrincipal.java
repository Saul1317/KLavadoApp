package com.example.sauldelgado.klavadoapp.Menu.View;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.Facturacion.View.FacturacionViewImpl;
import com.example.sauldelgado.klavadoapp.Menu.Presenter.MenuPresenteImpl;
import com.example.sauldelgado.klavadoapp.Menu.Presenter.MenuPresenter;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Registrar.View.Registrarse;
import com.example.sauldelgado.klavadoapp.Terminos.View.Terminos;
import com.example.sauldelgado.klavadoapp.TipoServicio.View.TipoServicio;

import java.util.ArrayList;

public class MenuPrincipal extends AppCompatActivity implements MenuView, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private Button boton_peticion_express;
    private Spinner spinner_ciudad_disponible;
    private LinearLayout txt_terminos_condiciones;
    private SwitchCompat switch_terminos_condiciones;
    private MenuPresenter menuPresenter;
    TextView txt_menu_advertencia;
    TextInputEditText txt_telefono,edt_nombre_usuario ;
    ConexionSQLite conn;

    ConstraintLayout constraintLayout_menu_principal;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            //w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_menu_lavado911);
        cargarViews();
    }

    @Override
    public void cargarViews() {

        conn = new ConexionSQLite(this, "bd_producto", null, SQLiteTablas.VERSION_BD);
        menuPresenter = new MenuPresenteImpl(this);

        constraintLayout_menu_principal = (ConstraintLayout) findViewById(R.id.constraintLayout_menu_principal);
        animationDrawable = (AnimationDrawable) constraintLayout_menu_principal.getBackground();
        animationDrawable.setEnterFadeDuration(7000);
        animationDrawable.setExitFadeDuration(7000);
        animationDrawable.start();

        txt_menu_advertencia = (TextView) findViewById(R.id.txt_menu_advertencia);

        edt_nombre_usuario = (TextInputEditText)  findViewById(R.id.edt_nombre_usuario);
        txt_telefono = (TextInputEditText)  findViewById(R.id.txt_telefono);

        boton_peticion_express = (Button) findViewById(R.id.button_peticion_express);
        boton_peticion_express.setOnClickListener(this);

        txt_terminos_condiciones = (LinearLayout) findViewById(R.id.txt_terminos_condiciones);
        txt_terminos_condiciones.setOnClickListener(this);

        spinner_ciudad_disponible = (Spinner) findViewById(R.id.spinner_ciudad_disponible);

        switch_terminos_condiciones = (SwitchCompat) findViewById(R.id.switch_terminos_condiciones);
        switch_terminos_condiciones.setOnCheckedChangeListener(this);
        switch_terminos_condiciones.setChecked(true);

        menuPresenter.verEstatusTerminos(switch_terminos_condiciones.isChecked());
        menuPresenter.getCiudadesDisponibles();
    }

    @Override
    public void abrirServicioExpress() {
        Intent intentKlavadoMenu = new Intent(MenuPrincipal.this,TipoServicio.class);
        startActivity(intentKlavadoMenu);
    }

    @Override
    public void abrirLogin() {
        Intent prueba = new Intent(MenuPrincipal.this, FacturacionViewImpl.class);
        startActivity(prueba);
    }

    @Override
    public void abrirRegistrar() {
        Intent intentRegistrarse = new Intent(MenuPrincipal.this, Registrarse.class);
        startActivity(intentRegistrarse);
    }

    @Override
    public void abrirTerminosCondiciones() {
        Intent intentRegistrarse = new Intent(MenuPrincipal.this, Terminos.class);
        startActivity(intentRegistrarse);
    }

    @Override
    public void bloquearBotones() {
        float alpha_button = (float) 0.6;

        boton_peticion_express.setEnabled(false);
        boton_peticion_express.setAlpha(alpha_button);

        txt_menu_advertencia.setVisibility(View.VISIBLE);
    }

    @Override
    public void desbloquearBotones() {
        float alpha_button = (float) 1;

        boton_peticion_express.setEnabled(true);
        boton_peticion_express.setAlpha(alpha_button);

        txt_menu_advertencia.setVisibility(View.INVISIBLE);
    }

    @Override
    public void llenarSpinner(ArrayList<String> lugaresDisponibles) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lugaresDisponibles);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ciudad_disponible.setAdapter(dataAdapter);
    }

    @Override
    public void NombreVacio() {
        edt_nombre_usuario.setError("Campo obligatorio");
    }

    @Override
    public void NumeroVacio() {
        txt_telefono.setError("Campo obligatorio");
    }

    @Override
    public void faltanNumeros() {
        txt_telefono.setError("Numero incompleto");
    }

    @Override
    public void GuardadoIncorrecto() {
        Toast.makeText(this, "Ocurrió un problema", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.txt_terminos_condiciones:
                menuPresenter.abrirTerminosCondiciones();
                break;

            case R.id.button_peticion_express:
                menuPresenter.addInformation(conn, edt_nombre_usuario.getText().toString(), txt_telefono.getText().toString());
                break;

            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        menuPresenter.verEstatusTerminos(switch_terminos_condiciones.isChecked());
    }
}
