package com.example.sauldelgado.klavadoapp.TipoLavado.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;

import java.util.List;

public class AdapterRecyclerviewLavadoEcologico extends RecyclerView.Adapter<AdapterRecyclerviewLavadoEcologico.DetallesServicioEcologicoAdapterRecyclerHolder> {

    private int resource;
    private List<Producto> detalles_producto;
    private Activity activity;
    private Context context;
    private onItemLavadoClickListener onItemLavadoClickListener;

    public AdapterRecyclerviewLavadoEcologico(int resource, List<Producto> detalles_producto, Activity activity, Context context, com.example.sauldelgado.klavadoapp.TipoLavado.Adapter.onItemLavadoClickListener onItemLavadoClickListener) {
        this.resource = resource;
        this.detalles_producto = detalles_producto;
        this.activity = activity;
        this.context = context;
        this.onItemLavadoClickListener = onItemLavadoClickListener;
    }

    @NonNull
    @Override
    public DetallesServicioEcologicoAdapterRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup,false);
        return new AdapterRecyclerviewLavadoEcologico.DetallesServicioEcologicoAdapterRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetallesServicioEcologicoAdapterRecyclerHolder holder, final int position) {
        final Producto producto = detalles_producto.get(position);
        holder.txt_titulo_servicio.setText(producto.getNombreProducto());
        holder.txt_caracteristicas.setText(producto.getDescripcionProducto().replace(", ", "\n"));
        holder.txt_costo_servicio.setText(producto.getPrecioProducto());
        holder.cardview_detalle_entrega_ecologico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemLavadoClickListener != null) {
                    onItemLavadoClickListener.onItemClickListener(view, position, producto);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return detalles_producto.size();
    }

    public class DetallesServicioEcologicoAdapterRecyclerHolder extends RecyclerView.ViewHolder{
        private FrameLayout cardview_detalle_entrega_ecologico;
        private TextView txt_titulo_servicio;
        private TextView txt_caracteristicas;
        private TextView txt_costo_servicio;

        public DetallesServicioEcologicoAdapterRecyclerHolder(View itemView) {
            super(itemView);
            txt_titulo_servicio = (TextView) itemView.findViewById(R.id.txt_titulo_servicio);
            txt_caracteristicas = (TextView) itemView.findViewById(R.id.txt_caracteristicas);
            txt_costo_servicio = (TextView) itemView.findViewById(R.id.txt_costo_servicio);
            cardview_detalle_entrega_ecologico = (FrameLayout) itemView.findViewById(R.id.cardview_detalle_entrega_ecologico);
        }
    }
}
