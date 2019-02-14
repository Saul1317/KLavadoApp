package com.example.sauldelgado.klavadoapp.Services.Adapter;

import android.view.View;

import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;


public interface onItemClickListener {
    void onItemClickListener(View view, int position, Producto producto_selected);
}
