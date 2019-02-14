package com.example.sauldelgado.klavadoapp.Services.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;

import java.util.List;

public class AdapterDetallesServicio extends RecyclerView.Adapter<AdapterDetallesServicio.DetallesServicioAdapterRecyclerHolder>{

    private int resource;
    private List<Producto> detalles_producto;
    private Activity activity;
    private Context context;
    private onItemClickListener mItemClickListener;

    public AdapterDetallesServicio(int resource, List<Producto> detalles_producto, Activity activity, Context context, onItemClickListener mItemClickListener) {
        this.resource = resource;
        this.detalles_producto = detalles_producto;
        this.activity = activity;
        this.context = context;
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public DetallesServicioAdapterRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup,false);
        return new AdapterDetallesServicio.DetallesServicioAdapterRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetallesServicioAdapterRecyclerHolder holder, final int position) {
        final Producto producto = detalles_producto.get(position);
        holder.txt_titulo_servicio.setText(producto.getNombreProducto());
        holder.txt_caracteristicas.setText(producto.getDescripcionProducto().replace(", ", "\n"));
        holder.txt_costo_servicio.setText(producto.getPrecioProducto());
        holder.cardview_detalle_entrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClickListener(view, position, producto);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return detalles_producto.size();
    }

    public class DetallesServicioAdapterRecyclerHolder extends RecyclerView.ViewHolder{
        private CardView cardview_detalle_entrega;
        private TextView txt_titulo_servicio;
        private TextView txt_caracteristicas;
        private TextView txt_costo_servicio;

        public DetallesServicioAdapterRecyclerHolder(View itemView) {
            super(itemView);
            txt_titulo_servicio = (TextView) itemView.findViewById(R.id.txt_titulo_servicio);
            txt_caracteristicas = (TextView) itemView.findViewById(R.id.txt_caracteristicas);
            txt_costo_servicio = (TextView) itemView.findViewById(R.id.txt_costo_servicio);
            cardview_detalle_entrega = (CardView) itemView.findViewById(R.id.cardview_detalle_entrega_ecologico);
        }
    }
}
