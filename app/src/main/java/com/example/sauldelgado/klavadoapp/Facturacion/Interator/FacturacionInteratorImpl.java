package com.example.sauldelgado.klavadoapp.Facturacion.Interator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.CorreoSQLite;
import com.example.sauldelgado.klavadoapp.Facturacion.Model.FacturaSQLite;
import com.example.sauldelgado.klavadoapp.Facturacion.Presenter.OnFacturacionFinish;

import java.util.ArrayList;
import java.util.List;

public class FacturacionInteratorImpl implements FacturacionInterator {


    @Override
    public void addFacturacion(ConexionSQLite conn, String RFC, String razon_social, String tipo_pago, String correo, OnFacturacionFinish onFacturacionFinish) {
        if(RFC.isEmpty()){
            onFacturacionFinish.RFCvacio();
        }else if(razon_social.isEmpty()){
            onFacturacionFinish.RazonSocialVacio();
        }else if(correo.isEmpty()){
            onFacturacionFinish.CorreoVacio();
        }else{
            if (InsertarFacturacion(conn, RFC, razon_social, tipo_pago) && InsertarCorreo(conn, correo)){
                onFacturacionFinish.facturacionGuardadaCorrectamente();
            }else{
                onFacturacionFinish.facturacionError();
            }
        }
    }
    private boolean InsertarFacturacion(ConexionSQLite conn, String RFC, String razon_social, String tipo_pago) {
        try {
            SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
            sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_FACTURA, null, null);
            String insertFactura = "INSERT INTO "+SQLiteTablas.TABLA_NOMBRE_FACTURA +" ("+SQLiteTablas.CAMPO_RFC_FACTURA+", "+SQLiteTablas.CAMPO_RAZON_SOCIAL_FACTURA+", "+SQLiteTablas.CAMPO_TIPO_PAGO_FACTURA+") " +
                    "VALUES ('" + RFC + "', '" + razon_social + "', '" + tipo_pago + "')";
            sqLiteDatabase.execSQL(insertFactura);
            sqLiteDatabase.close();
            ConsultarTodoFacturacion(conn);
            return true;
        } catch (Exception e) {
            Log.i("ERROR!!!", "Ocurrio un error: InsertarVehiculo() " + e);
            return false;
        }
    }
    private void ConsultarTodoFacturacion(ConexionSQLite conn) {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<FacturaSQLite> insertFactura = new ArrayList<FacturaSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteTablas.TABLA_NOMBRE_FACTURA, null);
            while(cursor.moveToNext()){
                FacturaSQLite facturaSQLite = new FacturaSQLite(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));
                insertFactura.add(facturaSQLite);
            }
            cursor.close();
            sqLiteDatabase.close();
            Log.e("FACTURA", insertFactura.toString());
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
