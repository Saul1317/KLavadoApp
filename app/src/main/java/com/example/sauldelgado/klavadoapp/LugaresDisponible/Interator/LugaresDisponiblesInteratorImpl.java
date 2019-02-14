package com.example.sauldelgado.klavadoapp.LugaresDisponible.Interator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.Data.Remoto.Retrofit.NetworkAdapter;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.TelefonoSQLite;
import com.example.sauldelgado.klavadoapp.LugaresDisponible.Model.CiudadesDisponibles;
import com.example.sauldelgado.klavadoapp.LugaresDisponible.Presenter.OnLugaresDisponiblesFinish;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LugaresDisponiblesInteratorImpl implements  LugaresDisponiblesInterator {

    @Override
    public void getCiudadesDisponibles(final OnLugaresDisponiblesFinish onLugaresDisponiblesFinish) {
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
                    onLugaresDisponiblesFinish.setCiudadesDisponibles(ciudades);
                }
            }

            @Override
            public void onFailure(Call<List<CiudadesDisponibles>> call, Throwable t) {
                Log.e("CIUDADES DISPONIBLES", t.getMessage());
            }
        });
    }

    @Override
    public void addTelefono(ConexionSQLite conn, String telefono, OnLugaresDisponiblesFinish onLugaresDisponiblesFinish) {
        if(!telefono.isEmpty()) {
            if(!(telefono.length() < 10)){
                if (InsertarTelefono(conn, telefono)) {
                    onLugaresDisponiblesFinish.addTelefonoSuccessful();
                }else{
                   onLugaresDisponiblesFinish.addTelefonoUnsuccessful();
                }
            }else{
                onLugaresDisponiblesFinish.telefonoIncompleto();
            }
        }else{
            onLugaresDisponiblesFinish.telefonoVacio();
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
