package com.example.sauldelgado.klavadoapp.TipoLavado.View.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import com.example.sauldelgado.klavadoapp.TipoLavado.Adapter.AdapterRecyclerviewLavado;
import com.example.sauldelgado.klavadoapp.TipoLavado.Adapter.onItemLavadoClickListener;
import com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Fragment.LavadoPremiumFragmentPresenter;
import com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Fragment.LavadoPremiumFragmentPresenterImpl;

import java.util.List;
/**
 * A simple {@link Fragment} subclass.
 */
public class LavadoPremiumFragmentImpl extends Fragment implements LavadoPremiumFragment {

    RecyclerView recyclerview_lavado_premium;
    LinearLayoutManager servicio;
    LavadoPremiumFragmentPresenter lavadoPremiumFragmentPresenter;
    ProgressBar progress_bar_premium;
    private ConexionSQLite conn;
    private SharedPreferences sharedPreferences;
    AlertDialog alertDialogConfirmacion;

    ConstraintLayout constraintLayout_premium;
    AnimationDrawable animationDrawable;

    public LavadoPremiumFragmentImpl() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lavado_premium, container, false);
        lavadoPremiumFragmentPresenter = new LavadoPremiumFragmentPresenterImpl(this);
        progress_bar_premium = (ProgressBar) view.findViewById(R.id.progress_bar_premium);
        conn = new ConexionSQLite(getContext(), "bd_producto", null, SQLiteTablas.VERSION_BD);
        sharedPreferences = getActivity().getSharedPreferences("klavado", Context.MODE_PRIVATE);

        /*constraintLayout_premium = (ConstraintLayout) view.findViewById(R.id.constraintLayout_premium);
        animationDrawable = (AnimationDrawable) constraintLayout_premium.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();*/

        recyclerview_lavado_premium = (RecyclerView) view.findViewById(R.id.recyclerview_lavado_premium);
        LinearLayoutManager linearLayoutManagerExtras = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerview_lavado_premium.setLayoutManager(linearLayoutManagerExtras);
        lavadoPremiumFragmentPresenter.getlavadosPremium(MetodosSharedPreference.ObtenerTipoVehiculoPref(sharedPreferences));
        return view;
    }

    @Override
    public void setLavadoPremiumContenido(List<Producto> servicioPremium) {
        AdapterRecyclerviewLavado vehiculo = new AdapterRecyclerviewLavado(R.layout.cardview_servicio_hidrolavado, servicioPremium, getActivity(), getContext(), new onItemLavadoClickListener() {
            @Override
            public void onItemClickListener(View view, int position, Producto producto_selected) {
                lavadoPremiumFragmentPresenter.addProductToCart(conn, producto_selected, sharedPreferences);
            }
        });
        recyclerview_lavado_premium.setAdapter(vehiculo);
    }

    @Override
    public void showProgressBar() {
        progress_bar_premium.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progress_bar_premium.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerview() {
        recyclerview_lavado_premium.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRecyclerview() {
        recyclerview_lavado_premium.setVisibility(View.GONE);
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
