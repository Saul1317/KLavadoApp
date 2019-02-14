package com.example.sauldelgado.klavadoapp.Finalizado.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sauldelgado.klavadoapp.Extras.Model.ExtrasSQLite;
import com.example.sauldelgado.klavadoapp.Extras.Model.Producto_Extras;
import com.example.sauldelgado.klavadoapp.R;

import java.util.List;

public class AdapterRecyclerViewExtrasSeleccionados extends RecyclerView.Adapter<AdapterRecyclerViewExtrasSeleccionados.ExtrasSeleccionadosAdapterRecyclerHolder>{

    private int resource;
    private List<ExtrasSQLite> list_extras;
    private Activity activity;
    private Context context;
    private onItemClickListenerExtrasSeleccionado onItemClickListenerExtrasSeleccionado;

    public AdapterRecyclerViewExtrasSeleccionados(int resource, List<ExtrasSQLite> list_extras, Activity activity, Context context, onItemClickListenerExtrasSeleccionado onItemClickListenerExtrasSeleccionado) {
        this.resource = resource;
        this.list_extras = list_extras;
        this.activity = activity;
        this.context = context;
        this.onItemClickListenerExtrasSeleccionado = onItemClickListenerExtrasSeleccionado;
    }

    @NonNull
    @Override
    public ExtrasSeleccionadosAdapterRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup,false);
        return new AdapterRecyclerViewExtrasSeleccionados.ExtrasSeleccionadosAdapterRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtrasSeleccionadosAdapterRecyclerHolder holder, int position) {
        ExtrasSQLite extras = list_extras.get(position);
        holder.txt_nombre_extra.setText(extras.getNombre_extra());
        holder.txt_precio_extras.setText("$" + String.valueOf(extras.getPrecio_extra()));
    }

    @Override
    public int getItemCount() {
        return list_extras.size();
    }

    public class ExtrasSeleccionadosAdapterRecyclerHolder extends RecyclerView.ViewHolder{
        private TextView txt_nombre_extra;
        private TextView txt_precio_extras;

        public ExtrasSeleccionadosAdapterRecyclerHolder(View itemView) {
            super(itemView);
            txt_nombre_extra = (TextView) itemView.findViewById(R.id.txt_nombre_extra_seleccionado);
            txt_precio_extras = (TextView) itemView.findViewById(R.id.txt_precio_extra_seleccionado);
        }
    }
}
