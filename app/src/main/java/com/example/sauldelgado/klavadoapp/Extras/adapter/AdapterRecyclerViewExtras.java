package com.example.sauldelgado.klavadoapp.Extras.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Extras.Model.Producto_Extras;
import com.example.sauldelgado.klavadoapp.R;


import java.util.List;

public class AdapterRecyclerViewExtras extends RecyclerView.Adapter<AdapterRecyclerViewExtras.ExtrasAdapterRecyclerHolder>{

    private int resource;
    private List<Producto_Extras> list_extras;
    private Activity activity;
    private Context context;
    private onItemClickListenerExtras onItemClickListenerExtras;

    public AdapterRecyclerViewExtras(int resource, List<Producto_Extras> list_extras, Activity activity, Context context, com.example.sauldelgado.klavadoapp.Extras.adapter.onItemClickListenerExtras onItemClickListenerExtras) {
        this.resource = resource;
        this.list_extras = list_extras;
        this.activity = activity;
        this.context = context;
        this.onItemClickListenerExtras = onItemClickListenerExtras;
    }

    @NonNull
    @Override
    public ExtrasAdapterRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup,false);
        return new AdapterRecyclerViewExtras.ExtrasAdapterRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ExtrasAdapterRecyclerHolder holder, int position) {
        final Producto_Extras extras = list_extras.get(position);
        final String precioFinal;

        if(Double.valueOf(extras.getPrecioExtras()) <= 0) {
            precioFinal = "Gratis";
        }else{
            precioFinal = "$" + extras.getPrecioExtras();
        }

        holder.txt_nombre_extra.setText(extras.getNombreExtras());
        holder.txt_precio_extras.setText(precioFinal);

        holder.txt_nombre_extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListenerExtras != null) {
                    onItemClickListenerExtras.onItemClickListenerDescripcion(view, extras.getNombreExtras(), extras.getDescripcionExtras(), precioFinal);
                }
            }
        });

        holder.checkbox_extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkbox_extra.isChecked()){
                    switch (extras.getTipoExtra()){

                        case "solicitud":

                            AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                            LayoutInflater inflater = activity.getLayoutInflater();
                            View dialoglayout = inflater.inflate(R.layout.dialog_solicitud_extra, null);
                            alert.setCancelable(false);
                            alert.setView(dialoglayout);
                            final AlertDialog alertDialog = alert.show();

                            TextView txtTitutlo  = (TextView) dialoglayout.findViewById(R.id.dialog_solicitar_title);
                            txtTitutlo.setText(extras.getNombreExtras());

                            final TextInputEditText textInputEditText = (TextInputEditText) dialoglayout.findViewById(R.id.dialog_solicitar_edt);
                            textInputEditText.setHint(extras.getAdvertencia());

                            Button botonAceptar = (Button) dialoglayout.findViewById(R.id.dialog_solicitar_btn_aceptar);
                            botonAceptar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(textInputEditText.getText().toString().isEmpty()){
                                        Toast.makeText(activity, "Debes ingresar el dato", Toast.LENGTH_SHORT).show();
                                    }else {
                                        holder.checkbox_extra.setChecked(true);
                                        if (onItemClickListenerExtras != null) {
                                            onItemClickListenerExtras.onItemClickListenerCheckList(v, holder.checkbox_extra.isChecked(), extras.getNombreExtras(), Double.valueOf(extras.getPrecioExtras()), textInputEditText.getText().toString());
                                        }
                                        alertDialog.dismiss();
                                    }
                                }
                            });

                            Button botonCancelar = (Button) dialoglayout.findViewById(R.id.dialog_solicitar_btn_cancelar);
                            botonCancelar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    holder.checkbox_extra.setChecked(false);
                                    Log.e("Mensaje", "Mensaje rechazado");
                                    alertDialog.dismiss();
                                }
                            });

                            break;

                        case "confirmacion":

                            AlertDialog.Builder alertConfirmacion = new AlertDialog.Builder(activity);
                            LayoutInflater inflaterConfirmacion = activity.getLayoutInflater();
                            View dialoglayoutConfirmacion = inflaterConfirmacion.inflate(R.layout.dialog_confirmacion, null);
                            alertConfirmacion.setCancelable(false);
                            alertConfirmacion.setView(dialoglayoutConfirmacion);
                            final AlertDialog alertDialogConfirmacion = alertConfirmacion.show();

                            TextView txtTitutlo_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_title);
                            txtTitutlo_dialog_confirmacion.setText(extras.getNombreExtras());

                            TextView txtDescripcion_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_descripcion);
                            txtDescripcion_dialog_confirmacion.setText(extras.getAdvertencia());

                            Button botonAceptar_dialog_confirmacion_aceptar = (Button) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_btn_aceptar);
                            botonAceptar_dialog_confirmacion_aceptar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    holder.checkbox_extra.setChecked(true);
                                    if (onItemClickListenerExtras != null) {
                                        onItemClickListenerExtras.onItemClickListenerCheckList(v, holder.checkbox_extra.isChecked(), extras.getNombreExtras(), Double.valueOf(extras.getPrecioExtras()), null);
                                    }
                                    alertDialogConfirmacion.dismiss();
                                }
                            });

                            Button botonAceptar_dialog_confirmacion_cancelar = (Button) dialoglayoutConfirmacion.findViewById(R.id.dialog_confirmacion_btn_cancelar);
                            botonAceptar_dialog_confirmacion_cancelar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    holder.checkbox_extra.setChecked(false);
                                    alertDialogConfirmacion.dismiss();
                                }
                            });

                            break;

                        case "aromatizante":

                            AlertDialog.Builder alertAromatizante = new AlertDialog.Builder(activity);
                            LayoutInflater inflaterAromatizante = activity.getLayoutInflater();
                            final View dialoglayoutAromatizante = inflaterAromatizante.inflate(R.layout.dialog_opciones, null);
                            alertAromatizante.setCancelable(false);
                            alertAromatizante.setView(dialoglayoutAromatizante);
                            final AlertDialog alertDialogAromatizante = alertAromatizante.show();
                            final RadioGroup radio_group_aromatizante = (RadioGroup) dialoglayoutAromatizante.findViewById(R.id.radio_group_aromatizante);
                            radio_group_aromatizante.check(R.id.aromatizante_briza_limpio);
                            Button dialog_aromatizante_btn_aceptar = (Button) dialoglayoutAromatizante.findViewById(R.id.dialog_aromatizante_btn_aceptar);
                            dialog_aromatizante_btn_aceptar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AppCompatRadioButton radiovalue = (AppCompatRadioButton)dialoglayoutAromatizante.findViewById(radio_group_aromatizante.getCheckedRadioButtonId());
                                    holder.checkbox_extra.setChecked(true);
                                    if (onItemClickListenerExtras != null) {
                                        onItemClickListenerExtras.onItemClickListenerCheckList(v, holder.checkbox_extra.isChecked(), extras.getNombreExtras(), Double.valueOf(extras.getPrecioExtras()), radiovalue.getText().toString());
                                    }
                                    alertDialogAromatizante.dismiss();
                                }
                            });

                            Button dialog_aromatizante_btn_cancelar = (Button) dialoglayoutAromatizante.findViewById(R.id.dialog_aromatizante_btn_cancelar);
                            dialog_aromatizante_btn_cancelar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    holder.checkbox_extra.setChecked(false);
                                    alertDialogAromatizante.dismiss();
                                }
                            });

                            break;

                        case "default":

                            holder.checkbox_extra.setChecked(true);
                            if (onItemClickListenerExtras != null) {
                                onItemClickListenerExtras.onItemClickListenerCheckList(v, holder.checkbox_extra.isChecked(), extras.getNombreExtras(), Double.valueOf(extras.getPrecioExtras()), null);
                            }
                            break;

                        default:
                            break;
                    }

                }else{
                    holder.checkbox_extra.setChecked(false);
                    if (onItemClickListenerExtras != null) {
                        onItemClickListenerExtras.onItemClickListenerCheckList(v, holder.checkbox_extra.isChecked(), extras.getNombreExtras(), Double.valueOf(extras.getPrecioExtras()), extras.getTipoExtra());
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list_extras.size();
    }

    public class ExtrasAdapterRecyclerHolder extends RecyclerView.ViewHolder{
        private TextView txt_nombre_extra;
        private TextView txt_precio_extras;
        private AppCompatCheckBox checkbox_extra;
        //private ImageView checkbox_extra_custom;

        public ExtrasAdapterRecyclerHolder(View itemView) {
            super(itemView);
            txt_nombre_extra = (TextView) itemView.findViewById(R.id.txt_nombre_extra);
            txt_precio_extras = (TextView) itemView.findViewById(R.id.txt_precio_extras);
            checkbox_extra = (AppCompatCheckBox) itemView.findViewById(R.id.checkbox_extra);
            //checkbox_extra_custom = (ImageView) itemView.findViewById(R.id.checkbox_extra_custom);
        }
    }
}
