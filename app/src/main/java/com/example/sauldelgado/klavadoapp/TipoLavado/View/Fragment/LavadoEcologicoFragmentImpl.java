package com.example.sauldelgado.klavadoapp.TipoLavado.View.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Data.Local.SharedPreference.MetodosSharedPreference;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.Extras.View.Extras;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;
import com.example.sauldelgado.klavadoapp.TipoLavado.Adapter.AdapterRecyclerviewLavadoEcologico;
import com.example.sauldelgado.klavadoapp.TipoLavado.Adapter.onItemLavadoClickListener;
import com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Fragment.LavadoEcologicoFragmentPresenter;
import com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Fragment.LavadoEcologicoFragmentPresenterImpl;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LavadoEcologicoFragmentImpl extends Fragment implements LavadoEcologicoFragment{

    RecyclerView recyclerview_lavado_ecologico;
    LinearLayoutManager servicio;
    LavadoEcologicoFragmentPresenter lavadoEcologicoFragmentPresenter;
    ProgressBar progress_bar_ecologico;
    private ConexionSQLite conn;
    private SharedPreferences sharedPreferences;
    AlertDialog alertDialogConfirmacion;

    public LavadoEcologicoFragmentImpl() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lavado_ecologico, container, false);
        lavadoEcologicoFragmentPresenter = new LavadoEcologicoFragmentPresenterImpl(this);
        progress_bar_ecologico = (ProgressBar) view.findViewById(R.id.progress_bar_ecologico);
        conn = new ConexionSQLite(getContext(), "bd_producto", null, SQLiteTablas.VERSION_BD);
        sharedPreferences = getActivity().getSharedPreferences("klavado", Context.MODE_PRIVATE);
        recyclerview_lavado_ecologico = (RecyclerView) view.findViewById(R.id.recyclerview_lavado_ecologico);
        LinearLayoutManager linearLayoutManagerExtras = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerview_lavado_ecologico.setLayoutManager(linearLayoutManagerExtras);
        lavadoEcologicoFragmentPresenter.getlavadosEcologico(MetodosSharedPreference.ObtenerTipoVehiculoPref(sharedPreferences));
        return view;
    }

    @Override
    public void setLavadoEcologicoContenido(List<Producto> servicioPremium) {
        AdapterRecyclerviewLavadoEcologico vehiculo = new AdapterRecyclerviewLavadoEcologico(R.layout.cardview_servicios, servicioPremium, getActivity(), getContext(), new onItemLavadoClickListener() {
            @Override
            public void onItemClickListener(View view, int position, Producto producto_selected) {
                lavadoEcologicoFragmentPresenter.addProductToCart(conn, producto_selected, sharedPreferences);
            }
        });
        recyclerview_lavado_ecologico.setAdapter(vehiculo);
    }

    @Override
    public void showProgressBar() {
        progress_bar_ecologico.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progress_bar_ecologico.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerview() {
        recyclerview_lavado_ecologico.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRecyclerview() {
        recyclerview_lavado_ecologico.setVisibility(View.GONE);
    }

    @Override
    public void setErrorConsultaRemota() {
        AlertDialog.Builder alertConfirmacion = new AlertDialog.Builder(getContext());
        LayoutInflater inflaterConfirmacion = this.getLayoutInflater();
        View dialoglayoutConfirmacion = inflaterConfirmacion.inflate(R.layout.dialog_conexion_error, null);
        alertConfirmacion.setCancelable(false);
        alertConfirmacion.setView(dialoglayoutConfirmacion);
        alertDialogConfirmacion = alertConfirmacion.show();
        TextView txtTitutlo_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_titulo);
        TextView txtDescripcion_dialog_confirmacion  = (TextView) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_contenido);
        Button botonAceptar_dialog_confirmacion_aceptar = (Button) dialoglayoutConfirmacion.findViewById(R.id.diaglo_advertencia_button);

        botonAceptar_dialog_confirmacion_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogConfirmacion.dismiss();
            }
        });
    }

    @Override
    public void setErrorInsertLocal() {
        Toast.makeText(getContext(), "Ocurrió un error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openExtras() {
        Intent intentKlavadoMenu = new Intent(getContext(), Extras.class);
        startActivity(intentKlavadoMenu);
    }
}
