package com.example.sauldelgado.klavadoapp.Ubicacion.Interator;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.DireccionSQLite;
import com.example.sauldelgado.klavadoapp.Ubicacion.Presenter.OnUbicacionInteratorFinish;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UbicacionInteratorImpl implements  UbicacionInterator {
    @Override
    public void getUbicacionActualGPS(Context context, final OnUbicacionInteratorFinish onUbicacionInteratorFinish) {
        final LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if(location != null){
                    Log.e("LOCATION", "localizacion obtenida");
                    onUbicacionInteratorFinish.setLocacionActual(location);
                }else{
                    Log.e("LOCATION", "localizacion fallida");
                    onUbicacionInteratorFinish.setLocacionActualError();
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        }, null);
    }

    @Override
    public void updateLocacionActual(Location location, OnUbicacionInteratorFinish onUbicacionInteratorFinish) {
        if (location != null) {
            double latitud = location.getLatitude();
            double longitud = location.getLongitude();
            onUbicacionInteratorFinish.updateLocacionActual(latitud, longitud);
        }else{
            Log.e("ERROR Update Location", "Ocurrió un error en updateLocacionActual()");
        }
    }

    @Override
    public void getNombreDireccion(Context context, Geocoder geocoder, LatLng latLng, OnUbicacionInteratorFinish onUbicacionInteratorFinish) {
        if(latLng != null){
            geocoder = new Geocoder(context, Locale.getDefault());
            try{
                List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                if (!addresses.isEmpty()){
                    String direccion = addresses.get(0).getAddressLine(0);
                    double latitud = addresses.get(0).getLatitude();
                    double longitud = addresses.get(0).getLongitude();
                    onUbicacionInteratorFinish.setNombreDireccion(direccion);
                }
            }catch (IOException e){
                Log.e("ERROR DIRECCION", e.getMessage());
            }
        }
    }

    @Override
    public void buscarDireccion(Context context, String nombre_direccion, Geocoder geocoder, OnUbicacionInteratorFinish onUbicacionInteratorFinish) {
        if (!nombre_direccion.isEmpty()){
            geocoder = new Geocoder(context, Locale.getDefault());
            try
            {
                List<Address> addresses = geocoder.getFromLocationName(nombre_direccion, 5);
                if (addresses.size() > 0)
                {
                    Double lat = (double) (addresses.get(0).getLatitude());
                    Double lon = (double) (addresses.get(0).getLongitude());
                    onUbicacionInteratorFinish.updateLocacionActual(lat, lon);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }else{
            onUbicacionInteratorFinish.buscadorVacio();
        }
    }

    @Override
    public void guardarDireccion(Context context, ConexionSQLite conn, Geocoder geocoder, LatLng latLng, OnUbicacionInteratorFinish onUbicacionInteratorFinish) {
        if(latLng != null){
            geocoder = new Geocoder(context, Locale.getDefault());
            try{
                List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                if (!addresses.isEmpty()){
                    String direccion = addresses.get(0).getAddressLine(0);
                    String latitud = String.valueOf(addresses.get(0).getLatitude());
                    String longitud = String.valueOf(addresses.get(0).getLongitude());
                    if(InsertarDireccion(conn, direccion, latitud, longitud)){
                        onUbicacionInteratorFinish.insertSuccessful();
                    }else{
                        onUbicacionInteratorFinish.insertUnsuccessful();
                    }
                }
            }catch (IOException e){
                Log.e("ERROR DIRECCION", e.getMessage());
            }
        }
    }

    public boolean InsertarDireccion(ConexionSQLite conn, String direccion, String latitud, String longitud) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_DIRECCION, null, null);
            String insertProducto = "INSERT INTO "+SQLiteTablas.TABLA_NOMBRE_DIRECCION+" (" + SQLiteTablas.CAMPO_DIRECCION + ", " + SQLiteTablas.CAMPO_LATITUD_DIRECCION + ", " + SQLiteTablas.CAMPO_LONGITUD_DIRECCION + ") " +
                    "VALUES ('" + direccion + "', '" + latitud + "', '" + longitud + "')";
            sqLiteDatabase.execSQL(insertProducto);
            sqLiteDatabase.close();
            ConsultarTodoDireccion(conn);
            return true;
        } catch (Exception e) {
            Log.e("ERROR SQL DIRECCION", "Excepción en InsertarDireccion() " + e.getMessage());
            return false;
        }
    }

    private void ConsultarTodoDireccion(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<DireccionSQLite> listProductos = new ArrayList<DireccionSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteTablas.TABLA_NOMBRE_DIRECCION, null);
            while(cursor.moveToNext()){
                DireccionSQLite usuario = new DireccionSQLite(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                listProductos.add(usuario);
            }
            cursor.close();
            sqLiteDatabase.close();
            Log.e("DIRRECCION", listProductos.toString());
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
        }
    }
}
