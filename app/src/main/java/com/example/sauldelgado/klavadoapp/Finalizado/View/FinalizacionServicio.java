package com.example.sauldelgado.klavadoapp.Finalizado.View;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.Extras.Model.ExtrasSQLite;
import com.example.sauldelgado.klavadoapp.Extras.View.Extras;
import com.example.sauldelgado.klavadoapp.Finalizado.Adapter.AdapterRecyclerViewExtrasSeleccionados;
import com.example.sauldelgado.klavadoapp.Finalizado.Adapter.onItemClickListenerExtrasSeleccionado;
import com.example.sauldelgado.klavadoapp.Finalizado.Presenter.FinalizacionServicioPresenter;
import com.example.sauldelgado.klavadoapp.Finalizado.Presenter.FinalizacionServicioPresenterImpl;
import com.example.sauldelgado.klavadoapp.Menu.View.MenuPrincipal;
import com.example.sauldelgado.klavadoapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class FinalizacionServicio extends AppCompatActivity implements FinalizacionServicioView, View.OnClickListener {

    FinalizacionServicioPresenter finalizacionServicioPresenter;
    TextView fecha_actual, hora_actual, txt_nombre_usuario, txt_telefono_usuario, txt_fecha_servicio_usuario,
            txt_direccion_usuario, txt_servicio_usuario, txt_precio_servicio_usuario, txt_precio_total;
    RecyclerView recycler_total_extras;
    Button btn_finalizar;
    private ConexionSQLite conn;
    SimpleDateFormat formatoVista = new SimpleDateFormat("dd-MM-yyyy");
    public final Calendar c = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizacion_servicio);
        LoadViews();
    }

    @Override
    public void LoadViews() {
        conn = new ConexionSQLite(this, "bd_producto", null, SQLiteTablas.VERSION_BD);

        fecha_actual = (TextView) findViewById(R.id.fecha_actual);
        hora_actual = (TextView) findViewById(R.id.hora_actual);

        txt_nombre_usuario = (TextView) findViewById(R.id.txt_nombre_usuario);
        txt_telefono_usuario = (TextView) findViewById(R.id.txt_telefono_usuario);
        txt_fecha_servicio_usuario = (TextView) findViewById(R.id.txt_fecha_servicio_usuario);
        txt_direccion_usuario = (TextView) findViewById(R.id.txt_direccion_usuario);
        txt_servicio_usuario = (TextView) findViewById(R.id.txt_servicio_usuario);
        txt_precio_servicio_usuario = (TextView) findViewById(R.id.txt_precio_servicio_usuario);
        txt_precio_total = (TextView) findViewById(R.id.txt_precio_total);

        btn_finalizar = (Button) findViewById(R.id.btn_finalizar);
        btn_finalizar.setOnClickListener(this);

        recycler_total_extras = (RecyclerView) findViewById(R.id.recycler_total_extras);
        LinearLayoutManager linearLayoutManagerExtras = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_total_extras.setLayoutManager(linearLayoutManagerExtras);

        finalizacionServicioPresenter = new FinalizacionServicioPresenterImpl(this);
        finalizacionServicioPresenter.CargarDatosDelServicio(conn, "19-12-2018", "11:00AM");

        CargarDialog();
    }

    private void CargarDialog() {
        AlertDialog.Builder alertConfirmacion = new AlertDialog.Builder(this);
        LayoutInflater inflaterConfirmacion = this.getLayoutInflater();
        View dialoglayoutConfirmacion = inflaterConfirmacion.inflate(R.layout.dialog_seguridad, null);
        alertConfirmacion.setCancelable(false);
        alertConfirmacion.setView(dialoglayoutConfirmacion);
        final AlertDialog alertDialogConfirmacion = alertConfirmacion.show();

        Button botonAceptar_dialog_confirmacion_aceptar = (Button) dialoglayoutConfirmacion.findViewById(R.id.diaglo_seguridad_button);
        botonAceptar_dialog_confirmacion_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogConfirmacion.dismiss();
            }
        });
    }

    @Override
    public void setInformacionServicio(String fecha, String hora, String nombre, String telefono, String fecha_servicio, String direccion, String servicio, String precio, String precio_total) {
        fecha_actual.setText(fecha);
        hora_actual.setText(hora);
        txt_nombre_usuario.setText(nombre);
        txt_telefono_usuario.setText(telefono);
        txt_fecha_servicio_usuario.setText(fecha_servicio);
        txt_direccion_usuario.setText(direccion);
        txt_servicio_usuario.setText(servicio);
        txt_precio_servicio_usuario.setText(precio);
        txt_precio_total.setText(precio_total);
    }
    @Override
    public void setExtrasServicio(List<ExtrasSQLite> extrasList) {
        AdapterRecyclerViewExtrasSeleccionados extras = new AdapterRecyclerViewExtrasSeleccionados(R.layout.cardview_extras_seleccionados, extrasList,
                FinalizacionServicio.this, getApplicationContext(), null);
        recycler_total_extras.setAdapter(extras);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_finalizar:
                Intent intentKlavadoServicio = new Intent(FinalizacionServicio.this, MenuPrincipal.class);
                intentKlavadoServicio.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intentKlavadoServicio);
                break;
            default:
                break;
        }
    }
}
