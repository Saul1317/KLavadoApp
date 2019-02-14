package com.example.sauldelgado.klavadoapp.Ubicacion.View;

import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.ConexionSQLite;
import com.example.sauldelgado.klavadoapp.Data.Local.Sqlite.SQLiteTablas;
import com.example.sauldelgado.klavadoapp.DatosPersonales.View.DatosPersonales;
import com.example.sauldelgado.klavadoapp.Facturacion.View.FacturacionViewImpl;
import com.example.sauldelgado.klavadoapp.Pago.View.Activity.Pago;
import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Ubicacion.Adapter.PlaceAutocompleteAdapter;
import com.example.sauldelgado.klavadoapp.Ubicacion.Presenter.UbicacionPresenter;
import com.example.sauldelgado.klavadoapp.Ubicacion.Presenter.UbicacionPresenterImpl;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Ubicacion extends FragmentActivity implements  UbicacionView, OnMapReadyCallback,
        GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraIdleListener, View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private Marker markerPrueba;
    private double latitud = 0.0;
    private double longitud = 0.0;
    private Geocoder geocoder;
    private LatLng coordenadasAcual;
    private CameraUpdate miUbicacion;
    private TextView txt_direccion_GPS;
    private ImageView fab_getLocalizacion, fab_search_location, btn_clear_texto, btn_back_ubicacion;
    private FloatingActionButton fab_continuar;
    private AutoCompleteTextView edt_search_direccion;
    private PlaceAutocompleteAdapter placeAutocompleteAdapter;
    private GoogleApiClient googleApiClient;
    private LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(new LatLng(-40, 168), new LatLng(71, 136));
    private ProgressBar progress_bar_ubicacion;
    private UbicacionPresenter ubicacionPresenter;
    private ConexionSQLite conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubicacion);
        LoadViews();
    }

    @Override
    public void LoadViews() {
        ubicacionPresenter = new UbicacionPresenterImpl(this);
        conn = new ConexionSQLite(this, "bd_producto", null, SQLiteTablas.VERSION_BD);
        txt_direccion_GPS = (TextView) findViewById(R.id.txt_direccion_GPS);

        AutocompleteFilter filter = new AutocompleteFilter.Builder().setCountry("MX").build();
        edt_search_direccion = (AutoCompleteTextView) findViewById(R.id.edt_search_direccion);
        googleApiClient = new GoogleApiClient.Builder(this).addApi(Places.GEO_DATA_API).addApi(Places.PLACE_DETECTION_API).enableAutoManage(this, this).build();
        placeAutocompleteAdapter = new PlaceAutocompleteAdapter(this, googleApiClient, LAT_LNG_BOUNDS ,filter);
        edt_search_direccion.setAdapter(placeAutocompleteAdapter);

        fab_getLocalizacion = (ImageView) findViewById(R.id.fab_getLocalizacion);
        fab_getLocalizacion.setOnClickListener(this);

        fab_continuar = (FloatingActionButton) findViewById(R.id.fab_continuar);
        fab_continuar.setOnClickListener(this);

        fab_search_location = (ImageView) findViewById(R.id.fab_search_location);
        fab_search_location.setOnClickListener(this);

        btn_clear_texto = (ImageView) findViewById(R.id.btn_clear_texto);
        btn_clear_texto.setOnClickListener(this);

        progress_bar_ubicacion = (ProgressBar) findViewById(R.id.progress_bar_ubicacion);
        btn_back_ubicacion = (ImageView) findViewById(R.id.btn_back_ubicacion);
        btn_back_ubicacion.setOnClickListener(this);
        TecladoActionSearch();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        edt_search_direccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0){
                    btn_clear_texto.setVisibility(View.VISIBLE);
                }else{
                    btn_clear_texto.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    @Override
    public void LocalizacionSuccessFul(Location location) {
        Log.i("LOCALIZACION ACTUAL", "LATITUD " + String.valueOf(location.getLatitude()) + " LONGITUD " + String.valueOf(location.getLongitude()));
        ubicacionPresenter.updateLocacionActual(location);
    }

    @Override
    public void LocalizacionUnsuccessFul() {
        Log.e("LOCALIZACION ERROR", "Al pareccer no sacamos ninguna localización");
        Toast.makeText(this, "Al parecer ocurrió un problema con el gps", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addMarcaMap(double latitud, double longitud) {
        coordenadasAcual = new LatLng(latitud, longitud);
        miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadasAcual, 18);
        if (markerPrueba != null) markerPrueba.remove();
        markerPrueba = mMap.addMarker(new MarkerOptions().position(coordenadasAcual).icon(BitmapDescriptorFactory.fromResource(R.drawable.lavado911_map_marker)).draggable(false));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.animateCamera(miUbicacion, 3000, null);
    }

    @Override
    public void setNombreDireccion(String nombre_direccion) {
        txt_direccion_GPS.setText(nombre_direccion);
    }

    @Override
    public void TecladoActionSearch() {
        edt_search_direccion.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == keyEvent.ACTION_DOWN || keyEvent.getAction() == keyEvent.KEYCODE_ENTER){
                    ubicacionPresenter.buscarDireccion(Ubicacion.this, edt_search_direccion.getText().toString(), geocoder);
                }
                return false;
            }
        });
    }

    @Override
    public void buscadorVacio() {
        Toast.makeText(this, "Ingresa una dirección", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void abrirSiguienteActivity() {
        Intent intentPago = new Intent(Ubicacion.this, Pago.class);
        startActivity(intentPago);
    }

    @Override
    public void insertErrorSQL() {
        Toast.makeText(this, "Ocurrió un error al guardar la dirección", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progress_bar_ubicacion.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progress_bar_ubicacion.setVisibility(View.GONE);
    }

    @Override
    public void bloquearBotones() {
        fab_getLocalizacion.setEnabled(false);
        fab_continuar.setEnabled(false);
        //fab_search_location.setEnabled(false);
    }

    @Override
    public void desbloquearBotones() {
        fab_getLocalizacion.setEnabled(true);
        fab_continuar.setEnabled(true);
        //fab_search_location.setEnabled(true);
    }

    @Override
    public void limpiarTxtDireccion() {
        txt_direccion_GPS.setText("Buscando dirección actual...");
    }

    @Override
    public void limpiarBuscador() {
        edt_search_direccion.setText("");
    }

    @Override
    public void regresarActivity() {
        onBackPressed();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        googleMap.setOnCameraMoveListener(this);
        googleMap.setOnCameraIdleListener(this);
        ubicacionPresenter.getUbicacionActualGPS(Ubicacion.this);
    }

    @Override
    public void onCameraMove() {
        coordenadasAcual = mMap.getCameraPosition().target;
        if(coordenadasAcual != null) {
            if(markerPrueba != null) markerPrueba.setPosition(coordenadasAcual);
        }
    }

    @Override
    public void onCameraIdle() {
        coordenadasAcual = mMap.getCameraPosition().target;
        ubicacionPresenter.getNombreDireccion(Ubicacion.this, geocoder, coordenadasAcual);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_getLocalizacion:
                ubicacionPresenter.getUbicacionActualGPS(Ubicacion.this);
                break;

            case R.id.fab_continuar:
                ubicacionPresenter.guardarDireccion(Ubicacion.this, conn, geocoder, coordenadasAcual);
                break;

            case R.id.fab_search_location:
                ubicacionPresenter.buscarDireccion(Ubicacion.this, edt_search_direccion.getText().toString(), geocoder);
                break;

            case R.id.btn_clear_texto:
                ubicacionPresenter.limpiarBuscador();
                break;

            case R.id.btn_back_ubicacion:
                ubicacionPresenter.regresarActivity();
                break;
            default:
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("On Connection Failed", "Ocurrió un error");
    }
}
