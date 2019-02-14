package com.example.sauldelgado.klavadoapp.DatosPersonales.Interactor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.CorreoSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.TelefonoSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.Usuario;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.VehiculoSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Presenter.OnDatosPersonalesFinish;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;

import java.util.ArrayList;
import java.util.List;

public class DatosPersonalesInteractorImpl implements DatosPersonalesInteractor {

    @Override
    public void addDatosPersonales(ConexionSQLite conn, String nombre, String correo, String tipo_vehiculo, String modelo_vehiculo, String color_vehiculo, String descripcion, OnDatosPersonalesFinish onDatosPersonalesFinish) {
        if (nombre.isEmpty()){
            onDatosPersonalesFinish.nombreError();
        }
        else if (correo.isEmpty()){
            onDatosPersonalesFinish.correoError();
        }else{
            if(InsertarUsuario(conn, nombre, descripcion) && InsertarCorreo(conn, correo) && InsertarVehiculo(conn, tipo_vehiculo, modelo_vehiculo, color_vehiculo)){
                onDatosPersonalesFinish.addSuccessful();
            }else{
                onDatosPersonalesFinish.addUnsuccessful();
            }
        }
    }

    private boolean InsertarUsuario(ConexionSQLite conn, String nombre, String descripcion) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE, null, null);
            String insertUsuario = "INSERT INTO "+SQLiteTablas.TABLA_NOMBRE+" (nombre_usuario, comentario_usuario) " +
                    "VALUES ('" + nombre + "', '" + descripcion + "')";
            sqLiteDatabase.execSQL(insertUsuario);
            sqLiteDatabase.close();
            ConsultarTodoUsuario(conn);
            return true;
        } catch (Exception e) {
            Log.i("ERROR!!!", "Ocurrio un error: InsertarUsuario() " + e);
            return false;
        }
    }

    private void ConsultarTodoUsuario(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<Usuario> listProductos = new ArrayList<Usuario>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteTablas.TABLA_NOMBRE, null);
            while(cursor.moveToNext()){
                Usuario usuario = new Usuario(
                        cursor.getInt(0),
                        cursor.getString(1));
                listProductos.add(usuario);
            }
            cursor.close();
            sqLiteDatabase.close();
            Log.e("USUARIO", listProductos.toString());
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
        }
    }

    private boolean InsertarVehiculo(ConexionSQLite conn, String tipo_vehiculo, String modelo_vehiculo, String color_vehiculo ) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_VEHICULO, null, null);
            String insertVehiculo = "INSERT INTO "+SQLiteTablas.TABLA_NOMBRE_VEHICULO +" (tipo_vehiculo, modelo_vehiculo, color_vehiculo) " +
                    "VALUES ('" + tipo_vehiculo + "', '" + modelo_vehiculo + "', '" + color_vehiculo + "')";
            sqLiteDatabase.execSQL(insertVehiculo);
            sqLiteDatabase.close();
            ConsultarTodoVehiculo(conn);
            return true;
        } catch (Exception e) {
            Log.i("ERROR!!!", "Ocurrio un error: InsertarVehiculo() " + e);
            return false;
        }
    }

    private void ConsultarTodoVehiculo(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<VehiculoSQLite> insertTelefono = new ArrayList<VehiculoSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteTablas.TABLA_NOMBRE_VEHICULO, null);
            while(cursor.moveToNext()){
                VehiculoSQLite vehiculoSQLite = new VehiculoSQLite(
                        cursor.getInt(0),
                        cursor.getString(1));
                insertTelefono.add(vehiculoSQLite);
            }
            cursor.close();
            sqLiteDatabase.close();
            Log.e("VEHICULO", insertTelefono.toString());
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
        }
    }

    private boolean InsertarCorreo(ConexionSQLite conn, String correo) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_CORREO, null, null);
            String insertCorreo = "INSERT INTO "+SQLiteTablas.TABLA_NOMBRE_CORREO+" (correo_usuario) " +
                    "VALUES ('" + correo + "')";
            sqLiteDatabase.execSQL(insertCorreo);
            sqLiteDatabase.close();
            ConsultarTodoCorreo(conn);
            return true;
        } catch (Exception e) {
            Log.i("ERROR!!!", "Ocurrio un error: InsertarCorreo() " + e);
            return false;
        }
    }

    private void ConsultarTodoCorreo(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<CorreoSQLite> correoSQLiteArrayList = new ArrayList<CorreoSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteTablas.CAMPO_ID_CORREO+", "+SQLiteTablas.CAMPO_CORREO_USUARIO+" FROM " + SQLiteTablas.TABLA_NOMBRE_CORREO, null);
            while(cursor.moveToNext()){
                CorreoSQLite correoSQLite = new CorreoSQLite(
                        cursor.getInt(0),
                        cursor.getString(1));
                correoSQLiteArrayList.add(correoSQLite);
            }
            cursor.close();
            sqLiteDatabase.close();
            Log.e("CORREO", correoSQLiteArrayList.toString());
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
        }
    }
}
