package com.example.sauldelgado.klavadoapp.Data.Local.Sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLite extends SQLiteOpenHelper {

    public ConexionSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteTablas.CREAR_TABLA_USUARIO);
        db.execSQL(SQLiteTablas.CREAR_TABLA_VEHICULO);
        db.execSQL(SQLiteTablas.CREAR_TABLA_DIRECCION);
        db.execSQL(SQLiteTablas.CREAR_TABLA_FACTURA);
        db.execSQL(SQLiteTablas.CREAR_TABLA_TELEFONO);
        db.execSQL(SQLiteTablas.CREAR_TABLA_CORREO);
        db.execSQL(SQLiteTablas.CREAR_TABLA_PRODUCTO);
        db.execSQL(SQLiteTablas.CREAR_TABLA_EXTRA);
        db.execSQL(SQLiteTablas.CREAR_TABLA_CITA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS direccion");
        db.execSQL("DROP TABLE IF EXISTS telefono");
        db.execSQL("DROP TABLE IF EXISTS correo");
        db.execSQL("DROP TABLE IF EXISTS producto");
        db.execSQL("DROP TABLE IF EXISTS extra");
        db.execSQL("DROP TABLE IF EXISTS cita");
        db.execSQL("DROP TABLE IF EXISTS vehiculo");
        db.execSQL("DROP TABLE IF EXISTS factura");
        onCreate(db);
    }
}
