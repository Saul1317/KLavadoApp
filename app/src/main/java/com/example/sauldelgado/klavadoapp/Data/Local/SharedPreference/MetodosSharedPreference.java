package com.example.sauldelgado.klavadoapp.Data.Local.SharedPreference;

import android.content.SharedPreferences;

public class MetodosSharedPreference {

    public static void GuardarTipoVehiculoPref(SharedPreferences prs, String vehiculo){
        SharedPreferences.Editor editor = prs.edit();
        editor.putString("tipo_vehiculo", vehiculo);
        editor.apply();
    }

    public static String ObtenerTipoVehiculoPref(SharedPreferences prs){
        return prs.getString("tipo_vehiculo",null);
    }
}
