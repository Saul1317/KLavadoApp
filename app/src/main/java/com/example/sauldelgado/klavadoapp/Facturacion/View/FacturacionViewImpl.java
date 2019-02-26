package com.example.sauldelgado.klavadoapp.Facturacion.View;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.DatosPersonales.View.DatosPersonales;
import com.example.sauldelgado.klavadoapp.Facturacion.Presenter.FacturaPresenterImpl;
import com.example.sauldelgado.klavadoapp.Facturacion.Presenter.FacturacionPresenter;
import com.example.sauldelgado.klavadoapp.Pago.View.Activity.Pago;
import com.example.sauldelgado.klavadoapp.R;

public class FacturacionViewImpl extends AppCompatActivity implements FacturacionView, View.OnClickListener {

    TextInputEditText txt_rfc_factura, txt_razon_social_factura, txt_tipo_pago_factura, edt_correo_usuario;
    Button button_factura_continuar;
    private FacturacionPresenter facturacionPresenter;
    private ConexionSQLite conn;
    private AppCompatSpinner spinner_cfdi;
    //private ImageView btn_back_facturacion;

    ConstraintLayout contraintlayout_facturacion;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facturacion_view_impl);

        facturacionPresenter = new FacturaPresenterImpl(this);
        conn = new ConexionSQLite(this, "bd_producto", null, SQLiteTablas.VERSION_BD);

        contraintlayout_facturacion = (ConstraintLayout) findViewById(R.id.contraintlayout_facturacion);
        animationDrawable = (AnimationDrawable) contraintlayout_facturacion.getBackground();
        animationDrawable.setEnterFadeDuration(7000);
        animationDrawable.setExitFadeDuration(7000);
        animationDrawable.start();

        txt_rfc_factura = (TextInputEditText) findViewById(R.id.txt_rfc_factura);
        txt_razon_social_factura = (TextInputEditText) findViewById(R.id.txt_razon_social_factura);

        //btn_back_facturacion = (ImageView) findViewById(R.id.btn_back_facturacion);
        //btn_back_facturacion.setOnClickListener(this);

        edt_correo_usuario = (TextInputEditText) findViewById(R.id.edt_correo_usuario);

        button_factura_continuar = (Button) findViewById(R.id.button_factura_continuar);
        button_factura_continuar.setOnClickListener(this);

        spinner_cfdi = (AppCompatSpinner) findViewById(R.id.spinner_cfdi);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.cfdi_array));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cfdi.setAdapter(dataAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.button_factura_continuar:
                facturacionPresenter.addFacturacion(
                        conn, txt_rfc_factura.getText().toString(),
                        txt_razon_social_factura.getText().toString(),
                        spinner_cfdi.getSelectedItem().toString(),
                        edt_correo_usuario.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void dialogoFacturacion() {
        AlertDialog.Builder alertConfirmacion = new AlertDialog.Builder(this);
        LayoutInflater inflaterConfirmacion = this.getLayoutInflater();
        View dialoglayoutConfirmacion = inflaterConfirmacion.inflate(R.layout.dialog_factura, null);
        alertConfirmacion.setCancelable(false);
        alertConfirmacion.setView(dialoglayoutConfirmacion);
        final AlertDialog alertDialogConfirmacion = alertConfirmacion.show();

        TextView dialog_solicitar_btn_aceptar = (TextView) dialoglayoutConfirmacion.findViewById(R.id.dialog_solicitar_btn_aceptar);
        dialog_solicitar_btn_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogConfirmacion.dismiss();
            }
        });

        TextView dialog_solicitar_btn_cancelar = (TextView) dialoglayoutConfirmacion.findViewById(R.id.dialog_solicitar_btn_cancelar);
        dialog_solicitar_btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirSiguienteActivity();
                alertDialogConfirmacion.dismiss();
            }
        });
    }

    @Override
    public void abrirSiguienteActivity() {
        onBackPressed();
        /*Intent intent = new Intent(FacturacionViewImpl.this, Pago.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
    }

    @Override
    public void facturacionError() {
        Toast.makeText(this, "Ocurrió un problema, intenta de nuevo", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void CorreoVacio() {
        edt_correo_usuario.setError("Campo Obligatorio");
    }

    @Override
    public void RFCvacio() {
        txt_rfc_factura.setError("Campo Obligatorio");
    }

    @Override
    public void RazonSocialVacio() {
        txt_razon_social_factura.setError("Campo Obligatorio");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
