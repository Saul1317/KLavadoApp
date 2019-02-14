package com.example.sauldelgado.klavadoapp.Menu.Interactor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.Data.Remoto.Retrofit.NetworkAdapter;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.TelefonoSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.Usuario;
import com.example.sauldelgado.klavadoapp.LugaresDisponible.Model.CiudadesDisponibles;
import com.example.sauldelgado.klavadoapp.Menu.Presenter.OnMenuPrincipalFinish;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuInteractorImpl implements MenuInteractor {

    @Override
    public void validarEstatusTerminos(boolean estatus, OnMenuPrincipalFinish onMenuPrincipalFinish) {
        if(estatus){
            onMenuPrincipalFinish.terminosAceptados();
        }else{
            onMenuPrincipalFinish.terminosRechazados();
        }
    }

    @Override
    public void getCiudadesDisponibles(final OnMenuPrincipalFinish onMenuPrincipalFinish) {
        Call<List<CiudadesDisponibles>> lugaresDisponibles = NetworkAdapter.getApiService().getLugaresDisponibles(
                "getLugaresDisponibles.php");
        lugaresDisponibles.enqueue(new Callback<List<CiudadesDisponibles>>() {
            @Override
            public void onResponse(Call<List<CiudadesDisponibles>> call, Response<List<CiudadesDisponibles>> response) {
                if(response.isSuccessful()){
                    List<CiudadesDisponibles> ciudadesDisponibles = response.body();
                    ArrayList<String> ciudades = new ArrayList<String>();
                    for (int contador = 0; contador < ciudadesDisponibles.size(); contador++) {
                        ciudades.add(ciudadesDisponibles.get(contador).getCiudad());
                    }
                    onMenuPrincipalFinish.setCiudadesDisponibles(ciudades);
                }
            }

            @Override
            public void onFailure(Call<List<CiudadesDisponibles>> call, Throwable t) {
                Log.e("CIUDADES DISPONIBLES", t.getMessage());
            }
        });
    }

    @Override
    public void addInformation(ConexionSQLite conn, String nombre, String telefono, OnMenuPrincipalFinish onMenuPrincipalFinish) {
       if(nombre.isEmpty()){
           onMenuPrincipalFinish.NombreVacio();
       }else if(telefono.isEmpty()){
           onMenuPrincipalFinish.NumeroVacio();
       }else if(telefono.length()<10){
           onMenuPrincipalFinish.faltanNumeros();
       }else{
           if (InsertarUsuario(conn, nombre) && InsertarTelefono(conn, telefono)){
               onMenuPrincipalFinish.GuardadoCorrectamente();
           }else{
               Log.e("ERROR","Ocurrió un error");
           }
       }
    }

    private boolean InsertarUsuario(ConexionSQLite conn, String nombre) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE, null, null);
            String insertUsuario = "INSERT INTO "+SQLiteTablas.TABLA_NOMBRE+" (nombre_usuario) " +
                    "VALUES ('" + nombre + "')";
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

    private boolean InsertarTelefono(ConexionSQLite conn, String telefono) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_TELEFONO, null, null);
            String insertTelefono = "INSERT INTO "+SQLiteTablas.TABLA_NOMBRE_TELEFONO+" (telefono_usuario) " +
                    "VALUES ('" + telefono + "')";
            sqLiteDatabase.execSQL(insertTelefono);
            sqLiteDatabase.close();
            ConsultarTodoTelefono(conn);
            return true;
        } catch (Exception e) {
            Log.i("ERROR!!!", "Ocurrio un error: InsertarTelefono() " + e);
            return false;
        }
    }

    private void ConsultarTodoTelefono(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<TelefonoSQLite> listProductos = new ArrayList<TelefonoSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteTablas.CAMPO_ID_TELEFONO+", "+SQLiteTablas.CAMPO_TELEFONO_USUARIO+" FROM " + SQLiteTablas.TABLA_NOMBRE_TELEFONO, null);
            while(cursor.moveToNext()){
                TelefonoSQLite telefonoSQLite = new TelefonoSQLite(
                        cursor.getInt(0),
                        cursor.getString(1));
                listProductos.add(telefonoSQLite);
            }
            cursor.close();
            sqLiteDatabase.close();
            Log.e("TELEFONO", listProductos.toString());
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
        }
    }
}
