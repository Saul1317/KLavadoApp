package com.example.sauldelgado.klavadoapp.TipoLavado.Adapter;

import android.view.View;

import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;

public interface onItemLavadoClickListener {
    void onItemClickListener(View view, int position, Producto producto_selected);
}
