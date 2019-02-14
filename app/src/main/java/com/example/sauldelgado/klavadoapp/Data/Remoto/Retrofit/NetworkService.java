package com.example.sauldelgado.klavadoapp.Data.Remoto.Retrofit;

import com.example.sauldelgado.klavadoapp.Cita.Model.Horarios;
import com.example.sauldelgado.klavadoapp.Extras.Model.Extras_Ofertas;
import com.example.sauldelgado.klavadoapp.Extras.Model.Producto_Extras;
import com.example.sauldelgado.klavadoapp.LugaresDisponible.Model.CiudadesDisponibles;
import com.example.sauldelgado.klavadoapp.TipoLavado.Model.Producto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface NetworkService {

    @FormUrlEncoded
    @POST
    Call<List<Producto>> getProductos(@Url String url,  @Field("vehiculo") String tipo_vehiculo, @Field("tipoServicio")String tipo_lavado);

    @FormUrlEncoded
    @POST
    Call<List<Producto_Extras>> getExtras(@Url String url, @Field("vehiculo") String tipo_vehiculo);

    @POST
    Call<List<CiudadesDisponibles>> getLugaresDisponibles(@Url String url);

    @FormUrlEncoded
    @POST
    Call<List<Extras_Ofertas>> getOfertas(@Url String url, @Field("vehiculo") String tipo_vehiculo);

    @FormUrlEncoded
    @POST
    Call<List<Horarios>> getHorariosDisponibles(@Url String url, @Field("fecha") String fecha_cita);

    @FormUrlEncoded
    @POST
    Call<String> setServicio(@Url String url,
                             @Field("productoJSON") String listProductos,
                             @Field("extraJSON") String listExtras,
                             @Field("citasJSON") String listCitas,
                             @Field("usuarioJSON") String listUsuario,
                             @Field("direccionJSON") String listDireccion,
                             @Field("vehiculoJSON") String listVehiculo,
                             @Field("telefonoJSON") String listTelefono,
                             @Field("correoJSON") String listCorreo);

    //@POST
    //Call<PagoRespuesta_retrofit> setServicio(@Url String url, @Body Productos productos);
}
