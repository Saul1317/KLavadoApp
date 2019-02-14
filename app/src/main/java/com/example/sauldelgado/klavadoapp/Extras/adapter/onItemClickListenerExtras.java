package com.example.sauldelgado.klavadoapp.Extras.adapter;

import android.view.View;

public interface onItemClickListenerExtras {

    void onItemClickListenerDescripcion(View view, String titulo, String descripcion, String precio);
    void onItemClickListenerCheckList(View view, boolean is_checked, String nombre, double precio, String parametro);

}
