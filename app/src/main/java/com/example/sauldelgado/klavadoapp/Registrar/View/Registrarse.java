package com.example.sauldelgado.klavadoapp.Registrar.View;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Cita.Model.CitaSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.DireccionSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.TelefonoSQLite;
import com.example.sauldelgado.klavadoapp.DatosPersonales.Model.Usuario;
import com.example.sauldelgado.klavadoapp.Extras.Model.ExtrasSQLite;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.Productos;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;

import java.util.ArrayList;
import java.util.List;

public class Registrarse extends AppCompatActivity {
    EditText id, nombre, precio;
    Button consultar;
    ConexionSQLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        consultar = (Button) findViewById(R.id.consultar);
        conn = new ConexionSQLite(this, "bd_producto", null, SQLiteTablas.VERSION_BD);
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsultarTodoProducto();
                ConsultarTodoExtras();
                ConsultarTodoCita();
                ConsultarTodoUsuario();
                ConsultarTodoDireccion();
                ConsultarTodoTelefono();
            }
        });
    }

    private void ConsultarTodoProducto() {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<Productos> listProductos = new ArrayList<Productos>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT id_producto, nombre_producto, precio_producto FROM " + SQLiteTablas.TABLA_NOMBRE_PRODUCTO, null);
            while(cursor.moveToNext()){
                Productos productos = new Productos(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
                listProductos.add(productos);
            }
            cursor.close();
            sqLiteDatabase.close();
            Log.e("PRODUCTOS", listProductos.toString());
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
        }
    }
    private void ConsultarTodoExtras() {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<ExtrasSQLite> listProductos = new ArrayList<ExtrasSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteTablas.CAMPO_ID_EXTRA+", "+SQLiteTablas.CAMPO_NOMBRE_EXTRA+", "+SQLiteTablas.CAMPO_PRECIO_EXTRA+", "+SQLiteTablas.CAMPO_COMENTARIO_EXTRA+" FROM " + SQLiteTablas.TABLA_NOMBRE_EXTRA, null);
            while(cursor.moveToNext()){
                ExtrasSQLite extrasSQLite = new ExtrasSQLite(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2), cursor.getString(3));
                listProductos.add(extrasSQLite);
            }
            cursor.close();
            sqLiteDatabase.close();
            Log.e("EXTRAS", listProductos.toString());
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
        }
    }
    private void ConsultarTodoCita() {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<CitaSQLite> listProductos = new ArrayList<CitaSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT id_cita, horario_cita, fecha_cita FROM " + SQLiteTablas.TABLA_NOMBRE_CITA, null);
            while(cursor.moveToNext()){
                CitaSQLite cita = new CitaSQLite(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
                listProductos.add(cita);

            }
            cursor.close();
            sqLiteDatabase.close();
            Log.e("CITA", listProductos.toString());
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
        }
    }
    private void ConsultarTodoUsuario() {
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
    private void ConsultarTodoDireccion() {
        try{
            SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
            List<DireccionSQLite> listProductos = new ArrayList<DireccionSQLite>();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT "+SQLiteTablas.CAMPO_ID_DIRECCION+", "+SQLiteTablas.CAMPO_DIRECCION+" FROM " + SQLiteTablas.TABLA_NOMBRE_DIRECCION, null);
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
    private void ConsultarTodoTelefono() {
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

    private void ConsultarProductos() {
        SQLiteDatabase sqLiteDatabase = conn.getReadableDatabase();
        String[] parametros = {id.getText().toString()};
        String[] campos = {SQLiteTablas.CAMPO_ID_PRODUCTO,SQLiteTablas.CAMPO_NOMBRE_PRODUCTO, SQLiteTablas.CAMPO_PRECIO_PRODUCTO};

        try{
            Cursor cursor = sqLiteDatabase.query(SQLiteTablas.TABLA_NOMBRE_PRODUCTO, campos, SQLiteTablas.CAMPO_ID_PRODUCTO + "=?", parametros, null, null, null);
            if(cursor.moveToFirst()) {
                Log.i("ID", cursor.getString(0));
                Log.i("NOMBRE", cursor.getString(1));
                Log.i("PRECIO", cursor.getString(2));
            }
            cursor.close();
        }catch (Exception e){
            Log.i("ERROR!!!", "Ocurrio un error " + e);
        }
    }
    private void ActualizarProductos() {
        SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
        String[] parametros = {id.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(SQLiteTablas.CAMPO_ID_PRODUCTO, 1);
        values.put(SQLiteTablas.CAMPO_NOMBRE_PRODUCTO, id.getText().toString());
        values.put(SQLiteTablas.CAMPO_PRECIO_PRODUCTO, 20);
        sqLiteDatabase.update(SQLiteTablas.TABLA_NOMBRE_PRODUCTO, values,SQLiteTablas.CAMPO_ID_PRODUCTO + "=?", parametros);
        sqLiteDatabase.close();
    }
    private void EliminarProductos() {
        SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
        //String[] parametros = {id.getText().toString()};
        //sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_PRODUCTO, SQLiteTablas.CAMPO_ID_PRODUCTO + "=?", parametros);
        sqLiteDatabase.delete(SQLiteTablas.TABLA_NOMBRE_PRODUCTO, null, null);
        sqLiteDatabase.close();
    }
    private void RegistrarProducto() {
        SQLiteDatabase sqLiteDatabase = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(SQLiteTablas.CAMPO_ID_PRODUCTO, id.getText().toString());
        values.put(SQLiteTablas.CAMPO_NOMBRE_PRODUCTO, nombre.getText().toString());
        values.put(SQLiteTablas.CAMPO_PRECIO_PRODUCTO, precio.getText().toString());
        Long idResultado = sqLiteDatabase.insert(SQLiteTablas.TABLA_NOMBRE_PRODUCTO, SQLiteTablas.CAMPO_ID_PRODUCTO, values);
        Toast.makeText(this, "Respuesta es "+idResultado, Toast.LENGTH_SHORT).show();
        sqLiteDatabase.close();
    }

}
