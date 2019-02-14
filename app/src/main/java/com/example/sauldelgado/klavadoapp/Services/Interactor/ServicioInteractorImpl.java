package com.example.sauldelgado.klavadoapp.Services.Interactor;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sauldelgado.klavadoapp.Data.Remoto.Retrofit.NetworkAdapter;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;
import com.example.sauldelgado.klavadoapp.Services.Presenter.OnSelectedVehiculeFinish;
import com.example.sauldelgado.klavadoapp.Data.Local.SharedPreference.MetodosSharedPreference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicioInteractorImpl implements ServicioInteractor {

    @Override
    public void getSeriviciosDisponibles(String tipo_vehiculo, OnSelectedVehiculeFinish onSelectedVehiculeFinish) {
        getProductosEcologico(tipo_vehiculo, onSelectedVehiculeFinish);
        getProductosHidrolavado(tipo_vehiculo, onSelectedVehiculeFinish);
    }

    @Override
    public void addProductToCart(ConexionSQLite conn, Producto producto, SharedPreferences sharedPreferences, OnSelectedVehiculeFinish onSelectedVehiculeFinish) {
        SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
        sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_PRODUCTO, null, null);
        sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_EXTRA, null, null);
        sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_CITA, null, null);
        ContentValues values = new ContentValues();
        values.put(SQLiteTablas.CAMPO_NOMBRE_PRODUCTO, producto.getNombreProducto());
        values.put(SQLiteTablas.CAMPO_PRECIO_PRODUCTO, Double.valueOf(producto.getPrecioProducto()));
        Long idResultado = sqLiteDatabase.insert(SQLiteTablas.TABLA_NOMBRE_PRODUCTO, SQLiteTablas.CAMPO_ID_PRODUCTO, values);
        if(idResultado != -1) {
            onSelectedVehiculeFinish.addSuccessful();
            MetodosSharedPreference.GuardarTipoVehiculoPref(sharedPreferences, producto.getVehiculos());
        }
        else{
            onSelectedVehiculeFinish.addUnsuccessful();
        }
        sqLiteDatabase.close();
    }

    private void getProductosEcologico(String tipo_vehiculo, final OnSelectedVehiculeFinish onSelectedVehiculeFinish) {
        Call<List<Producto>> auto = NetworkAdapter.getApiService().getProductos(
                "getProductos.php", tipo_vehiculo, "ecologico");
        auto.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    List<Producto> productoList = response.body();
                    onSelectedVehiculeFinish.setDescriptionServicesEcologico(productoList);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("WEB SERVICES", t.getMessage());
                onSelectedVehiculeFinish.getServiciosError();
            }
        });
    }

    private void getProductosHidrolavado(String tipo_vehiculo, final OnSelectedVehiculeFinish onSelectedVehiculeFinish) {
        Call<List<Producto>> auto = NetworkAdapter.getApiService().getProductos(
                "getProductos.php", tipo_vehiculo, "hidrolavado");
        auto.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    List<Producto> productoList = response.body();
                    onSelectedVehiculeFinish.setDescriptionServicesHidrolavado(productoList);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("WEB SERVICES", t.getMessage());
                onSelectedVehiculeFinish.getServiciosError();
            }
        });
    }
}
