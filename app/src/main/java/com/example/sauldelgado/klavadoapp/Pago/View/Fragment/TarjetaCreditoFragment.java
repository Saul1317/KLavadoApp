package com.example.sauldelgado.klavadoapp.Pago.View.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.Facturacion.View.FacturacionViewImpl;
import com.example.sauldelgado.klavadoapp.Finalizado.View.FinalizacionServicio;
import com.example.sauldelgado.klavadoapp.Pago.Presenter.PagoPresenter;
import com.example.sauldelgado.klavadoapp.Pago.Presenter.PagoPresenterImpl;
import com.example.sauldelgado.klavadoapp.Pago.View.Activity.Pago;
import com.example.sauldelgado.klavadoapp.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TarjetaCreditoFragment extends Fragment implements TarjetaCreditoView, View.OnClickListener {

    private TextInputEditText fragment_num_tarjeta_credito, fragment_nombre_tarjeta_credito, fragment_fecha_tarjeta_credito, fragment_cvv_tarjeta_credito;
    private LinearLayout boton_agregar_facturacion;
    private Button fragment_button_pagar;
    private PagoPresenter pagoPresenter;
    private ConexionSQLite conn;
    private ImageView img_agregar_factura;
    private TextView txt_agregar_factura;
    int count = 0;
    int countCvv = 0;
    boolean estadoFacturacion = false;

    public TarjetaCreditoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tarjeta_credito, container, false);
        conn = new ConexionSQLite(getActivity(), "bd_producto", null, SQLiteTablas.VERSION_BD);
        pagoPresenter = new PagoPresenterImpl(this);

        boton_agregar_facturacion = (LinearLayout) view.findViewById(R.id.boton_agregar_facturacion);
        boton_agregar_facturacion.setOnClickListener(this);

        fragment_num_tarjeta_credito = (TextInputEditText) view.findViewById(R.id.fragment_num_tarjeta_credito);
        fragment_num_tarjeta_credito.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (count <= fragment_num_tarjeta_credito.getText().toString().length()
                        &&(fragment_num_tarjeta_credito.getText().toString().length()==4
                        ||fragment_num_tarjeta_credito.getText().toString().length()==9
                        ||fragment_num_tarjeta_credito.getText().toString().length()==14)){
                    fragment_num_tarjeta_credito.setText(fragment_num_tarjeta_credito.getText().toString()+"-");
                    int pos = fragment_num_tarjeta_credito.getText().length();
                    fragment_num_tarjeta_credito.setSelection(pos);
                }else if (count >= fragment_num_tarjeta_credito.getText().toString().length()
                        &&(fragment_num_tarjeta_credito.getText().toString().length()==4
                        ||fragment_num_tarjeta_credito.getText().toString().length()==9
                        ||fragment_num_tarjeta_credito.getText().toString().length()==14)){
                    fragment_num_tarjeta_credito.setText(fragment_num_tarjeta_credito.getText().toString().substring(0,fragment_num_tarjeta_credito.getText().toString().length()-1));
                    int pos = fragment_num_tarjeta_credito.getText().length();
                    fragment_num_tarjeta_credito.setSelection(pos);
                }
                count = fragment_num_tarjeta_credito.getText().toString().length();
            }
        });
        fragment_nombre_tarjeta_credito = (TextInputEditText) view.findViewById(R.id.fragment_nombre_tarjeta_credito);
        fragment_fecha_tarjeta_credito = (TextInputEditText) view.findViewById(R.id.fragment_fecha_tarjeta_credito);
        fragment_fecha_tarjeta_credito.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (countCvv <= fragment_fecha_tarjeta_credito.getText().toString().length() &&(fragment_fecha_tarjeta_credito.getText().toString().length()==2))
                { fragment_fecha_tarjeta_credito.setText(fragment_fecha_tarjeta_credito.getText().toString()+"/");
                    int pos = fragment_fecha_tarjeta_credito.getText().length();
                    fragment_fecha_tarjeta_credito.setSelection(pos);
                }else if (countCvv >= fragment_fecha_tarjeta_credito.getText().toString().length()
                        &&(fragment_fecha_tarjeta_credito.getText().toString().length()== 2)){
                    fragment_fecha_tarjeta_credito.setText(fragment_fecha_tarjeta_credito.getText().toString().substring(0,fragment_fecha_tarjeta_credito.getText().toString().length()-1));
                    int pos = fragment_fecha_tarjeta_credito.getText().length();
                    fragment_fecha_tarjeta_credito.setSelection(pos);
                }
                countCvv = fragment_fecha_tarjeta_credito.getText().toString().length();
            }
        });
        fragment_cvv_tarjeta_credito = (TextInputEditText) view.findViewById(R.id.fragment_cvv_tarjeta_credito);
        fragment_button_pagar = (Button) view.findViewById(R.id.fragment_button_pagar);
        fragment_button_pagar.setOnClickListener(this);

        img_agregar_factura = (ImageView) view.findViewById(R.id.img_agregar_factura);
        txt_agregar_factura = (TextView) view.findViewById(R.id.txt_agregar_factura);


        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        pagoPresenter.validarFacturacion(conn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_button_pagar:
                ((PagoPresenterImpl) pagoPresenter).InsertarInformacion(
                        conn, fragment_num_tarjeta_credito.getText().toString(), fragment_nombre_tarjeta_credito.getText().toString(),
                        fragment_fecha_tarjeta_credito.getText().toString(), fragment_cvv_tarjeta_credito.getText().toString());
                break;

            case R.id.boton_agregar_facturacion:
                ((PagoPresenterImpl) pagoPresenter).AbrirDatosFacturacion(estadoFacturacion);
                break;
            default:
                break;
        }
    }

    @Override
    public void AbrirResumenDeCompra() {
        Intent intent = new Intent(getActivity(), FinalizacionServicio.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void AbrirDatosFacturacion() {
        Intent intent = new Intent(getActivity(), FacturacionViewImpl.class);
        getActivity().startActivity(intent);
    }

    @Override
    public void dialogEliminarDatosFacturacion() {
        AlertDialog.Builder alertConfirmacion = new AlertDialog.Builder(getContext());
        LayoutInflater inflaterConfirmacion = getActivity().getLayoutInflater();
        View dialoglayoutConfirmacion = inflaterConfirmacion.inflate(R.layout.dialog_confirmacion, null);
        alertConfirmacion.setCancelable(false);
        alertConfirmacion.setView(dialoglayoutConfirmacion);
        final AlertDialog alertDialogConfirmacion = alertConfirmacion.show();

        TextView txtTitutlo_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_title);
        txtTitutlo_dialog_confirmacion.setText("Eliminar Datos de Facturación");

        TextView txtDescripcion_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_descripcion);
        txtDescripcion_dialog_confirmacion.setText("¿Quieres eliminar los datos de facturación?");

        Button botonAceptar_dialog_confirmacion_aceptar = (Button) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_btn_aceptar);
        botonAceptar_dialog_confirmacion_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagoPresenter.EliminarDatosFacturacion(conn);
                alertDialogConfirmacion.dismiss();
            }
        });

        Button botonAceptar_dialog_confirmacion_cancelar = (Button) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_btn_cancelar);
        botonAceptar_dialog_confirmacion_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogConfirmacion.dismiss();
            }
        });
    }

    @Override
    public void dialogAceptarFacturacion() {
        AlertDialog.Builder alertConfirmacion = new AlertDialog.Builder(getContext());
        LayoutInflater inflaterConfirmacion = getActivity().getLayoutInflater();
        View dialoglayoutConfirmacion = inflaterConfirmacion.inflate(R.layout.dialog_confirmacion, null);
        alertConfirmacion.setCancelable(false);
        alertConfirmacion.setView(dialoglayoutConfirmacion);
        final AlertDialog alertDialogConfirmacion = alertConfirmacion.show();

        TextView txtTitutlo_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_title);
        txtTitutlo_dialog_confirmacion.setText("Datos de Facturación");

        TextView txtDescripcion_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_descripcion);
        txtDescripcion_dialog_confirmacion.setText("Se le agregara el IVA al cargo total del servicio, ¿Desea continuar?");

        Button botonAceptar_dialog_confirmacion_aceptar = (Button) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_btn_aceptar);
        botonAceptar_dialog_confirmacion_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pagoPresenter.AbrirFormularioDatosFacturacion();
                alertDialogConfirmacion.dismiss();
            }
        });

        Button botonAceptar_dialog_confirmacion_cancelar = (Button) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_btn_cancelar);
        botonAceptar_dialog_confirmacion_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogConfirmacion.dismiss();
            }
        });
    }

    @Override
    public void setPrecioTotal(String precioTotal) {
        fragment_button_pagar.setText(precioTotal);
    }

    @Override
    public void PagoError() {
        Toast.makeText(getActivity(), "Ocurrió un error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void MostrarFacturacionAgregadaCorrectamente() {
        estadoFacturacion = true;
        txt_agregar_factura.setText("Datos de facturacion agregados");
        txt_agregar_factura.setTextColor(getResources().getColor(R.color.light_text));
        img_agregar_factura.setBackgroundResource(R.drawable.correct);
        boton_agregar_facturacion.setBackgroundResource(R.drawable.layout_redondo_successful);
        pagoPresenter.obtenerPreciototal(conn, estadoFacturacion);
    }

    @Override
    public void MostrarSinFactura() {
        estadoFacturacion = false;
        txt_agregar_factura.setText("Agregar datos de facturación");
        txt_agregar_factura.setTextColor(getResources().getColor(R.color.primary_text));
        img_agregar_factura.setBackgroundResource(R.drawable.add);
        boton_agregar_facturacion.setBackgroundResource(R.drawable.layout_redondo);
        pagoPresenter.obtenerPreciototal(conn, estadoFacturacion);
    }
}
