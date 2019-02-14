package com.example.sauldelgado.klavadoapp.Extras.Interactor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sauldelgado.klavadoapp.Extras.Model.ExtrasSQLite;
import com.example.sauldelgado.klavadoapp.Extras.Model.Producto_Extras;
import com.example.sauldelgado.klavadoapp.Extras.Presenter.OnSelectedExtraFinish;
import com.example.sauldelgado.klavadoapp.Data.Remoto.Retrofit.NetworkAdapter;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.Productos;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExtrasInteractorImpl implements ExtrasInteractor{

    @Override
    public void cargarExtras(String tipo_vehiculo, final OnSelectedExtraFinish onSelectedExtraFinish) {
        Call<List<Producto_Extras>> minivan = NetworkAdapter.getApiService().getExtras(
                "getExtras.php", tipo_vehiculo);
        minivan.enqueue(new Callback<List<Producto_Extras>>() {
            @Override
            public void onResponse(Call<List<Producto_Extras>> call, Response<List<Producto_Extras>> response) {
                List<Producto_Extras> producto_extras = response.body();
                if (response.isSuccessful()){
                     onSelectedExtraFinish.showExtras(producto_extras);
                }
            }

            @Override
            public void onFailure(Call<List<Producto_Extras>> call, Throwable t) {
                Log.e("LISTADO", t.toString());
                onSelectedExtraFinish.ConsultarExtrasError();
            }
        });
    }

    @Override
    public void sacarPrecioTotal(ConexionSQLite conn, OnSelectedExtraFinish onSelectedExtraFinish) {
        Log.e("Producto", String.valueOf(getPrecioProducto(conn)));
        Log.e("EXTRAS", String.valueOf(getPrecioExtras(conn)));
        onSelectedExtraFinish.setTotalPrice("$"+String.valueOf(getPrecioProducto(conn) + getPrecioExtras(conn)));
    }

    private double getPrecioProducto(ConexionSQLite conn) {
        SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
        List<Productos> listProductos = new ArrayList<Productos>();
        double montoTotal = 0.0;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT id_producto, nombre_producto, precio_producto, tipo_producto FROM " + SQLiteTablas.TABLA_NOMBRE_PRODUCTO, null);
        while(cursor.moveToNext()){
            Productos productos = new Productos(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
            listProductos.add(productos);
        }
        cursor.close();
        sqLiteDatabase.close();

        for(int i=0; i<listProductos.size(); i++){
            montoTotal = listProductos.get(i).getPrecio_producto() + montoTotal;
        }
       return montoTotal;
    }

    private double getPrecioExtras(ConexionSQLite conn) {
        SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
        List<ExtrasSQLite> listProductos = new ArrayList<ExtrasSQLite>();
        double montoTotal = 0.0;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteTablas.CAMPO_ID_EXTRA+", "+SQLiteTablas.CAMPO_NOMBRE_EXTRA+", "+SQLiteTablas.CAMPO_PRECIO_EXTRA+", "+SQLiteTablas.CAMPO_COMENTARIO_EXTRA+" " +
                "FROM " + SQLiteTablas.TABLA_NOMBRE_EXTRA, null);
        while(cursor.moveToNext()){
            ExtrasSQLite extrasSQLite = new ExtrasSQLite(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
            listProductos.add(extrasSQLite);
        }
        cursor.close();
        sqLiteDatabase.close();

        for(int i=0; i<listProductos.size(); i++){
            montoTotal = listProductos.get(i).getPrecio_extra() + montoTotal;
        }
        return montoTotal;
    }

    @Override
    public void addExtraToCart(ConexionSQLite conn, String servicio, double precio_servicio, String parametro, OnSelectedExtraFinish onSelectedExtraFinish) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            String insertProducto = "INSERT INTO extra ("+SQLiteTablas.CAMPO_NOMBRE_EXTRA+", "+SQLiteTablas.CAMPO_PRECIO_EXTRA+", "+SQLiteTablas.CAMPO_COMENTARIO_EXTRA+") VALUES ('"+servicio+"','"+precio_servicio+"','"+parametro+"')";
            sqLiteDatabase.execSQL(insertProducto);
            sqLiteDatabase.close();
            sacarPrecioTotal(conn, onSelectedExtraFinish);
        } catch (Exception e) {
            Log.i("ERROR", "");
        }
    }

    @Override
    public void removeExtraToCart(ConexionSQLite conn, String servicio, OnSelectedExtraFinish onSelectedExtraFinish) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            String[] parametros = {servicio};
            sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_EXTRA, SQLiteTablas.CAMPO_NOMBRE_EXTRA + "=?", parametros);
            sqLiteDatabase.close();
            sacarPrecioTotal(conn, onSelectedExtraFinish);
        } catch (Exception e) {
            Log.i("ERROR", e.getMessage());
        }
    }

    @Override
    public void CargarOfertaSemanal(String tipo_vehiculo, final OnSelectedExtraFinish onSelectedExtraFinish) {
        Call<List<Producto_Extras>> minivan = NetworkAdapter.getApiService().getExtras(
                "getOfertas.php", tipo_vehiculo);
        minivan.enqueue(new Callback<List<Producto_Extras>>() {
            @Override
            public void onResponse(Call<List<Producto_Extras>> call, Response<List<Producto_Extras>> response) {
                if (response.isSuccessful()){
                    List<Producto_Extras> producto_extras = response.body();
                    if(!producto_extras.isEmpty()){
                        double porcentaje_rebaja = (Double.valueOf(producto_extras.get(0).getPrecioExtras()) * Double.valueOf(producto_extras.get(0).getPorcentajeDescuento())) / 100;
                        double precio_final = Double.valueOf(producto_extras.get(0).getPrecioExtras()) - porcentaje_rebaja;
                        String nombre_extra = producto_extras.get(0).getNombreExtras();
                        String precio_original = "$" + producto_extras.get(0).getPrecioExtras();
                        String precio_descuento = "$" + String.valueOf(precio_final) ;
                        String porcentaje_descuento = "Descuento del " + producto_extras.get(0).getPorcentajeDescuento() + "%";
                        onSelectedExtraFinish.setOfertas(nombre_extra, precio_original, precio_descuento, porcentaje_descuento);
                    }else{
                        onSelectedExtraFinish.SinOfertas();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Producto_Extras>> call, Throwable t) {
                Log.e("LISTADO", t.toString());
                onSelectedExtraFinish.SinOfertas();
            }
        });
    }
}



