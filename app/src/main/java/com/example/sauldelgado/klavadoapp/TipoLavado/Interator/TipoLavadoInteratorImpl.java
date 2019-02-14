package com.example.sauldelgado.klavadoapp.TipoLavado.Interator;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sauldelgado.klavadoapp.Data.Local.SharedPreference.MetodosSharedPreference;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.Data.Remoto.Retrofit.NetworkAdapter;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;
import com.example.sauldelgado.klavadoapp.TipoLavado.Presenter.Fragment.OnLavadoFragmentFinish;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipoLavadoInteratorImpl implements TipoLavadoInterator {

    @Override
    public void getlavadosPremium(String vehiculos, final OnLavadoFragmentFinish onTipoLavadoFinish) {
        Call<List<Producto>> auto = NetworkAdapter.getApiService().getProductos(
                "getProductos.php", vehiculos, "hidrolavado");
        auto.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    List<Producto> productoList = response.body();
                    Log.e("Productos", productoList.toString());
                    onTipoLavadoFinish.setDescriptionServices(productoList);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("WEB SERVICES", t.getMessage());
            }
        });
    }

    @Override
    public void getlavadosEcologico(String vehiculos, final OnLavadoFragmentFinish onTipoLavadoFinish) {
        Call<List<Producto>> auto = NetworkAdapter.getApiService().getProductos(
                "getProductos.php", vehiculos, "ecologico");
        auto.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    List<Producto> productoList = response.body();
                    Log.e("Productos", productoList.toString());
                    onTipoLavadoFinish.setDescriptionServices(productoList);
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("WEB SERVICES", t.getMessage());
            }
        });
    }

    @Override
    public void addProductToCart(ConexionSQLite conn, Producto producto, SharedPreferences sharedPreferences, OnLavadoFragmentFinish onLavadoFragmentFinish) {
        SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
        sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_PRODUCTO, null, null);
        sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_EXTRA, null, null);
        sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_CITA, null, null);
        ContentValues values = new ContentValues();
        values.put(SQLiteTablas.CAMPO_NOMBRE_PRODUCTO, producto.getNombreProducto());
        values.put(SQLiteTablas.CAMPO_PRECIO_PRODUCTO, Double.valueOf(producto.getPrecioProducto()));
        values.put(SQLiteTablas.CAMPO_TIPO_PRODUCTO, producto.getTipoProducto());
        Long idResultado = sqLiteDatabase.insert(SQLiteTablas.TABLA_NOMBRE_PRODUCTO, SQLiteTablas.CAMPO_ID_PRODUCTO, values);
        if(idResultado != -1) {
            onLavadoFragmentFinish.addSuccessful();
            MetodosSharedPreference.GuardarTipoVehiculoPref(sharedPreferences, producto.getVehiculos());
        }
        else{
            onLavadoFragmentFinish.addUnsuccessful();
        }
        sqLiteDatabase.close();
    }
}
