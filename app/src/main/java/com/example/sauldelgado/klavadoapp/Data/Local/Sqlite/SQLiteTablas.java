package com.example.sauldelgado.klavadoapp.Data.Local.Sqlite;

public class SQLiteTablas {

    public static final int VERSION_BD = 13;

    /*------TABLA USUARIO-----*/
    public static final String TABLA_NOMBRE = "usuario";
    public static final String CAMPO_ID_USUARIO = "id_usuario";
    public static final String CAMPO_NOMBRE_USUARIO = "nombre_usuario";
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_NOMBRE+" " +
            "("+CAMPO_ID_USUARIO+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+CAMPO_NOMBRE_USUARIO+" TEXT)";

    /*------TABLA VEHICULO-------*/
    public static final String TABLA_NOMBRE_VEHICULO = "vehiculo";
    public static final String CAMPO_ID_VEHICULO = "id_vehiculo";
    public static final String CAMPO_DESCRIPCION_VEHICULO = "descripcion_vehiculo";

    public static final String CREAR_TABLA_VEHICULO = "CREATE TABLE vehiculo(id_vehiculo INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, descripcion_vehiculo TEXT)";

    /*------TABLA DIRECCION-----*/
    public static final String TABLA_NOMBRE_DIRECCION = "direccion";
    public static final String CAMPO_ID_DIRECCION = "id_direccion";
    public static final String CAMPO_DIRECCION = "direccion_usuario";
    public static final String CAMPO_LATITUD_DIRECCION = "latitud_usuario";
    public static final String CAMPO_LONGITUD_DIRECCION = "longitud_usuario";

    public static final String CREAR_TABLA_DIRECCION = "CREATE TABLE "+ TABLA_NOMBRE_DIRECCION + "("+CAMPO_ID_DIRECCION+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+CAMPO_DIRECCION+" TEXT, "+CAMPO_LATITUD_DIRECCION+" TEXT, "+CAMPO_LONGITUD_DIRECCION+" TEXT)";

    /*------TABLA TELEFONO-----*/
    public static final String TABLA_NOMBRE_TELEFONO = "telefono";
    public static final String CAMPO_ID_TELEFONO = "id_telefono";
    public static final String CAMPO_TELEFONO_USUARIO = "telefono_usuario";

    public static final String CREAR_TABLA_TELEFONO = "CREATE TABLE "+TABLA_NOMBRE_TELEFONO+" " +
            "("+CAMPO_ID_TELEFONO+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+CAMPO_TELEFONO_USUARIO+" TEXT)";

    /*------TABLA CORREO-----*/
    public static final String TABLA_NOMBRE_CORREO = "correo";
    public static final String CAMPO_ID_CORREO = "id_correo";
    public static final String CAMPO_CORREO_USUARIO = "correo_usuario";

    public static final String CREAR_TABLA_CORREO = "CREATE TABLE "+TABLA_NOMBRE_CORREO+" " +
            "(" + CAMPO_ID_CORREO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + CAMPO_CORREO_USUARIO + " TEXT)";

    /*------TABLA CITAS-----*/
    public static final String TABLA_NOMBRE_CITA = "cita";
    public static final String CAMPO_ID_CITA = "id_cita";
    public static final String CAMPO_HORARIO_CITA = "horario_cita";
    public static final String CAMPO_FECHA_CITA = "fecha_cita";

    public static final String CREAR_TABLA_CITA = "CREATE TABLE " + TABLA_NOMBRE_CITA +
            "("+CAMPO_ID_CITA+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + CAMPO_HORARIO_CITA + " TEXT, "+ CAMPO_FECHA_CITA +" TEXT)";

    /*------TABLA FACTURA-----*/
    public static final String TABLA_NOMBRE_FACTURA = "factura";
    public static final String CAMPO_ID_FACTURA = "id_factura";
    public static final String CAMPO_RFC_FACTURA = "rfc_factura";
    public static final String CAMPO_RAZON_SOCIAL_FACTURA = "razon_social_factura";
    public static final String CAMPO_TIPO_PAGO_FACTURA = "tipo_pago_factura";

    public static final String CREAR_TABLA_FACTURA = "CREATE TABLE " + TABLA_NOMBRE_FACTURA +
            "("+CAMPO_ID_FACTURA+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + CAMPO_RFC_FACTURA + " TEXT, "+ CAMPO_RAZON_SOCIAL_FACTURA +" TEXT, "+ CAMPO_TIPO_PAGO_FACTURA +" TEXT)";

    /*------TABLA PRODUCTOS-----*/
    public static final String TABLA_NOMBRE_PRODUCTO = "producto";
    public static final String CAMPO_ID_PRODUCTO = "id_producto";
    public static final String CAMPO_NOMBRE_PRODUCTO = "nombre_producto";
    public static final String CAMPO_PRECIO_PRODUCTO = "precio_producto";
    public static final String CAMPO_TIPO_PRODUCTO = "tipo_producto";

    public static final String CREAR_TABLA_PRODUCTO = "CREATE TABLE " + TABLA_NOMBRE_PRODUCTO +
            "("+CAMPO_ID_PRODUCTO+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            ""+CAMPO_NOMBRE_PRODUCTO+" TEXT, " +
            ""+CAMPO_PRECIO_PRODUCTO+" TEXT, " +
            ""+ CAMPO_TIPO_PRODUCTO +" TEXT)";

    /*------TABLA EXTRAS-----*/
    public static final String TABLA_NOMBRE_EXTRA = "extra";
    public static final String CAMPO_ID_EXTRA = "id_extra";
    public static final String CAMPO_NOMBRE_EXTRA = "nombre_extra";
    public static final String CAMPO_PRECIO_EXTRA = "precio_extra";
    public static final String CAMPO_COMENTARIO_EXTRA = "comentario_extra";

    public static final String CREAR_TABLA_EXTRA = "CREATE TABLE " + TABLA_NOMBRE_EXTRA +
            "("+CAMPO_ID_EXTRA+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            ""+CAMPO_NOMBRE_EXTRA+" TEXT, " +
            ""+CAMPO_PRECIO_EXTRA+" TEXT, " +
            ""+CAMPO_COMENTARIO_EXTRA+" TEXT)";
}

