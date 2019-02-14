package com.example.sauldelgado.klavadoapp.Services.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Data.Local.SharedPreference.MetodosSharedPreference;
import com.example.sauldelgado.klavadoapp.Menu.View.MenuPrincipal;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.Services.Adapter.AdapterDetallesServicio;
import com.example.sauldelgado.klavadoapp.Services.Adapter.AdapterDetallesServicioHidrolavado;
import com.example.sauldelgado.klavadoapp.Services.Adapter.onItemClickListener;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;
import com.example.sauldelgado.klavadoapp.Services.Presenter.ServicioPresenter;
import com.example.sauldelgado.klavadoapp.Services.Presenter.ServicioPresenterImpl;
import com.example.sauldelgado.klavadoapp.Extras.View.Extras;

import java.util.List;

public class Servicio extends AppCompatActivity implements ServicioView, View.OnClickListener {

    public RecyclerView recyclerview_servicio, recyclerview_servicio_hidrolavado;

    public ServicioPresenter servicioPresenter;
    private ConexionSQLite conn;
    private SharedPreferences sharedPreferences;
    private ProgressBar progressbar_servicio;
    private TextView titulo1_servicio, titulo2_servicio;
    Animation tarjeta_animation_izq, tarjeta_animation_der;
    ImageView btn_back_servicio;
    AlertDialog alertDialogConfirmacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klavado_servicio);
        LoadViews();
    }

    @Override
    public void LoadViews() {
        servicioPresenter = new ServicioPresenterImpl(this);
        conn = new ConexionSQLite(this, "bd_producto", null, SQLiteTablas.VERSION_BD);
        sharedPreferences = getSharedPreferences("klavado", Context.MODE_PRIVATE);

        titulo1_servicio = (TextView) findViewById(R.id.titulo1_servicio);
        titulo2_servicio = (TextView) findViewById(R.id.titulo2_servicio);

        recyclerview_servicio = (RecyclerView) findViewById(R.id.recyclerview_servicio);
        recyclerview_servicio.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerview_servicio.setVisibility(View.GONE);

        recyclerview_servicio_hidrolavado = (RecyclerView) findViewById(R.id.recyclerview_servicio_hidrolavado);
        recyclerview_servicio_hidrolavado.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerview_servicio_hidrolavado.setVisibility(View.GONE);

        btn_back_servicio = (ImageView) findViewById(R.id.btn_back_servicio);
        btn_back_servicio.setOnClickListener(this);

        progressbar_servicio = (ProgressBar) findViewById(R.id.progressbar_servicio);
        servicioPresenter.getSeriviciosDisponibles(MetodosSharedPreference.ObtenerTipoVehiculoPref(sharedPreferences));
        //servicioPresenter.CargarAnimacionTitulo();
    }

    @Override
    public void CargarTipoServicio() {
        onBackPressed();
    }

    @Override
    public void showServices() {
        recyclerview_servicio.setVisibility(View.VISIBLE);
        recyclerview_servicio_hidrolavado.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideServices() {
        recyclerview_servicio.setVisibility(View.GONE);
        recyclerview_servicio_hidrolavado.setVisibility(View.GONE);
    }

    @Override
    public void ServiceDescriptionEcologico(List<Producto> servicios) {
        AdapterDetallesServicio vehiculo = new AdapterDetallesServicio(R.layout.cardview_servicios, servicios, Servicio.this, getApplicationContext(), new onItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position, Producto producto_selected) {
                servicioPresenter.addProductToCart(conn, producto_selected, sharedPreferences);
            }
        });
        recyclerview_servicio.setAdapter(vehiculo);
    }

    @Override
    public void ServiceDescriptionHidrolavado(List<Producto> servicios) {
        AdapterDetallesServicioHidrolavado vehiculo = new AdapterDetallesServicioHidrolavado(R.layout.cardview_servicio_hidrolavado, servicios, Servicio.this, getApplicationContext(), new onItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position, Producto producto_selected) {
                servicioPresenter.addProductToCart(conn, producto_selected, sharedPreferences);
            }
        });
        recyclerview_servicio_hidrolavado.setAdapter(vehiculo);
    }

    @Override
    public void getErrorDescripcion() {
        AlertDialog.Builder alertConfirmacion = new AlertDialog.Builder(this);
        LayoutInflater inflaterConfirmacion = this.getLayoutInflater();
        View dialoglayoutConfirmacion = inflaterConfirmacion.inflate(R.layout.dialog_conexion_error, null);
        alertConfirmacion.setCancelable(false);
        alertConfirmacion.setView(dialoglayoutConfirmacion);
        alertDialogConfirmacion = alertConfirmacion.show();
        TextView txtTitutlo_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_titulo);
        TextView txtDescripcion_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_contenido);
        Button botonAceptar_dialog_confirmacion_aceptar = (Button) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_button);

        botonAceptar_dialog_confirmacion_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                servicioPresenter.RegresarMainActivity();
                alertDialogConfirmacion.dismiss();
            }
        });
    }

    @Override
    public void openExtras() {
        Intent intentKlavadoMenu = new Intent(Servicio.this, Extras.class);
        startActivity(intentKlavadoMenu);
    }

    @Override
    public void showProgressBar() {
        progressbar_servicio.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressbar_servicio.setVisibility(View.GONE);
    }

    @Override
    public void RegresarMainActivity() {
        Intent intentKlavadoServicio = new Intent(Servicio.this, MenuPrincipal.class);
        intentKlavadoServicio.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentKlavadoServicio);
    }

    @Override
    public void AnimacionTitulo1() {
        tarjeta_animation_der = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.tipo_servicio_deslizar_der);
        titulo1_servicio.setAnimation(tarjeta_animation_der);
    }

    @Override
    public void AnimacionTitulo2() {
        tarjeta_animation_izq = AnimationUtils.loadAnimation(this,R.anim.tipo_servicio_deslizar_izq);
        titulo2_servicio.setAnimation(tarjeta_animation_izq);
    }

    @Override
    public void ErrorSQL() {
        Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_back_servicio:
                servicioPresenter.CargarTipoServicio();
                break;
            default:
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if ( alertDialogConfirmacion!=null && alertDialogConfirmacion.isShowing() ){
            alertDialogConfirmacion.cancel();
        }
    }
}
