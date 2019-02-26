package com.example.sauldelgado.klavadoapp.Extras.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Extras.Model.Producto_Extras;
import com.example.sauldelgado.klavadoapp.Extras.Presenter.ExtrasPresenter;
import com.example.sauldelgado.klavadoapp.Extras.Presenter.ExtrasPresenterImpl;
import com.example.sauldelgado.klavadoapp.Extras.adapter.AdapterRecyclerViewExtras;
import com.example.sauldelgado.klavadoapp.Extras.adapter.onItemClickListenerExtras;
import com.example.sauldelgado.klavadoapp.Menu.View.MenuPrincipal;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Cita.View.Cita;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.Data.Local.SharedPreference.MetodosSharedPreference;

import java.util.List;

public class Extras extends AppCompatActivity implements ExtrasView, View.OnClickListener {

    TextView txt_total_price, txt_precio_original, txt_nombre_cardview_oferta_semana, txt_precio_final_cardview_oferta_semana, txt_porcentaje_descuento_cardview_oferta_semana;
    CheckBox checkbox_cardview_oferta_semana;
    //ImageView btn_back_extras;
    CardView cardview_oferta_semanal;
    Animation tarjeta_oferta_semanal_animation, tarjeta_animation_izq, tarjeta_animation_der;
    Button button_extras_continuar;
    RecyclerView recycler_extras;
    SharedPreferences sharedPreferences;
    private ExtrasPresenter extrasPresenter;
    private ProgressBar progressbar_extra;
    private ConexionSQLite conn;


    ConstraintLayout contraintlayout_extra;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extras_klavado);
        LoadView();
    }

    @Override
    public void LoadView() {

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            MenuPrincipal.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }

        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            MenuPrincipal.setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        conn = new ConexionSQLite(this, "bd_producto", null, SQLiteTablas.VERSION_BD);
        sharedPreferences = getSharedPreferences("klavado", Context.MODE_PRIVATE);
        extrasPresenter = new ExtrasPresenterImpl(this);

        contraintlayout_extra = (ConstraintLayout) findViewById(R.id.contraintlayout_extra);
        animationDrawable = (AnimationDrawable) contraintlayout_extra.getBackground();
        animationDrawable.setEnterFadeDuration(7000);
        animationDrawable.setExitFadeDuration(7000);
        animationDrawable.start();

        recycler_extras = (RecyclerView) findViewById(R.id.recycler_extras);
        LinearLayoutManager linearLayoutManagerExtras = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recycler_extras.setLayoutManager(linearLayoutManagerExtras);

        progressbar_extra = (ProgressBar) findViewById(R.id.progressbar_extra);

        txt_total_price = (TextView) findViewById(R.id.txt_total_price);
        button_extras_continuar = (Button) findViewById(R.id.button_extras_continuar);
        button_extras_continuar.setOnClickListener(this);

        txt_nombre_cardview_oferta_semana = (TextView) findViewById(R.id.txt_nombre_cardview_oferta_semana);
        txt_precio_final_cardview_oferta_semana = (TextView) findViewById(R.id.txt_precio_final_cardview_oferta_semana);

        txt_precio_original = (TextView) findViewById(R.id.txt_precio_original_cardview_oferta_semana);
        txt_precio_original.setPaintFlags(txt_precio_original.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        checkbox_cardview_oferta_semana = (CheckBox) findViewById(R.id.checkbox_cardview_oferta_semana);
        checkbox_cardview_oferta_semana.setOnClickListener(this);

        //btn_back_extras = (ImageView) findViewById(R.id.btn_back_extras);
        //btn_back_extras.setOnClickListener(this);

        cardview_oferta_semanal = (CardView) findViewById(R.id.cardview_oferta_semanal);

        tarjeta_oferta_semanal_animation = AnimationUtils.loadAnimation(this,R.anim.tarjeta_oferta_semanal);
        txt_porcentaje_descuento_cardview_oferta_semana = (TextView) findViewById(R.id.txt_porcentaje_descuento_cardview_oferta_semana);


        extrasPresenter.cargarExtras(MetodosSharedPreference.ObtenerTipoVehiculoPref(sharedPreferences));
        extrasPresenter.CargarOfertaSemanal(MetodosSharedPreference.ObtenerTipoVehiculoPref(sharedPreferences));
        extrasPresenter.obtenerPreciototal(conn);



    }

    @Override
    public void showExtras(final List<Producto_Extras> producto_extras) {
        AdapterRecyclerViewExtras vehiculo = new AdapterRecyclerViewExtras(R.layout.cardview_extras, producto_extras, Extras.this, getApplicationContext(), new onItemClickListenerExtras() {

            @Override
            public void onItemClickListenerDescripcion(View view, String titulo, String descripcion, String precio) {
                extrasPresenter.showDescripcionExtra(titulo, descripcion, precio);
            }

            @Override
            public void onItemClickListenerCheckList(View view, boolean is_checked, String nombre, double precio, String parametro) {
                if (is_checked){
                    extrasPresenter.addExtraToCart(conn, nombre, precio, parametro);
                }else{
                    extrasPresenter.removeExtraToCart(conn, nombre);
                }
            }
        });
        recycler_extras.setAdapter(vehiculo);
    }

    @Override
    public void showDescripcionExtra(String titulo, String descripcion, String precio) {
        AlertDialog.Builder alert = new AlertDialog.Builder(Extras.this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.dialog_descripcion_extras, null);
        alert.setView(dialoglayout);
        final AlertDialog alertDialog = alert.show();

        TextView txtTitutlo  = (TextView) dialoglayout.findViewById(R.id.dialog_title);
        TextView txtDescripcion  = (TextView) dialoglayout.findViewById(R.id.dialog_description);
        TextView txtPrecio  = (TextView) dialoglayout.findViewById(R.id.dialog_price);

        txtTitutlo.setText(titulo);
        txtDescripcion.setText(descripcion);
        txtPrecio.setText(precio);

        LinearLayout dialogcontent = (LinearLayout) dialoglayout.findViewById(R.id.dialog_content);
        dialogcontent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void showProgressbar() {
        progressbar_extra.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressbar() {
        progressbar_extra.setVisibility(View.GONE);
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
                extrasPresenter.RegresarActivity();
                alertDialogConfirmacion.dismiss();
            }
        });
    }

    @Override
    public void hideExtras() {
        recycler_extras.setVisibility(View.GONE);
    }

    @Override
    public void setTotalPrice(String totalPrice) {
        txt_total_price.setText(totalPrice);
    }

    @Override
    public void CargarCitas() {
        Intent intentKlavadoProgramacion = new Intent(Extras.this,Cita.class);
        startActivity(intentKlavadoProgramacion);
    }

    @Override
    public void RegresarActivity() {
        onBackPressed();
    }

    @Override
    public void BloquearBotonContinuar() {
        button_extras_continuar.setClickable(false);
    }

    @Override
    public void DesbloquearBotonContinuar() {
        button_extras_continuar.setClickable(true);
    }

    @Override
    public void showOfertas() {
        cardview_oferta_semanal.setVisibility(View.VISIBLE);
        cardview_oferta_semanal.setAnimation(tarjeta_oferta_semanal_animation);
    }

    @Override
    public void hideOfertas() {
        cardview_oferta_semanal.setVisibility(View.GONE);
    }

    @Override
    public void setOfertaSemanal(String nombre, String precio_original, String precio_descuento, String porcentaje_descuento) {
        txt_nombre_cardview_oferta_semana.setText(nombre);
        txt_precio_original.setText(precio_original);
        txt_precio_final_cardview_oferta_semana.setText(precio_descuento);
        txt_porcentaje_descuento_cardview_oferta_semana.setText(porcentaje_descuento);
    }

    @Override
    public void RegresarMainActivity() {
        Intent intentKlavadoServicio = new Intent(Extras.this, MenuPrincipal.class);
        intentKlavadoServicio.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentKlavadoServicio);
    }

    @Override
    public void AnimacionTitulo1() {

    }

    @Override
    public void AnimacionTitulo2() {
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.button_extras_continuar:
                extrasPresenter.CargarCitas();
                break;

            /*case R.id.btn_back_extras:
                extrasPresenter.RegresarActivity();
                break;*/

            case R.id.checkbox_cardview_oferta_semana:
                if (checkbox_cardview_oferta_semana.isChecked()){
                    extrasPresenter.addExtraToCart(conn, txt_nombre_cardview_oferta_semana.getText().toString(), Double.valueOf(txt_precio_final_cardview_oferta_semana.getText().toString().replace("$","")), "");
                }else{
                    extrasPresenter.removeExtraToCart(conn, txt_nombre_cardview_oferta_semana.getText().toString());
                }
                break;

            default:
                Toast.makeText(this, "Ocurrió un error", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
