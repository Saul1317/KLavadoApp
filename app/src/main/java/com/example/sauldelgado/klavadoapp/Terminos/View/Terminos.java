package com.example.sauldelgado.klavadoapp.Terminos.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauldelgado.klavadoapp.R;
import com.example.sauldelgado.klavadoapp.Terminos.Presenter.TerminosPresenter;
import com.example.sauldelgado.klavadoapp.Terminos.Presenter.TerminosPresenterImp;

public class Terminos extends AppCompatActivity implements TerminosView, View.OnClickListener {

    ImageView btn_back_terminos_condiciones;
    TerminosPresenter terminosPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terminos);
        LoadViews();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back_terminos_condiciones:
                terminosPresenter.Regresar();
                break;

             default:
                break;
        }
    }

    @Override
    public void LoadViews() {
        terminosPresenter = new TerminosPresenterImp(this);
        btn_back_terminos_condiciones = (ImageView) findViewById(R.id.btn_back_terminos_condiciones);
        btn_back_terminos_condiciones.setOnClickListener(this);
    }

    @Override
    public void Regresar() {
        onBackPressed();
    }
}
