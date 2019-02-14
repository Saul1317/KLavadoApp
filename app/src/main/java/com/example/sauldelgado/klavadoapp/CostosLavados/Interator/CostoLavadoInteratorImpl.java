package com.example.sauldelgado.klavadoapp.CostosLavados.Interator;

import android.util.Log;

import com.example.sauldelgado.klavadoapp.CostosLavados.Presenter.OnCostoLavadoFinish;
import com.example.sauldelgado.klavadoapp.Data.Remoto.Retrofit.NetworkAdapter;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CostoLavadoInteratorImpl implements CostoLavadoInterator{
    @Override
    public void getCostoLavado(String vehiculos, OnCostoLavadoFinish onCostoLavadoFinish) {
        getCostoLavadoPremium(vehiculos, onCostoLavadoFinish);
        getCostoLavadoEcologico(vehiculos, onCostoLavadoFinish);
    }

    private void getCostoLavadoPremium(String vehiculos, final OnCostoLavadoFinish onCostoLavadoFinish) {
        Call<List<Producto>> auto = NetworkAdapter.getApiService().getProductos(
                "getProductos.php", vehiculos, "hidrolavado");
        auto.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    List<Producto> productoList = response.body();
                    onCostoLavadoFinish.setCostoLavadoPremium("$"+productoList.get(0).getPrecioProducto(), "$"+productoList.get(1).getPrecioProducto());
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("WEB SERVICES", t.getMessage());
            }
        });
    }

    private void getCostoLavadoEcologico(String vehiculos, final OnCostoLavadoFinish onCostoLavadoFinish) {
        Call<List<Producto>> auto = NetworkAdapter.getApiService().getProductos(
                "getProductos.php", vehiculos, "ecologico");
        auto.enqueue(new Callback<List<Producto>>() {
            @Override
            public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
                if (response.isSuccessful()) {
                    List<Producto> productoList = response.body();
                    onCostoLavadoFinish.setCostoLavadoEcologico("$"+productoList.get(0).getPrecioProducto(), "$"+productoList.get(1).getPrecioProducto());
                }
            }

            @Override
            public void onFailure(Call<List<Producto>> call, Throwable t) {
                Log.e("WEB SERVICES", t.getMessage());
            }
        });
    }


}
