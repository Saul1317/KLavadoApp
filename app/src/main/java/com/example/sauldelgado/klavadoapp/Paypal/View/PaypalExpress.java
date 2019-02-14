package com.example.sauldelgado.klavadoapp.Paypal.View;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.sauldelgado.klavadoapp.R;

public class PaypalExpress extends AppCompatActivity {

    String API_GET_TOKEN = "http://localhost/Klavado/paypal/main.php";
    String API_GET_CHECKOUT = "";
    private EditText cargo_total_prueba;
    private Button boton_pagar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal_express);
        cargo_total_prueba = (EditText) findViewById(R.id.cargo_total_prueba);
        boton_pagar = (Button) findViewById(R.id.boton_pagar);
    }



}
