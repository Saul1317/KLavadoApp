package com.example.sauldelgado.klavadoapp.Pago.Interactor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sauldelgado.klavadoapp.Cita.Model.CitaSQLite;
import com.example.sauldelgado.klavadoapp.Data.Remoto.Retrofit.NetworkAdapter;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.CorreoSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.DireccionSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.TelefonoSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.Usuario;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.VehiculoSQLite;
import com.example.sauldelgado.klavadoapp.Extras.Model.ExtrasSQLite;
import com.example.sauldelgado.klavadoapp.Pago.Presenter.OnPagoFinish;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.Productos;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoInteractorImpl implements PagoInteractor {

    @Override
    public void sacarPrecioTotal(ConexionSQLite conn, boolean estadoFacturacion, OnPagoFinish onPagoFinish) {
        double precioProductos = getPrecioProducto(conn) + getPrecioExtras(conn);
        double IVA = precioProductos * 0.16;
        if(estadoFacturacion){
            onPagoFinish.setTotalPrice("PAGAR SERVICIO $"+String.valueOf(precioProductos + IVA + " (con IVA)"));
        } else {
            onPagoFinish.setTotalPrice("PAGAR SERVICIO $" + String.valueOf(precioProductos));
        }
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
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT id_extra, nombre_extra, precio_extra, "+SQLiteTablas.CAMPO_COMENTARIO_EXTRA+" FROM " + SQLiteTablas.TABLA_NOMBRE_EXTRA, null);
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
    public void InsertarInformacion(ConexionSQLite conn, String numero_tarjeta, String nombre, String fecha_vencimiento, String codigo_seguridad, final OnPagoFinish onPagoFinish) {
        Call<String> setServicio = NetworkAdapter.getApiService().setServicio(
                "setServicio.php", getProductos(conn), getExtras(conn), getCitas(conn), getUsuario(conn), getDireccion(conn), getVehiculo(conn), getTelefono(conn), getCorreo(conn));
        setServicio.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    Log.i("RESPUESTA", response.body());
                    onPagoFinish.PagoCompletado();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
                onPagoFinish.PagoError();
            }
        });
    }

    @Override
    public void validarFacturacion(ConexionSQLite conn, OnPagoFinish onPagoFinish) {
        if(ConsultarFacturacion(conn)){
            onPagoFinish.MostrarFacturacionAgregadaCorrectamente();
        }else{
            onPagoFinish.MostrarSinFactura();
        }
    }

    @Override
    public void AbrirDatosFacturacion(boolean estatusFacturacion, OnPagoFinish onPagoFinish) {
        if (estatusFacturacion){
            onPagoFinish.dialogEliminarDatosFacturacion();
        }else{
            onPagoFinish.dialogDatosFacturacion();
        }
    }

    @Override
    public void EliminarDatosFacturacion(ConexionSQLite conn, OnPagoFinish onPagoFinish) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            sqLiteDatabase.execSQL("DELETE FROM " + SQLiteTablas.TABLA_NOMBRE_FACTURA);
            sqLiteDatabase.close();
            onPagoFinish.MostrarSinFactura();
        } catch (Exception e) {
            Log.i("ERROR", e.getMessage());
        }
    }

    private boolean ConsultarFacturacion(ConexionSQLite conn){
        SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
        boolean isExist = false;
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteTablas.TABLA_NOMBRE_FACTURA,  null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                isExist = true;
            }
            cursor.close();
        }
        return isExist;
    }
    private String getVehiculo(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<VehiculoSQLite> listVehiculo = new ArrayList<VehiculoSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteTablas.TABLA_NOMBRE_VEHICULO, null);
            while(cursor.moveToNext()){
                VehiculoSQLite vehiculoSQLite = new VehiculoSQLite(
                        cursor.getInt(0),
                        cursor.getString(1));
                listVehiculo.add(vehiculoSQLite);
            }
            cursor.close();
            sqLiteDatabase.close();

            JSONArray vehiculo = new JSONArray();

            for (int i = 0; i < listVehiculo.size(); i++) {
                JSONObject row = new JSONObject();
                try {
                    row.put("id_vehiculo", listVehiculo.get(i).getId_vehiculo());
                    row.put("tipo_vehiculo", listVehiculo.get(i).getDescripcion_vehiculo());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                vehiculo.put(row);
            }
            Log.e("VEHICULO", vehiculo.toString());
            return vehiculo.toString();
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }
    private String getProductos(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            ArrayList<Productos> listProductos = new ArrayList<Productos>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteTablas.CAMPO_ID_PRODUCTO+", "+SQLiteTablas.CAMPO_NOMBRE_PRODUCTO+", "+SQLiteTablas.CAMPO_PRECIO_PRODUCTO+ ", " + SQLiteTablas.CAMPO_TIPO_PRODUCTO + " FROM " + SQLiteTablas.TABLA_NOMBRE_PRODUCTO, null);

            while(cursor.moveToNext()){
                Productos productosSQLite = new Productos(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
                listProductos.add(productosSQLite);
            }

            cursor.close();
            sqLiteDatabase.close();
            JSONArray productos = new JSONArray();

            for (int i = 0; i < listProductos.size(); i++) {
                JSONObject row = new JSONObject();
                try {
                    row.put("id_producto", listProductos.get(i).getId_producto().toString());
                    row.put("nombre_producto", listProductos.get(i).getNombre_producto());
                    row.put("precio_producto", listProductos.get(i).getPrecio_producto());
                    row.put("tipo_producto", listProductos.get(i).getTipo_producto());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                productos.put(row);
            }

            Log.e("PRODUCTO", productos.toString());
            return productos.toString();
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }
    private String getExtras(ConexionSQLite conn) {
        SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
        ArrayList<ExtrasSQLite> listProductos = new ArrayList<ExtrasSQLite>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteTablas.CAMPO_ID_EXTRA+", "+SQLiteTablas.CAMPO_NOMBRE_EXTRA+", "+SQLiteTablas.CAMPO_PRECIO_EXTRA+", "+SQLiteTablas.CAMPO_COMENTARIO_EXTRA+" " +
                "FROM " + SQLiteTablas.TABLA_NOMBRE_EXTRA, null);
        while(cursor.moveToNext()){
            ExtrasSQLite extrasSQLite = new ExtrasSQLite(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
            listProductos.add(extrasSQLite);
        }
        cursor.close();
        sqLiteDatabase.close();

        JSONArray extras = new JSONArray();

        for (int i = 0; i < listProductos.size(); i++) {
            JSONObject row = new JSONObject();
            try {
                row.put("id_extras", listProductos.get(i).getId_extra().toString());
                row.put("nombre_extras", listProductos.get(i).getNombre_extra());
                row.put("precio_extras", listProductos.get(i).getPrecio_extra().toString());
                row.put("descripcion_extras", listProductos.get(i).getDescripcion_extra());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            extras.put(row);
        }

        Log.e("EXTRAS", extras.toString());
        return extras.toString();
    }
    private String getCitas(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<CitaSQLite> listcita = new ArrayList<CitaSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT id_cita, horario_cita, fecha_cita FROM " + SQLiteTablas.TABLA_NOMBRE_CITA, null);
            while(cursor.moveToNext()){
                CitaSQLite cita = new CitaSQLite(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                listcita.add(cita);
            }
            cursor.close();
            sqLiteDatabase.close();

            JSONArray cita = new JSONArray();

            for (int i = 0; i < listcita.size(); i++) {
                JSONObject row = new JSONObject();
                try {
                    row.put("id_cita", listcita.get(i).getId_cita());
                    row.put("hora_cita", listcita.get(i).getHorarios());
                    row.put("fecha_cita", listcita.get(i).getFecha());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                cita.put(row);
            }

            Log.e("CITA", cita.toString());
            return cita.toString();
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }
    private String getUsuario(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<Usuario> listUsuario = new ArrayList<Usuario>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteTablas.TABLA_NOMBRE, null);
            while(cursor.moveToNext()){
                Usuario usuario = new Usuario(
                        cursor.getInt(0),
                        cursor.getString(1));
                listUsuario.add(usuario);
            }
            cursor.close();
            sqLiteDatabase.close();
            JSONArray usuario = new JSONArray();

            for (int i = 0; i < listUsuario.size(); i++) {
                JSONObject row = new JSONObject();
                try {
                    row.put("id_usuario", listUsuario.get(i).getId_usuario());
                    row.put("nombre_usuario", listUsuario.get(i).getNombre_usuario());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                usuario.put(row);
            }

            Log.e("USUARIO", usuario.toString());
            return usuario.toString();
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }
    private String getDireccion(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<DireccionSQLite> listDireccion = new ArrayList<DireccionSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteTablas.TABLA_NOMBRE_DIRECCION, null);
            while(cursor.moveToNext()){
                DireccionSQLite direccionSQLite = new DireccionSQLite(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                listDireccion.add(direccionSQLite);
            }
            cursor.close();
            sqLiteDatabase.close();

            JSONArray direccion = new JSONArray();

            for (int i = 0; i < listDireccion.size(); i++) {
                JSONObject row = new JSONObject();
                try {
                    row.put("id_direccion", listDireccion.get(i).getId_direccion());
                    row.put("direccion_usuario", listDireccion.get(i).getDireccion_usuario());
                    row.put("latitud_usuario", listDireccion.get(i).getLatitud_usuario());
                    row.put("longitud_usuario", listDireccion.get(i).getLongitud_usuario());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                direccion.put(row);
            }

            Log.e("DIRECCION", direccion.toString());
            return direccion.toString();
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }
    private String getTelefono(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<TelefonoSQLite> listTelefono = new ArrayList<TelefonoSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteTablas.CAMPO_ID_TELEFONO+", "+SQLiteTablas.CAMPO_TELEFONO_USUARIO+"" + " FROM " + SQLiteTablas.TABLA_NOMBRE_TELEFONO, null);
            while(cursor.moveToNext()){
                TelefonoSQLite telefonoSQLite = new TelefonoSQLite(
                        cursor.getInt(0),
                        cursor.getString(1));
                listTelefono.add(telefonoSQLite);
            }
            cursor.close();
            sqLiteDatabase.close();

            JSONArray telefono = new JSONArray();

            for (int i = 0; i < listTelefono.size(); i++) {
                JSONObject row = new JSONObject();
                try {
                    row.put("id_telefono", listTelefono.get(i).getId_telefono());
                    row.put("telefono", listTelefono.get(i).getTelefono());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                telefono.put(row);
            }

            Log.e("TELEFONO", telefono.toString());
            return telefono.toString();
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }
    private String getCorreo(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<CorreoSQLite> listCorreo = new ArrayList<CorreoSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteTablas.CAMPO_ID_CORREO+", "+SQLiteTablas.CAMPO_CORREO_USUARIO+"" + " FROM " + SQLiteTablas.TABLA_NOMBRE_CORREO, null);
            while(cursor.moveToNext()){
                CorreoSQLite correoSQLite = new CorreoSQLite(
                        cursor.getInt(0),
                        cursor.getString(1));
                listCorreo.add(correoSQLite);
            }
            cursor.close();
            sqLiteDatabase.close();

            JSONArray telefono = new JSONArray();

            for (int i = 0; i < listCorreo.size(); i++) {
                JSONObject row = new JSONObject();
                try {
                    row.put("id_correo", listCorreo.get(i).getId_corre());
                    row.put("correo", listCorreo.get(i).getCorreo());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                telefono.put(row);
            }

            Log.e("CORREO", telefono.toString());
            return telefono.toString();
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }
}
