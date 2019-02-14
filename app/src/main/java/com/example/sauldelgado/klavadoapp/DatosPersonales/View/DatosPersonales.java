package com.example.sauldelgado.klavadoapp.DatosPersonales.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Data.Local.SharedPreference.MetodosSharedPreference;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Adapter.CustomSpinnerAdapter;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Presenter.DatosPersonalesPresenter;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Presenter.DatosPersonalesPresenterImpl;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Pago.View.Activity.Pago;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Color;

import java.util.ArrayList;
import java.util.List;

public class DatosPersonales extends AppCompatActivity implements DatosPersonalesView, View.OnClickListener {

    private Button button_datos_personales_continuar;
    private DatosPersonalesPresenter datosPersonalesPresenter;
    private TextInputEditText edt_nombre_usuario, edt_correo_usuario, edt_comentario_usuario, edt_modelo_vehiculo;
    private ImageView btn_back_datos_personales;
    private ConexionSQLite conn;
    private SharedPreferences sharedPreferences;
    private Spinner spinner_color;
    List<Color> colorList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_personales_klavado);
        CargarViews();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.button_datos_personales_continuar:
                datosPersonalesPresenter.addDatosPersonales(conn,
                        edt_nombre_usuario.getText().toString(),
                        edt_correo_usuario.getText().toString(),
                        MetodosSharedPreference.ObtenerTipoVehiculoPref(sharedPreferences),
                        edt_modelo_vehiculo.getText().toString(),
                        regresarColor(spinner_color.getSelectedItemPosition()),
                        edt_comentario_usuario.getText().toString());
                break;

            case R.id.btn_back_datos_personales:
                datosPersonalesPresenter.RegresarActivity();
                break;

            default:
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private String regresarColor(int selectedItemPosition) {
        return colorList.get(selectedItemPosition).getNombre_color();
    }

    @Override
    public void CargarViews() {
        conn = new ConexionSQLite(this, "bd_producto", null, SQLiteTablas.VERSION_BD);
        sharedPreferences = getSharedPreferences("klavado", Context.MODE_PRIVATE);
        button_datos_personales_continuar = (Button) findViewById(R.id.button_datos_personales_continuar);
        button_datos_personales_continuar.setOnClickListener(this);
        datosPersonalesPresenter = new DatosPersonalesPresenterImpl(this);
        btn_back_datos_personales = (ImageView) findViewById(R.id.btn_back_datos_personales);
        btn_back_datos_personales.setOnClickListener(this);
        edt_nombre_usuario = (TextInputEditText) findViewById(R.id.edt_nombre_usuario);
        edt_correo_usuario = (TextInputEditText) findViewById(R.id.edt_correo_usuario);
        edt_comentario_usuario = (TextInputEditText) findViewById(R.id.edt_comentario_usuario);
        edt_modelo_vehiculo = (TextInputEditText) findViewById(R.id.edt_modelo_vehiculo);
        spinner_color = (Spinner) findViewById(R.id.spinner_color);

        Color rojo = new Color();
        rojo.setId_color(1);
        rojo.setNombre_color("Rojo");
        rojo.setHexadecimal("#f44336");

        Color azul = new Color();
        azul.setId_color(2);
        azul.setNombre_color("Azul");
        azul.setHexadecimal("#3f51b5");

        Color verde = new Color();
        verde.setId_color(3);
        verde.setNombre_color("Verde");
        verde.setHexadecimal("#4caf50");

        Color amarillo = new Color();
        amarillo.setId_color(4);
        amarillo.setNombre_color("Amarillo");
        amarillo.setHexadecimal("#ffeb3b");

        Color cafe = new Color();
        cafe.setId_color(5);
        cafe.setNombre_color("Cafe");
        cafe.setHexadecimal("#795548");

        Color gris = new Color();
        gris.setId_color(6);
        gris.setNombre_color("Gris");
        gris.setHexadecimal("#bdbdbd");

        Color blanco = new Color();
        blanco.setId_color(7);
        blanco.setNombre_color("Blanco");
        blanco.setHexadecimal("#fafafa");

        Color negro = new Color();
        negro.setId_color(8);
        negro.setNombre_color("Negro");
        negro.setHexadecimal("#212121");

        colorList = new ArrayList<Color>();
        colorList.add(rojo);
        colorList.add(azul);
        colorList.add(verde);
        colorList.add(amarillo);
        colorList.add(cafe);
        colorList.add(gris);
        colorList.add(blanco);
        colorList.add(negro);

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this,
                R.layout.spinner_colores_vehiculo, colorList);
        spinner_color.setAdapter(adapter);
    }

    @Override
    public void abrirPago() {
        Intent intentKlavadoPagos = new Intent(DatosPersonales.this,Pago.class);
        startActivity(intentKlavadoPagos);
    }

    @Override
    public void nombreError() {
        edt_nombre_usuario.setError("Campo obligatorio");
    }

    @Override
    public void correoError() {
        edt_correo_usuario.setError("Campo obligatorio");
    }

    @Override
    public void comentarioError() {
        edt_comentario_usuario.setError("Campo obligatorio");
    }

    @Override
    public void RegresarActivity() {
        onBackPressed();
    }

}
