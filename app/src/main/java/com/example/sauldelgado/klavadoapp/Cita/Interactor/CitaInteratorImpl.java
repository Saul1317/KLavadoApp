package com.example.sauldelgado.klavadoapp.Cita.Interactor;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sauldelgado.klavadoapp.Cita.Model.CitaSQLite;
import com.example.sauldelgado.klavadoapp.Cita.Model.Horarios;
import com.example.sauldelgado.klavadoapp.Cita.Presenter.OnCitaFinish;
import com.example.sauldelgado.klavadoapp.Data.Remoto.Retrofit.NetworkAdapter;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.VehiculoSQLite;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitaInteratorImpl implements CitaInteractor {

    @Override
    public void ConsultarHorariosDisponibles(String fecha, final OnCitaFinish onCitaFinish) {
        Call<List<Horarios>> minivan = NetworkAdapter.getApiService().getHorariosDisponibles(
                "getCitaDisponible.php", fecha);
        minivan.enqueue(new Callback<List<Horarios>>() {
            @Override
            public void onResponse(Call<List<Horarios>> call, Response<List<Horarios>> response) {
                if (response.isSuccessful()){
                    List<Horarios> horariosList = response.body();
                    ArrayList<String> list = new ArrayList<String>();
                    if (horariosList.isEmpty()){
                        list.add("No hay horas disponibles");
                    }else{
                        for (int i = 0 ; i< horariosList.size(); i++){
                            list.add(horariosList.get(i).getHorarioDisponible());
                        }
                    }
                    onCitaFinish.MostrarHorariosDisponibles(list);
                }
            }

            @Override
            public void onFailure(Call<List<Horarios>> call, Throwable t) {
                onCitaFinish.showDialogConectionError();
            }
        });
    }

    @Override
    public void agregarInformacion(ConexionSQLite conn, String fecha, String horario, String descripcion_vehiculo, OnCitaFinish onCitaFinish) {
        if(horario.equals("No hay horas disponibles")){
            onCitaFinish.horarioError();
        }else {
            if (InsertarVehiculo(conn, descripcion_vehiculo, onCitaFinish) && agregarHorarioAlCarrito(conn, fecha, horario, onCitaFinish)) {
                onCitaFinish.addToCartSucessful();
            } else {
                Log.e("ERROR", "No se almaceno nada");
            }
        }
    }

    public boolean agregarHorarioAlCarrito(ConexionSQLite conn, String fecha, String horario, OnCitaFinish onCitaFinish) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_CITA, null, null);
            String insertProducto = "INSERT INTO "+ SQLiteTablas.TABLA_NOMBRE_CITA +
                    "(" + SQLiteTablas.CAMPO_FECHA_CITA + ", " + SQLiteTablas.CAMPO_HORARIO_CITA + ") " +
                    "VALUES ('" + fecha + "','" + horario + "')";
            sqLiteDatabase.execSQL(insertProducto);
            sqLiteDatabase.close();
            ConsultarCitas(conn);
            return true;
        } catch (Exception e) {
            Log.i("ERROR", e.getMessage());
            return false;
        }
    }

    private void ConsultarCitas(ConexionSQLite conn) {
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

        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
        }
    }


    private boolean InsertarVehiculo(ConexionSQLite conn, String descripcion_vehiculo, OnCitaFinish onCitaFinish) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_VEHICULO, null, null);
            String insertVehiculo = "INSERT INTO "+SQLiteTablas.TABLA_NOMBRE_VEHICULO +" (descripcion_vehiculo) " +
                    "VALUES ('" + descripcion_vehiculo + "')";
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
}
