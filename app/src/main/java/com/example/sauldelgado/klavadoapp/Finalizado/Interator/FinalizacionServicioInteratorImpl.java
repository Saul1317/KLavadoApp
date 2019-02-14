package com.example.sauldelgado.klavadoapp.Finalizado.Interator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sauldelgado.klavadoapp.Cita.Model.CitaSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.Productos;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.CorreoSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.DireccionSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.TelefonoSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.Usuario;
import com.example.sauldelgado.klavadoapp.Extras.Model.ExtrasSQLite;
import com.example.sauldelgado.klavadoapp.Finalizado.Presenter.OnFinalizacionServicioFinish;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FinalizacionServicioInteratorImpl implements FinalizacionServicioInterator {

    @Override
    public void CargarDatosDelServicio(ConexionSQLite conn, String fecha, String hora, OnFinalizacionServicioFinish onFinalizacionServicioFinish) {
        List<Usuario> usuarioList = getUsuarios(conn);
        List<DireccionSQLite> direccionSQLitesList  = getDireccion(conn);
        List<TelefonoSQLite> telefonoSQLiteList =  getTelefono(conn);
        List<CitaSQLite> citaSQLitesList = getCita(conn);
        List<Productos> productosList =  getProducto(conn);

        //String num_casa = "#" + direccionSQLitesList.get(0).getNumero_calle_usuario();
        String direccion = direccionSQLitesList.get(0).getDireccion_usuario();
        String fechaProgramada = "Programado el " + citaSQLitesList.get(0).getFecha() + " de " + citaSQLitesList.get(0).getHorarios();
        String precioServicio = "$"+String.valueOf(productosList.get(0).getPrecio_producto());
        double precio_sum = getPrecioProducto(conn) + getPrecioExtras(conn);
        String precio_total = "$" + String.valueOf(precio_sum);

        onFinalizacionServicioFinish.setInformacionDelServicio(fecha, hora, usuarioList.get(0).getNombre_usuario(), telefonoSQLiteList.get(0).getTelefono(), fechaProgramada,
                direccion, productosList.get(0).getNombre_producto(), precioServicio, precio_total);

        onFinalizacionServicioFinish.setCargarExtrasSeleccionados(getExtras(conn));
    }

    private List<Usuario> getUsuarios(ConexionSQLite conn){
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
            return listUsuario;
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }

    private List<DireccionSQLite> getDireccion(ConexionSQLite conn){
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

            return listDireccion;
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }

    private List<TelefonoSQLite> getTelefono(ConexionSQLite conn){
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
            return listTelefono;
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }

    private List<CorreoSQLite> getCorreo(ConexionSQLite conn){
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
            return listCorreo;
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }

    private List<CitaSQLite> getCita(ConexionSQLite conn){
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
            return listcita;
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }

    private List<Productos> getProducto(ConexionSQLite conn){
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<Productos> listProductos = new ArrayList<Productos>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteTablas.CAMPO_ID_PRODUCTO+", "+SQLiteTablas.CAMPO_NOMBRE_PRODUCTO+", "+SQLiteTablas.CAMPO_PRECIO_PRODUCTO+ ", " + SQLiteTablas.CAMPO_TIPO_PRODUCTO + " FROM " + SQLiteTablas.TABLA_NOMBRE_PRODUCTO, null);

            while(cursor.moveToNext()){
                Productos productosSQLite = new Productos(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
                listProductos.add(productosSQLite);
            }

            cursor.close();
            sqLiteDatabase.close();
            return listProductos;
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
            return null;
        }
    }

    private List<ExtrasSQLite> getExtras(ConexionSQLite conn) {
        SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
        List<ExtrasSQLite> listExtra = new ArrayList<ExtrasSQLite>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteTablas.CAMPO_ID_EXTRA+", "+SQLiteTablas.CAMPO_NOMBRE_EXTRA+", "+SQLiteTablas.CAMPO_PRECIO_EXTRA+", "+SQLiteTablas.CAMPO_COMENTARIO_EXTRA+" " +
                "FROM " + SQLiteTablas.TABLA_NOMBRE_EXTRA, null);
        while(cursor.moveToNext()){
            ExtrasSQLite extrasSQLite = new ExtrasSQLite(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
            listExtra.add(extrasSQLite);
        }
        cursor.close();
        sqLiteDatabase.close();
        return listExtra;
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
}
